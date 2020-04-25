<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="supply">
                <h2 class="jh-entity-heading"><span v-text="$t('raiseApp.supply.detail.title')">Supply</span> {{supply.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('raiseApp.supply.amount')">Amount</span>
                    </dt>
                    <dd>
                        <span>{{supply.amount}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.supply.geometry')">Geometry</span>
                    </dt>
                    <dd>
                        <span>{{supply.geometry}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.supply.runInstance')">Run Instance</span>
                    </dt>
                    <dd>
                        <div v-if="supply.runInstance">
                            <router-link :to="{name: 'RunInstanceView', params: {runInstanceId: supply.runInstance.id}}">{{supply.runInstance.name}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.supply.resource')">Resource</span>
                    </dt>
                    <dd>
                        <div v-if="supply.resource">
                            <router-link :to="{name: 'ResourceView', params: {resourceId: supply.resource.id}}">{{supply.resource.name}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.supply.region')">Region</span>
                    </dt>
                    <dd>
                        <div v-if="supply.region">
                            <router-link :to="{name: 'RegionView', params: {regionId: supply.region.id}}">{{supply.region.name}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="supply.id" :to="{name: 'SupplyEdit', params: {supplyId: supply.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./supply-details.component.ts">
</script>
