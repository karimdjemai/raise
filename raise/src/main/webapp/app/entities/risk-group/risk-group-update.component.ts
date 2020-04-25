import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IRiskGroup, RiskGroup } from '@/shared/model/risk-group.model';
import RiskGroupService from './risk-group.service';

const validations: any = {
  riskGroup: {
    name: {
      required
    },
    category: {
      required,
      numeric,
      min: minValue(0)
    }
  }
};

@Component({
  validations
})
export default class RiskGroupUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('riskGroupService') private riskGroupService: () => RiskGroupService;
  public riskGroup: IRiskGroup = new RiskGroup();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.riskGroupId) {
        vm.retrieveRiskGroup(to.params.riskGroupId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.riskGroup.id) {
      this.riskGroupService()
        .update(this.riskGroup)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.riskGroup.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.riskGroupService()
        .create(this.riskGroup)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.riskGroup.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveRiskGroup(riskGroupId): void {
    this.riskGroupService()
      .find(riskGroupId)
      .then(res => {
        this.riskGroup = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
