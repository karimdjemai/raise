<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="raiseApp.demand.home.createOrEditLabel" v-text="$t('raiseApp.demand.home.createOrEditLabel')">Create or edit a Demand</h2>
                <div>
                    <div class="form-group" v-if="demand.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="demand.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.demand.amount')" for="demand-amount">Amount</label>
                        <input type="number" class="form-control" name="amount" id="demand-amount"
                            :class="{'valid': !$v.demand.amount.$invalid, 'invalid': $v.demand.amount.$invalid }" v-model.number="$v.demand.amount.$model"  required/>
                        <div v-if="$v.demand.amount.$anyDirty && $v.demand.amount.$invalid">
                            <small class="form-text text-danger" v-if="!$v.demand.amount.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.demand.amount.min" v-text="$t('entity.validation.min', {min: 0})">
                                This field should be at least 0.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.demand.amount.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.demand.geometry')" for="demand-geometry">Geometry</label>
                        <input type="text" class="form-control" name="geometry" id="demand-geometry"
                            :class="{'valid': !$v.demand.geometry.$invalid, 'invalid': $v.demand.geometry.$invalid }" v-model="$v.demand.geometry.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.demand.runInstance')" for="demand-runInstance">Run Instance</label>
                        <select class="form-control" id="demand-runInstance" name="runInstance" v-model="demand.runInstance">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="demand.runInstance && runInstanceOption.id === demand.runInstance.id ? demand.runInstance : runInstanceOption" v-for="runInstanceOption in runInstances" :key="runInstanceOption.id">{{runInstanceOption.name}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.demand.resource')" for="demand-resource">Resource</label>
                        <select class="form-control" id="demand-resource" name="resource" v-model="demand.resource">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="demand.resource && resourceOption.id === demand.resource.id ? demand.resource : resourceOption" v-for="resourceOption in resources" :key="resourceOption.id">{{resourceOption.name}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.demand.region')" for="demand-region">Region</label>
                        <select class="form-control" id="demand-region" name="region" v-model="demand.region">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="demand.region && regionOption.id === demand.region.id ? demand.region : regionOption" v-for="regionOption in regions" :key="regionOption.id">{{regionOption.name}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.demand.riskGroup')" for="demand-riskGroup">Risk Group</label>
                        <select class="form-control" id="demand-riskGroup" name="riskGroup" v-model="demand.riskGroup">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="demand.riskGroup && riskGroupOption.id === demand.riskGroup.id ? demand.riskGroup : riskGroupOption" v-for="riskGroupOption in riskGroups" :key="riskGroupOption.id">{{riskGroupOption.name}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.demand.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./demand-update.component.ts">
</script>
