import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import RunInstanceService from '../run-instance/run-instance.service';
import { IRunInstance } from '@/shared/model/run-instance.model';

import ResourceService from '../resource/resource.service';
import { IResource } from '@/shared/model/resource.model';

import RegionService from '../region/region.service';
import { IRegion } from '@/shared/model/region.model';

import AlertService from '@/shared/alert/alert.service';
import { ISupply, Supply } from '@/shared/model/supply.model';
import SupplyService from './supply.service';

const validations: any = {
  supply: {
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
export default class SupplyUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('supplyService') private supplyService: () => SupplyService;
  public supply: ISupply = new Supply();

  @Inject('runInstanceService') private runInstanceService: () => RunInstanceService;

  public runInstances: IRunInstance[] = [];

  @Inject('resourceService') private resourceService: () => ResourceService;

  public resources: IResource[] = [];

  @Inject('regionService') private regionService: () => RegionService;

  public regions: IRegion[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.supplyId) {
        vm.retrieveSupply(to.params.supplyId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.supply.id) {
      this.supplyService()
        .update(this.supply)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.supply.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.supplyService()
        .create(this.supply)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.supply.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveSupply(supplyId): void {
    this.supplyService()
      .find(supplyId)
      .then(res => {
        this.supply = res;
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
  }
}
