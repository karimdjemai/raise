<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="raiseApp.supply.home.createOrEditLabel" v-text="$t('raiseApp.supply.home.createOrEditLabel')">Create or edit a Supply</h2>
                <div>
                    <div class="form-group" v-if="supply.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="supply.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.supply.amount')" for="supply-amount">Amount</label>
                        <input type="number" class="form-control" name="amount" id="supply-amount"
                            :class="{'valid': !$v.supply.amount.$invalid, 'invalid': $v.supply.amount.$invalid }" v-model.number="$v.supply.amount.$model"  required/>
                        <div v-if="$v.supply.amount.$anyDirty && $v.supply.amount.$invalid">
                            <small class="form-text text-danger" v-if="!$v.supply.amount.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.supply.amount.min" v-text="$t('entity.validation.min', {min: 0})">
                                This field should be at least 0.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.supply.amount.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.supply.geometry')" for="supply-geometry">Geometry</label>
                        <input type="text" class="form-control" name="geometry" id="supply-geometry"
                            :class="{'valid': !$v.supply.geometry.$invalid, 'invalid': $v.supply.geometry.$invalid }" v-model="$v.supply.geometry.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.supply.runInstance')" for="supply-runInstance">Run Instance</label>
                        <select class="form-control" id="supply-runInstance" name="runInstance" v-model="supply.runInstance">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="supply.runInstance && runInstanceOption.id === supply.runInstance.id ? supply.runInstance : runInstanceOption" v-for="runInstanceOption in runInstances" :key="runInstanceOption.id">{{runInstanceOption.name}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.supply.resource')" for="supply-resource">Resource</label>
                        <select class="form-control" id="supply-resource" name="resource" v-model="supply.resource">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="supply.resource && resourceOption.id === supply.resource.id ? supply.resource : resourceOption" v-for="resourceOption in resources" :key="resourceOption.id">{{resourceOption.name}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.supply.region')" for="supply-region">Region</label>
                        <select class="form-control" id="supply-region" name="region" v-model="supply.region">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="supply.region && regionOption.id === supply.region.id ? supply.region : regionOption" v-for="regionOption in regions" :key="regionOption.id">{{regionOption.name}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.supply.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./supply-update.component.ts">
</script>
