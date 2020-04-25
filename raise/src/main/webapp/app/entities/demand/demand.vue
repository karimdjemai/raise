<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('raiseApp.demand.home.title')" id="demand-heading">Demands</span>
            <router-link :to="{name: 'DemandCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-demand">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('raiseApp.demand.home.createLabel')">
                    Create a new Demand
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
        <div class="alert alert-warning" v-if="!isFetching && demands && demands.length === 0">
            <span v-text="$t('raiseApp.demand.home.notFound')">No demands found</span>
        </div>
        <div class="table-responsive" v-if="demands && demands.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('amount')"><span v-text="$t('raiseApp.demand.amount')">Amount</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('geometry')"><span v-text="$t('raiseApp.demand.geometry')">Geometry</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'geometry'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('runInstance.name')"><span v-text="$t('raiseApp.demand.runInstance')">Run Instance</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'runInstance.name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('resource.name')"><span v-text="$t('raiseApp.demand.resource')">Resource</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'resource.name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('region.name')"><span v-text="$t('raiseApp.demand.region')">Region</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'region.name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('riskGroup.name')"><span v-text="$t('raiseApp.demand.riskGroup')">Risk Group</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'riskGroup.name'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="demand in demands"
                    :key="demand.id">
                    <td>
                        <router-link :to="{name: 'DemandView', params: {demandId: demand.id}}">{{demand.id}}</router-link>
                    </td>
                    <td>{{demand.amount}}</td>
                    <td>{{demand.geometry}}</td>
                    <td>
                        <div v-if="demand.runInstance">
                            <router-link :to="{name: 'RunInstanceView', params: {runInstanceId: demand.runInstance.id}}">{{demand.runInstance.name}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="demand.resource">
                            <router-link :to="{name: 'ResourceView', params: {resourceId: demand.resource.id}}">{{demand.resource.name}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="demand.region">
                            <router-link :to="{name: 'RegionView', params: {regionId: demand.region.id}}">{{demand.region.name}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="demand.riskGroup">
                            <router-link :to="{name: 'RiskGroupView', params: {riskGroupId: demand.riskGroup.id}}">{{demand.riskGroup.name}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'DemandView', params: {demandId: demand.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'DemandEdit', params: {demandId: demand.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(demand)"
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
            <span slot="modal-title"><span id="raiseApp.demand.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-demand-heading" v-text="$t('raiseApp.demand.delete.question', {'id': removeId})">Are you sure you want to delete this Demand?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-demand" v-text="$t('entity.action.delete')" v-on:click="removeDemand()">Delete</button>
            </div>
        </b-modal>
        <div v-show="demands && demands.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./demand.component.ts">
</script>
