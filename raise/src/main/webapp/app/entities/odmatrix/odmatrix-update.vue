<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="raiseApp.odmatrix.home.createOrEditLabel" v-text="$t('raiseApp.odmatrix.home.createOrEditLabel')">Create or edit a Odmatrix</h2>
                <div>
                    <div class="form-group" v-if="odmatrix.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="odmatrix.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.odmatrix.cost')" for="odmatrix-cost">Cost</label>
                        <input type="number" class="form-control" name="cost" id="odmatrix-cost"
                            :class="{'valid': !$v.odmatrix.cost.$invalid, 'invalid': $v.odmatrix.cost.$invalid }" v-model.number="$v.odmatrix.cost.$model"  required/>
                        <div v-if="$v.odmatrix.cost.$anyDirty && $v.odmatrix.cost.$invalid">
                            <small class="form-text text-danger" v-if="!$v.odmatrix.cost.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.odmatrix.cost.min" v-text="$t('entity.validation.min', {min: 0})">
                                This field should be at least 0.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.odmatrix.cost.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.odmatrix.fromRegion')" for="odmatrix-fromRegion">From Region</label>
                        <select class="form-control" id="odmatrix-fromRegion" name="fromRegion" v-model="odmatrix.fromRegion" required>
                            <option v-if="!odmatrix.fromRegion" v-bind:value="null" selected></option>
                            <option v-bind:value="odmatrix.fromRegion && regionOption.id === odmatrix.fromRegion.id ? odmatrix.fromRegion : regionOption" v-for="regionOption in regions" :key="regionOption.id">{{regionOption.name}}</option>
                        </select>
                    </div>
                    <div v-if="$v.odmatrix.fromRegion.$anyDirty && $v.odmatrix.fromRegion.$invalid">
                        <small class="form-text text-danger" v-if="!$v.odmatrix.fromRegion.required" v-text="$t('entity.validation.required')">
                            This field is required.
                        </small>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.odmatrix.toRegion')" for="odmatrix-toRegion">To Region</label>
                        <select class="form-control" id="odmatrix-toRegion" name="toRegion" v-model="odmatrix.toRegion" required>
                            <option v-if="!odmatrix.toRegion" v-bind:value="null" selected></option>
                            <option v-bind:value="odmatrix.toRegion && regionOption.id === odmatrix.toRegion.id ? odmatrix.toRegion : regionOption" v-for="regionOption in regions" :key="regionOption.id">{{regionOption.name}}</option>
                        </select>
                    </div>
                    <div v-if="$v.odmatrix.toRegion.$anyDirty && $v.odmatrix.toRegion.$invalid">
                        <small class="form-text text-danger" v-if="!$v.odmatrix.toRegion.required" v-text="$t('entity.validation.required')">
                            This field is required.
                        </small>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.odmatrix.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./odmatrix-update.component.ts">
</script>
