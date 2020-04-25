<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="riskGroup">
                <h2 class="jh-entity-heading"><span v-text="$t('raiseApp.riskGroup.detail.title')">RiskGroup</span> {{riskGroup.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('raiseApp.riskGroup.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{riskGroup.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.riskGroup.category')">Category</span>
                    </dt>
                    <dd>
                        <span>{{riskGroup.category}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="riskGroup.id" :to="{name: 'RiskGroupEdit', params: {riskGroupId: riskGroup.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./risk-group-details.component.ts">
</script>
