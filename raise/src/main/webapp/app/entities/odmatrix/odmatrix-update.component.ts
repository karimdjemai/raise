import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import RegionService from '../region/region.service';
import { IRegion } from '@/shared/model/region.model';

import AlertService from '@/shared/alert/alert.service';
import { IOdmatrix, Odmatrix } from '@/shared/model/odmatrix.model';
import OdmatrixService from './odmatrix.service';

const validations: any = {
  odmatrix: {
    cost: {
      required,
      numeric,
      min: minValue(0)
    },
    fromRegion: {
      required
    },
    toRegion: {
      required
    }
  }
};

@Component({
  validations
})
export default class OdmatrixUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('odmatrixService') private odmatrixService: () => OdmatrixService;
  public odmatrix: IOdmatrix = new Odmatrix();

  @Inject('regionService') private regionService: () => RegionService;

  public regions: IRegion[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.odmatrixId) {
        vm.retrieveOdmatrix(to.params.odmatrixId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.odmatrix.id) {
      this.odmatrixService()
        .update(this.odmatrix)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.odmatrix.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.odmatrixService()
        .create(this.odmatrix)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.odmatrix.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveOdmatrix(odmatrixId): void {
    this.odmatrixService()
      .find(odmatrixId)
      .then(res => {
        this.odmatrix = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.regionService()
      .retrieve()
      .then(res => {
        this.regions = res.data;
      });
    this.regionService()
      .retrieve()
      .then(res => {
        this.regions = res.data;
      });
  }
}
