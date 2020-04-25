<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="allocation">
                <h2 class="jh-entity-heading"><span v-text="$t('raiseApp.allocation.detail.title')">Allocation</span> {{allocation.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('raiseApp.allocation.amount')">Amount</span>
                    </dt>
                    <dd>
                        <span>{{allocation.amount}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.allocation.cost')">Cost</span>
                    </dt>
                    <dd>
                        <span>{{allocation.cost}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.allocation.runInstance')">Run Instance</span>
                    </dt>
                    <dd>
                        <div v-if="allocation.runInstance">
                            <router-link :to="{name: 'RunInstanceView', params: {runInstanceId: allocation.runInstance.id}}">{{allocation.runInstance.name}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.allocation.resource')">Resource</span>
                    </dt>
                    <dd>
                        <div v-if="allocation.resource">
                            <router-link :to="{name: 'ResourceView', params: {resourceId: allocation.resource.id}}">{{allocation.resource.name}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.allocation.region')">Region</span>
                    </dt>
                    <dd>
                        <div v-if="allocation.region">
                            <router-link :to="{name: 'RegionView', params: {regionId: allocation.region.id}}">{{allocation.region.name}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.allocation.riskGroup')">Risk Group</span>
                    </dt>
                    <dd>
                        <div v-if="allocation.riskGroup">
                            <router-link :to="{name: 'RiskGroupView', params: {riskGroupId: allocation.riskGroup.id}}">{{allocation.riskGroup.name}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="allocation.id" :to="{name: 'AllocationEdit', params: {allocationId: allocation.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./allocation-details.component.ts">
</script>
