import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IResource, Resource } from '@/shared/model/resource.model';
import ResourceService from './resource.service';

const validations: any = {
  resource: {
    name: {
      required
    }
  }
};

@Component({
  validations
})
export default class ResourceUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('resourceService') private resourceService: () => ResourceService;
  public resource: IResource = new Resource();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.resourceId) {
        vm.retrieveResource(to.params.resourceId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.resource.id) {
      this.resourceService()
        .update(this.resource)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.resource.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.resourceService()
        .create(this.resource)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('raiseApp.resource.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveResource(resourceId): void {
    this.resourceService()
      .find(resourceId)
      .then(res => {
        this.resource = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
