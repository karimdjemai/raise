<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('raiseApp.odmatrix.home.title')" id="odmatrix-heading">Odmatrices</span>
            <router-link :to="{name: 'OdmatrixCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-odmatrix">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('raiseApp.odmatrix.home.createLabel')">
                    Create a new Odmatrix
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
        <div class="alert alert-warning" v-if="!isFetching && odmatrices && odmatrices.length === 0">
            <span v-text="$t('raiseApp.odmatrix.home.notFound')">No odmatrices found</span>
        </div>
        <div class="table-responsive" v-if="odmatrices && odmatrices.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('cost')"><span v-text="$t('raiseApp.odmatrix.cost')">Cost</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'cost'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('fromRegion.name')"><span v-text="$t('raiseApp.odmatrix.fromRegion')">From Region</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fromRegion.name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('toRegion.name')"><span v-text="$t('raiseApp.odmatrix.toRegion')">To Region</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'toRegion.name'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="odmatrix in odmatrices"
                    :key="odmatrix.id">
                    <td>
                        <router-link :to="{name: 'OdmatrixView', params: {odmatrixId: odmatrix.id}}">{{odmatrix.id}}</router-link>
                    </td>
                    <td>{{odmatrix.cost}}</td>
                    <td>
                        <div v-if="odmatrix.fromRegion">
                            <router-link :to="{name: 'RegionView', params: {regionId: odmatrix.fromRegion.id}}">{{odmatrix.fromRegion.name}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="odmatrix.toRegion">
                            <router-link :to="{name: 'RegionView', params: {regionId: odmatrix.toRegion.id}}">{{odmatrix.toRegion.name}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'OdmatrixView', params: {odmatrixId: odmatrix.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'OdmatrixEdit', params: {odmatrixId: odmatrix.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(odmatrix)"
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
            <span slot="modal-title"><span id="raiseApp.odmatrix.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-odmatrix-heading" v-text="$t('raiseApp.odmatrix.delete.question', {'id': removeId})">Are you sure you want to delete this Odmatrix?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-odmatrix" v-text="$t('entity.action.delete')" v-on:click="removeOdmatrix()">Delete</button>
            </div>
        </b-modal>
        <div v-show="odmatrices && odmatrices.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./odmatrix.component.ts">
</script>
