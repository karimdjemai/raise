<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('raiseApp.allocation.home.title')" id="allocation-heading">Allocations</span>
            <router-link :to="{name: 'AllocationCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-allocation">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('raiseApp.allocation.home.createLabel')">
                    Create a new Allocation
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
        <div class="alert alert-warning" v-if="!isFetching && allocations && allocations.length === 0">
            <span v-text="$t('raiseApp.allocation.home.notFound')">No allocations found</span>
        </div>
        <div class="table-responsive" v-if="allocations && allocations.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('amount')"><span v-text="$t('raiseApp.allocation.amount')">Amount</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('cost')"><span v-text="$t('raiseApp.allocation.cost')">Cost</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'cost'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('runInstance.name')"><span v-text="$t('raiseApp.allocation.runInstance')">Run Instance</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'runInstance.name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('resource.name')"><span v-text="$t('raiseApp.allocation.resource')">Resource</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'resource.name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('region.name')"><span v-text="$t('raiseApp.allocation.region')">Region</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'region.name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('riskGroup.name')"><span v-text="$t('raiseApp.allocation.riskGroup')">Risk Group</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'riskGroup.name'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="allocation in allocations"
                    :key="allocation.id">
                    <td>
                        <router-link :to="{name: 'AllocationView', params: {allocationId: allocation.id}}">{{allocation.id}}</router-link>
                    </td>
                    <td>{{allocation.amount}}</td>
                    <td>{{allocation.cost}}</td>
                    <td>
                        <div v-if="allocation.runInstance">
                            <router-link :to="{name: 'RunInstanceView', params: {runInstanceId: allocation.runInstance.id}}">{{allocation.runInstance.name}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="allocation.resource">
                            <router-link :to="{name: 'ResourceView', params: {resourceId: allocation.resource.id}}">{{allocation.resource.name}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="allocation.region">
                            <router-link :to="{name: 'RegionView', params: {regionId: allocation.region.id}}">{{allocation.region.name}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="allocation.riskGroup">
                            <router-link :to="{name: 'RiskGroupView', params: {riskGroupId: allocation.riskGroup.id}}">{{allocation.riskGroup.name}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'AllocationView', params: {allocationId: allocation.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'AllocationEdit', params: {allocationId: allocation.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(allocation)"
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
            <span slot="modal-title"><span id="raiseApp.allocation.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-allocation-heading" v-text="$t('raiseApp.allocation.delete.question', {'id': removeId})">Are you sure you want to delete this Allocation?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-allocation" v-text="$t('entity.action.delete')" v-on:click="removeAllocation()">Delete</button>
            </div>
        </b-modal>
        <div v-show="allocations && allocations.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./allocation.component.ts">
</script>
