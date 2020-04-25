<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="odmatrix">
                <h2 class="jh-entity-heading"><span v-text="$t('raiseApp.odmatrix.detail.title')">Odmatrix</span> {{odmatrix.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('raiseApp.odmatrix.cost')">Cost</span>
                    </dt>
                    <dd>
                        <span>{{odmatrix.cost}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.odmatrix.fromRegion')">From Region</span>
                    </dt>
                    <dd>
                        <div v-if="odmatrix.fromRegion">
                            <router-link :to="{name: 'RegionView', params: {regionId: odmatrix.fromRegion.id}}">{{odmatrix.fromRegion.name}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.odmatrix.toRegion')">To Region</span>
                    </dt>
                    <dd>
                        <div v-if="odmatrix.toRegion">
                            <router-link :to="{name: 'RegionView', params: {regionId: odmatrix.toRegion.id}}">{{odmatrix.toRegion.name}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="odmatrix.id" :to="{name: 'OdmatrixEdit', params: {odmatrixId: odmatrix.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./odmatrix-details.component.ts">
</script>
