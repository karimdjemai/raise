import { Component, Vue, Inject } from 'vue-property-decorator';

import { IOdmatrix } from '@/shared/model/odmatrix.model';
import OdmatrixService from './odmatrix.service';

@Component
export default class OdmatrixDetails extends Vue {
  @Inject('odmatrixService') private odmatrixService: () => OdmatrixService;
  public odmatrix: IOdmatrix = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.odmatrixId) {
        vm.retrieveOdmatrix(to.params.odmatrixId);
      }
    });
  }

  public retrieveOdmatrix(odmatrixId) {
    this.odmatrixService()
      .find(odmatrixId)
      .then(res => {
        this.odmatrix = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
