import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import RunInstanceService from '../run-instance/run-instance.service';
import { IRunInstance } from '@/shared/model/run-instance.model';

import ResourceService from '../resource/resource.service';
import { IResource } from '@/shared/model/resource.model';

import RegionService from '../region/region.service';
import { IRegion } from '@/shared/model/region.model';

import RiskGroupService from '../risk-group/risk-group.service';
import { IRiskGroup } from '@/shared/model/risk-group.model';

import AlertService from '@/shared/alert/alert.service';
import { IAllocation, Allocation } from '@/shared/model/allocation.model';
import AllocationService from './allocation.service';

const validations: any = {
  allocation: {
    amount: {
      required,
      numeric,
      min: minValue(0)
    },
    cost: {
      required,
      numeric,
      min: minValue(0)
    }
  }
};

@Component({
  validations
})
export default class AllocationUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('allocationService') private allocationService: () => AllocationService;
  public allocation: IAllocation = new Allocation();

  @Inject('runInstanceService') private runInstanceService: () => RunInstanceService;

  public runInstances: IRunInstance[] = [];

  @Inject('resourceService') private resourceService: () => ResourceService;

  public resources: IResource[] = [];

  @Inject('regionService') private regionService: () => RegionService;

  public regions: IRegion[] = [];

  @Inject('riskGroupService') private riskGroupService: () => RiskGroupService;

  public riskGroups: IRiskGroup[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.allocationId) {
        vm.retrieveAllocation(to.params.allocationId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.allocation.id) {
      this.allocationService()
        .update(this.allocation)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.allocation.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.allocationService()
        .create(this.allocation)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.allocation.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveAllocation(allocationId): void {
    this.allocationService()
      .find(allocationId)
      .then(res => {
        this.allocation = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.runInstanceService()
      .retrieve()
      .then(res => {
        this.runInstances = res.data;
      });
    this.resourceService()
      .retrieve()
      .then(res => {
        this.resources = res.data;
      });
    this.regionService()
      .retrieve()
      .then(res => {
        this.regions = res.data;
      });
    this.riskGroupService()
      .retrieve()
      .then(res => {
        this.riskGroups = res.data;
      });
  }
}
