<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="demand">
                <h2 class="jh-entity-heading"><span v-text="$t('raiseApp.demand.detail.title')">Demand</span> {{demand.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('raiseApp.demand.amount')">Amount</span>
                    </dt>
                    <dd>
                        <span>{{demand.amount}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.demand.geometry')">Geometry</span>
                    </dt>
                    <dd>
                        <span>{{demand.geometry}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.demand.runInstance')">Run Instance</span>
                    </dt>
                    <dd>
                        <div v-if="demand.runInstance">
                            <router-link :to="{name: 'RunInstanceView', params: {runInstanceId: demand.runInstance.id}}">{{demand.runInstance.name}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.demand.resource')">Resource</span>
                    </dt>
                    <dd>
                        <div v-if="demand.resource">
                            <router-link :to="{name: 'ResourceView', params: {resourceId: demand.resource.id}}">{{demand.resource.name}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.demand.region')">Region</span>
                    </dt>
                    <dd>
                        <div v-if="demand.region">
                            <router-link :to="{name: 'RegionView', params: {regionId: demand.region.id}}">{{demand.region.name}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.demand.riskGroup')">Risk Group</span>
                    </dt>
                    <dd>
                        <div v-if="demand.riskGroup">
                            <router-link :to="{name: 'RiskGroupView', params: {riskGroupId: demand.riskGroup.id}}">{{demand.riskGroup.name}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="demand.id" :to="{name: 'DemandEdit', params: {demandId: demand.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./demand-details.component.ts">
</script>
