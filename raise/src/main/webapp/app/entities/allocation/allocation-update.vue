<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="raiseApp.allocation.home.createOrEditLabel" v-text="$t('raiseApp.allocation.home.createOrEditLabel')">Create or edit a Allocation</h2>
                <div>
                    <div class="form-group" v-if="allocation.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="allocation.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.allocation.amount')" for="allocation-amount">Amount</label>
                        <input type="number" class="form-control" name="amount" id="allocation-amount"
                            :class="{'valid': !$v.allocation.amount.$invalid, 'invalid': $v.allocation.amount.$invalid }" v-model.number="$v.allocation.amount.$model"  required/>
                        <div v-if="$v.allocation.amount.$anyDirty && $v.allocation.amount.$invalid">
                            <small class="form-text text-danger" v-if="!$v.allocation.amount.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.allocation.amount.min" v-text="$t('entity.validation.min', {min: 0})">
                                This field should be at least 0.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.allocation.amount.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.allocation.cost')" for="allocation-cost">Cost</label>
                        <input type="number" class="form-control" name="cost" id="allocation-cost"
                            :class="{'valid': !$v.allocation.cost.$invalid, 'invalid': $v.allocation.cost.$invalid }" v-model.number="$v.allocation.cost.$model"  required/>
                        <div v-if="$v.allocation.cost.$anyDirty && $v.allocation.cost.$invalid">
                            <small class="form-text text-danger" v-if="!$v.allocation.cost.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.allocation.cost.min" v-text="$t('entity.validation.min', {min: 0})">
                                This field should be at least 0.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.allocation.cost.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.allocation.runInstance')" for="allocation-runInstance">Run Instance</label>
                        <select class="form-control" id="allocation-runInstance" name="runInstance" v-model="allocation.runInstance">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="allocation.runInstance && runInstanceOption.id === allocation.runInstance.id ? allocation.runInstance : runInstanceOption" v-for="runInstanceOption in runInstances" :key="runInstanceOption.id">{{runInstanceOption.name}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.allocation.resource')" for="allocation-resource">Resource</label>
                        <select class="form-control" id="allocation-resource" name="resource" v-model="allocation.resource">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="allocation.resource && resourceOption.id === allocation.resource.id ? allocation.resource : resourceOption" v-for="resourceOption in resources" :key="resourceOption.id">{{resourceOption.name}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.allocation.region')" for="allocation-region">Region</label>
                        <select class="form-control" id="allocation-region" name="region" v-model="allocation.region">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="allocation.region && regionOption.id === allocation.region.id ? allocation.region : regionOption" v-for="regionOption in regions" :key="regionOption.id">{{regionOption.name}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.allocation.riskGroup')" for="allocation-riskGroup">Risk Group</label>
                        <select class="form-control" id="allocation-riskGroup" name="riskGroup" v-model="allocation.riskGroup">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="allocation.riskGroup && riskGroupOption.id === allocation.riskGroup.id ? allocation.riskGroup : riskGroupOption" v-for="riskGroupOption in riskGroups" :key="riskGroupOption.id">{{riskGroupOption.name}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.allocation.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./allocation-update.component.ts">
</script>
