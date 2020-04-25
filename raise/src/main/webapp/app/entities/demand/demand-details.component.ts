import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDemand } from '@/shared/model/demand.model';
import DemandService from './demand.service';

@Component
export default class DemandDetails extends Vue {
  @Inject('demandService') private demandService: () => DemandService;
  public demand: IDemand = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.demandId) {
        vm.retrieveDemand(to.params.demandId);
      }
    });
  }

  public retrieveDemand(demandId) {
    this.demandService()
      .find(demandId)
      .then(res => {
        this.demand = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
