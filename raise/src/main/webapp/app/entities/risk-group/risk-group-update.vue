<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="raiseApp.riskGroup.home.createOrEditLabel" v-text="$t('raiseApp.riskGroup.home.createOrEditLabel')">Create or edit a RiskGroup</h2>
                <div>
                    <div class="form-group" v-if="riskGroup.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="riskGroup.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.riskGroup.name')" for="risk-group-name">Name</label>
                        <input type="text" class="form-control" name="name" id="risk-group-name"
                            :class="{'valid': !$v.riskGroup.name.$invalid, 'invalid': $v.riskGroup.name.$invalid }" v-model="$v.riskGroup.name.$model"  required/>
                        <div v-if="$v.riskGroup.name.$anyDirty && $v.riskGroup.name.$invalid">
                            <small class="form-text text-danger" v-if="!$v.riskGroup.name.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.riskGroup.category')" for="risk-group-category">Category</label>
                        <input type="number" class="form-control" name="category" id="risk-group-category"
                            :class="{'valid': !$v.riskGroup.category.$invalid, 'invalid': $v.riskGroup.category.$invalid }" v-model.number="$v.riskGroup.category.$model"  required/>
                        <div v-if="$v.riskGroup.category.$anyDirty && $v.riskGroup.category.$invalid">
                            <small class="form-text text-danger" v-if="!$v.riskGroup.category.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.riskGroup.category.min" v-text="$t('entity.validation.min', {min: 0})">
                                This field should be at least 0.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.riskGroup.category.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.riskGroup.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./risk-group-update.component.ts">
</script>
