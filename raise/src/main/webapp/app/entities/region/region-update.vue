<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="raiseApp.region.home.createOrEditLabel" v-text="$t('raiseApp.region.home.createOrEditLabel')">Create or edit a Region</h2>
                <div>
                    <div class="form-group" v-if="region.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="region.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.region.name')" for="region-name">Name</label>
                        <input type="text" class="form-control" name="name" id="region-name"
                            :class="{'valid': !$v.region.name.$invalid, 'invalid': $v.region.name.$invalid }" v-model="$v.region.name.$model"  required/>
                        <div v-if="$v.region.name.$anyDirty && $v.region.name.$invalid">
                            <small class="form-text text-danger" v-if="!$v.region.name.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.region.geometry')" for="region-geometry">Geometry</label>
                        <input type="text" class="form-control" name="geometry" id="region-geometry"
                            :class="{'valid': !$v.region.geometry.$invalid, 'invalid': $v.region.geometry.$invalid }" v-model="$v.region.geometry.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.region.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./region-update.component.ts">
</script>
