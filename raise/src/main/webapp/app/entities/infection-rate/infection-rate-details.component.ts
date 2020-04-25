import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInfectionRate } from '@/shared/model/infection-rate.model';
import InfectionRateService from './infection-rate.service';

@Component
export default class InfectionRateDetails extends Vue {
  @Inject('infectionRateService') private infectionRateService: () => InfectionRateService;
  public infectionRate: IInfectionRate = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.infectionRateId) {
        vm.retrieveInfectionRate(to.params.infectionRateId);
      }
    });
  }

  public retrieveInfectionRate(infectionRateId) {
    this.infectionRateService()
      .find(infectionRateId)
      .then(res => {
        this.infectionRate = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
