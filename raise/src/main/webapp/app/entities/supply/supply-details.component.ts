import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISupply } from '@/shared/model/supply.model';
import SupplyService from './supply.service';

@Component
export default class SupplyDetails extends Vue {
  @Inject('supplyService') private supplyService: () => SupplyService;
  public supply: ISupply = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.supplyId) {
        vm.retrieveSupply(to.params.supplyId);
      }
    });
  }

  public retrieveSupply(supplyId) {
    this.supplyService()
      .find(supplyId)
      .then(res => {
        this.supply = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
