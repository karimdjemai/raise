<template>
	<div class="input-table">
		<md-table v-model="demand">
			
			<md-table-row slot="md-table-row" slot-scope="{ item }">
				<md-table-cell md-label="ID" md-sort-by="id" md-numeric>
					{{item.id}}
				</md-table-cell>
				<md-table-cell md-label="Location" md-sort-by="location">
					<md-field>
						<md-input v-model="item.loc_name">{{ item.loc_name }}</md-input>
					</md-field>
				</md-table-cell>
				<md-table-cell md-label="Resource" md-sort-by="resource">
					<md-field>
						<md-input v-model="item.resource">{{ item.resource }}</md-input>
					</md-field>
				</md-table-cell>
				<md-table-cell md-label="Quantity" md-sort-by="quantity">
					<md-field>
						<md-input v-model="item.quantity">{{ item.quantity }}</md-input>
					</md-field>
				</md-table-cell>
			</md-table-row>
		</md-table>
	</div>
</template>

<script>
	export default {
		name: "InputTable",
		computed: {
			supply: {
				get() {
					return this.$store.state.inpSupplyValues;
				},
				set(value) {
					//TODO position
					value.position = {};
					this.$store.commit('setInpSupplyValues', value)
				}
			},
			demand: {
				get() {
					return this.$store.state.inpDemandValues;
				},
				set(value) {
					//TODO position
					value.position = {};
					this.$store.commit('setInpDemandValues', value)
				}
			},
		}
	}
</script>

<style lang="scss">
	.input-table {
		background-color: #f2f2f2;
		color: #54bbc1;
		border-radius: 1em;
		
		.md-table {
			.md-table-cell {
				.md-field {
					margin: 0;
					padding: 0;
					overflow: hidden;
					min-height: 0;
					
					.md-input {
						width: 100%;
						color: #999999;
					}
				}
			}
		}
	}
</style>
