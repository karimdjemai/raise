import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IInfectionRate } from '@/shared/model/infection-rate.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import InfectionRateService from './infection-rate.service';

@Component
export default class InfectionRate extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('infectionRateService') private infectionRateService: () => InfectionRateService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public infectionRates: IInfectionRate[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllInfectionRates();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllInfectionRates();
  }

  public retrieveAllInfectionRates(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort()
    };
    this.infectionRateService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.infectionRates = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IInfectionRate): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeInfectionRate(): void {
    this.infectionRateService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('raiseApp.infectionRate.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllInfectionRates();
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
    this.retrieveAllInfectionRates();
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
