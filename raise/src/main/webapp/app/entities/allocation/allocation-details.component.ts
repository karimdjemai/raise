import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAllocation } from '@/shared/model/allocation.model';
import AllocationService from './allocation.service';

@Component
export default class AllocationDetails extends Vue {
  @Inject('allocationService') private allocationService: () => AllocationService;
  public allocation: IAllocation = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.allocationId) {
        vm.retrieveAllocation(to.params.allocationId);
      }
    });
  }

  public retrieveAllocation(allocationId) {
    this.allocationService()
      .find(allocationId)
      .then(res => {
        this.allocation = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
