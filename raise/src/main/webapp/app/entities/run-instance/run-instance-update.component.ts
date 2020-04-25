import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IRunInstance, RunInstance } from '@/shared/model/run-instance.model';
import RunInstanceService from './run-instance.service';

const validations: any = {
  runInstance: {
    name: {
      required
    }
  }
};

@Component({
  validations
})
export default class RunInstanceUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('runInstanceService') private runInstanceService: () => RunInstanceService;
  public runInstance: IRunInstance = new RunInstance();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.runInstanceId) {
        vm.retrieveRunInstance(to.params.runInstanceId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.runInstance.id) {
      this.runInstanceService()
        .update(this.runInstance)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.runInstance.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.runInstanceService()
        .create(this.runInstance)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.runInstance.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveRunInstance(runInstanceId): void {
    this.runInstanceService()
      .find(runInstanceId)
      .then(res => {
        this.runInstance = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
