import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRiskGroup } from '@/shared/model/risk-group.model';
import RiskGroupService from './risk-group.service';

@Component
export default class RiskGroupDetails extends Vue {
  @Inject('riskGroupService') private riskGroupService: () => RiskGroupService;
  public riskGroup: IRiskGroup = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.riskGroupId) {
        vm.retrieveRiskGroup(to.params.riskGroupId);
      }
    });
  }

  public retrieveRiskGroup(riskGroupId) {
    this.riskGroupService()
      .find(riskGroupId)
      .then(res => {
        this.riskGroup = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
