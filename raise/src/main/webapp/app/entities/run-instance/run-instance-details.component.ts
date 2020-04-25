import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRunInstance } from '@/shared/model/run-instance.model';
import RunInstanceService from './run-instance.service';

@Component
export default class RunInstanceDetails extends Vue {
  @Inject('runInstanceService') private runInstanceService: () => RunInstanceService;
  public runInstance: IRunInstance = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.runInstanceId) {
        vm.retrieveRunInstance(to.params.runInstanceId);
      }
    });
  }

  public retrieveRunInstance(runInstanceId) {
    this.runInstanceService()
      .find(runInstanceId)
      .then(res => {
        this.runInstance = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
