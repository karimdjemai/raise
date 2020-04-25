<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="raiseApp.resource.home.createOrEditLabel" v-text="$t('raiseApp.resource.home.createOrEditLabel')">Create or edit a Resource</h2>
                <div>
                    <div class="form-group" v-if="resource.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="resource.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.resource.name')" for="resource-name">Name</label>
                        <input type="text" class="form-control" name="name" id="resource-name"
                            :class="{'valid': !$v.resource.name.$invalid, 'invalid': $v.resource.name.$invalid }" v-model="$v.resource.name.$model"  required/>
                        <div v-if="$v.resource.name.$anyDirty && $v.resource.name.$invalid">
                            <small class="form-text text-danger" v-if="!$v.resource.name.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.resource.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./resource-update.component.ts">
</script>
