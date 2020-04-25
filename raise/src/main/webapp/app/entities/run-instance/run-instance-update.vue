<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="raiseApp.runInstance.home.createOrEditLabel" v-text="$t('raiseApp.runInstance.home.createOrEditLabel')">Create or edit a RunInstance</h2>
                <div>
                    <div class="form-group" v-if="runInstance.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="runInstance.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('raiseApp.runInstance.name')" for="run-instance-name">Name</label>
                        <input type="text" class="form-control" name="name" id="run-instance-name"
                            :class="{'valid': !$v.runInstance.name.$invalid, 'invalid': $v.runInstance.name.$invalid }" v-model="$v.runInstance.name.$model"  required/>
                        <div v-if="$v.runInstance.name.$anyDirty && $v.runInstance.name.$invalid">
                            <small class="form-text text-danger" v-if="!$v.runInstance.name.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.runInstance.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./run-instance-update.component.ts">
</script>
