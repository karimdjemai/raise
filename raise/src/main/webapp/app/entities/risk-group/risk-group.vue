<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('raiseApp.riskGroup.home.title')" id="risk-group-heading">Risk Groups</span>
            <router-link :to="{name: 'RiskGroupCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-risk-group">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('raiseApp.riskGroup.home.createLabel')">
                    Create a new Risk Group
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
        <div class="alert alert-warning" v-if="!isFetching && riskGroups && riskGroups.length === 0">
            <span v-text="$t('raiseApp.riskGroup.home.notFound')">No riskGroups found</span>
        </div>
        <div class="table-responsive" v-if="riskGroups && riskGroups.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('raiseApp.riskGroup.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('category')"><span v-text="$t('raiseApp.riskGroup.category')">Category</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'category'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="riskGroup in riskGroups"
                    :key="riskGroup.id">
                    <td>
                        <router-link :to="{name: 'RiskGroupView', params: {riskGroupId: riskGroup.id}}">{{riskGroup.id}}</router-link>
                    </td>
                    <td>{{riskGroup.name}}</td>
                    <td>{{riskGroup.category}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'RiskGroupView', params: {riskGroupId: riskGroup.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'RiskGroupEdit', params: {riskGroupId: riskGroup.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(riskGroup)"
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
            <span slot="modal-title"><span id="raiseApp.riskGroup.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-riskGroup-heading" v-text="$t('raiseApp.riskGroup.delete.question', {'id': removeId})">Are you sure you want to delete this Risk Group?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-riskGroup" v-text="$t('entity.action.delete')" v-on:click="removeRiskGroup()">Delete</button>
            </div>
        </b-modal>
        <div v-show="riskGroups && riskGroups.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./risk-group.component.ts">
</script>
