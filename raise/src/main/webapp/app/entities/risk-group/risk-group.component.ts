import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IRiskGroup } from '@/shared/model/risk-group.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import RiskGroupService from './risk-group.service';

@Component
export default class RiskGroup extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('riskGroupService') private riskGroupService: () => RiskGroupService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public riskGroups: IRiskGroup[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllRiskGroups();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllRiskGroups();
  }

  public retrieveAllRiskGroups(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort()
    };
    this.riskGroupService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.riskGroups = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IRiskGroup): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeRiskGroup(): void {
    this.riskGroupService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('raiseApp.riskGroup.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllRiskGroups();
        this.closeDialog();
      });
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllRiskGroups();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
