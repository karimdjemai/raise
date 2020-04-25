<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="infectionRate">
                <h2 class="jh-entity-heading"><span v-text="$t('raiseApp.infectionRate.detail.title')">InfectionRate</span> {{infectionRate.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('raiseApp.infectionRate.amount')">Amount</span>
                    </dt>
                    <dd>
                        <span>{{infectionRate.amount}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('raiseApp.infectionRate.population')">Population</span>
                    </dt>
                    <dd>
                        <span>{{infectionRate.population}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="infectionRate.id" :to="{name: 'InfectionRateEdit', params: {infectionRateId: infectionRate.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./infection-rate-details.component.ts">
</script>
