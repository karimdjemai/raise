import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IInfectionRate, InfectionRate } from '@/shared/model/infection-rate.model';
import InfectionRateService from './infection-rate.service';

const validations: any = {
  infectionRate: {
    amount: {
      required,
      numeric,
      min: minValue(0)
    },
    population: {
      required,
      numeric,
      min: minValue(0)
    }
  }
};

@Component({
  validations
})
export default class InfectionRateUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('infectionRateService') private infectionRateService: () => InfectionRateService;
  public infectionRate: IInfectionRate = new InfectionRate();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.infectionRateId) {
        vm.retrieveInfectionRate(to.params.infectionRateId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.infectionRate.id) {
      this.infectionRateService()
        .update(this.infectionRate)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.infectionRate.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.infectionRateService()
        .create(this.infectionRate)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.infectionRate.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveInfectionRate(infectionRateId): void {
    this.infectionRateService()
      .find(infectionRateId)
      .then(res => {
        this.infectionRate = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
