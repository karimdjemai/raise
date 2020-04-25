<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="raiseApp.infectionRate.home.createOrEditLabel" v-text="$t('raiseApp.infectionRate.home.createOrEditLabel')">Create or edit a InfectionRate</h2>
                <div>
                    <div class="form-group" v-if="infectionRate.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="infectionRate.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.infectionRate.amount')" for="infection-rate-amount">Amount</label>
                        <input type="number" class="form-control" name="amount" id="infection-rate-amount"
                            :class="{'valid': !$v.infectionRate.amount.$invalid, 'invalid': $v.infectionRate.amount.$invalid }" v-model.number="$v.infectionRate.amount.$model"  required/>
                        <div v-if="$v.infectionRate.amount.$anyDirty && $v.infectionRate.amount.$invalid">
                            <small class="form-text text-danger" v-if="!$v.infectionRate.amount.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.infectionRate.amount.min" v-text="$t('entity.validation.min', {min: 0})">
                                This field should be at least 0.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.infectionRate.amount.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.infectionRate.population')" for="infection-rate-population">Population</label>
                        <input type="number" class="form-control" name="population" id="infection-rate-population"
                            :class="{'valid': !$v.infectionRate.population.$invalid, 'invalid': $v.infectionRate.population.$invalid }" v-model.number="$v.infectionRate.population.$model"  required/>
                        <div v-if="$v.infectionRate.population.$anyDirty && $v.infectionRate.population.$invalid">
                            <small class="form-text text-danger" v-if="!$v.infectionRate.population.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.infectionRate.population.min" v-text="$t('entity.validation.min', {min: 0})">
                                This field should be at least 0.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.infectionRate.population.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.infectionRate.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./infection-rate-update.component.ts">
</script>
