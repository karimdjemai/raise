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
import { IDemand, Demand } from '@/shared/model/demand.model';
import DemandService from './demand.service';

const validations: any = {
  demand: {
    amount: {
      required,
      numeric,
      min: minValue(0)
    },
    geometry: {}
  }
};

@Component({
  validations
})
export default class DemandUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('demandService') private demandService: () => DemandService;
  public demand: IDemand = new Demand();

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
      if (to.params.demandId) {
        vm.retrieveDemand(to.params.demandId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.demand.id) {
      this.demandService()
        .update(this.demand)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.demand.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.demandService()
        .create(this.demand)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.demand.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveDemand(demandId): void {
    this.demandService()
      .find(demandId)
      .then(res => {
        this.demand = res;
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
