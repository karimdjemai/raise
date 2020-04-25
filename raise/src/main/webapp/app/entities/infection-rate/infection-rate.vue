<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('raiseApp.infectionRate.home.title')" id="infection-rate-heading">Infection Rates</span>
            <router-link :to="{name: 'InfectionRateCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-infection-rate">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('raiseApp.infectionRate.home.createLabel')">
                    Create a new Infection Rate
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && infectionRates && infectionRates.length === 0">
            <span v-text="$t('raiseApp.infectionRate.home.notFound')">No infectionRates found</span>
        </div>
        <div class="table-responsive" v-if="infectionRates && infectionRates.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('amount')"><span v-text="$t('raiseApp.infectionRate.amount')">Amount</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('population')"><span v-text="$t('raiseApp.infectionRate.population')">Population</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'population'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="infectionRate in infectionRates"
                    :key="infectionRate.id">
                    <td>
                        <router-link :to="{name: 'InfectionRateView', params: {infectionRateId: infectionRate.id}}">{{infectionRate.id}}</router-link>
                    </td>
                    <td>{{infectionRate.amount}}</td>
                    <td>{{infectionRate.population}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'InfectionRateView', params: {infectionRateId: infectionRate.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'InfectionRateEdit', params: {infectionRateId: infectionRate.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(infectionRate)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="raiseApp.infectionRate.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-infectionRate-heading" v-text="$t('raiseApp.infectionRate.delete.question', {'id': removeId})">Are you sure you want to delete this Infection Rate?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-infectionRate" v-text="$t('entity.action.delete')" v-on:click="removeInfectionRate()">Delete</button>
            </div>
        </b-modal>
        <div v-show="infectionRates && infectionRates.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./infection-rate.component.ts">
</script>
