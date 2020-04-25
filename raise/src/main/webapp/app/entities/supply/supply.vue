<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('raiseApp.supply.home.title')" id="supply-heading">Supplies</span>
            <router-link :to="{name: 'SupplyCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-supply">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('raiseApp.supply.home.createLabel')">
                    Create a new Supply
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
        <div class="alert alert-warning" v-if="!isFetching && supplies && supplies.length === 0">
            <span v-text="$t('raiseApp.supply.home.notFound')">No supplies found</span>
        </div>
        <div class="table-responsive" v-if="supplies && supplies.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('amount')"><span v-text="$t('raiseApp.supply.amount')">Amount</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('geometry')"><span v-text="$t('raiseApp.supply.geometry')">Geometry</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'geometry'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('runInstance.name')"><span v-text="$t('raiseApp.supply.runInstance')">Run Instance</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'runInstance.name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('resource.name')"><span v-text="$t('raiseApp.supply.resource')">Resource</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'resource.name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('region.name')"><span v-text="$t('raiseApp.supply.region')">Region</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'region.name'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="supply in supplies"
                    :key="supply.id">
                    <td>
                        <router-link :to="{name: 'SupplyView', params: {supplyId: supply.id}}">{{supply.id}}</router-link>
                    </td>
                    <td>{{supply.amount}}</td>
                    <td>{{supply.geometry}}</td>
                    <td>
                        <div v-if="supply.runInstance">
                            <router-link :to="{name: 'RunInstanceView', params: {runInstanceId: supply.runInstance.id}}">{{supply.runInstance.name}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="supply.resource">
                            <router-link :to="{name: 'ResourceView', params: {resourceId: supply.resource.id}}">{{supply.resource.name}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="supply.region">
                            <router-link :to="{name: 'RegionView', params: {regionId: supply.region.id}}">{{supply.region.name}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SupplyView', params: {supplyId: supply.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'SupplyEdit', params: {supplyId: supply.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(supply)"
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
            <span slot="modal-title"><span id="raiseApp.supply.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-supply-heading" v-text="$t('raiseApp.supply.delete.question', {'id': removeId})">Are you sure you want to delete this Supply?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-supply" v-text="$t('entity.action.delete')" v-on:click="removeSupply()">Delete</button>
            </div>
        </b-modal>
        <div v-show="supplies && supplies.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./supply.component.ts">
</script>
