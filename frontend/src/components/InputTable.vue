<template>
	<div class="input-table">
		<md-table v-model="rows">
			<!-- <md-table-row>
				<md-table-head md-numeric>No.</md-table-head>
				<md-table-head>Location</md-table-head>
				<md-table-head>Resource</md-table-head>
				<md-table-head>Quantitiy</md-table-head>
			</md-table-row> -->
			
			<md-table-row slot="md-table-row" slot-scope="{ item }">
			<!-- <md-table-row v-for="n in rows.length" :key="n"> -->
				<md-table-cell md-label="ID" md-sort-by="id" md-numeric>
					{{item.id}}
				</md-table-cell>
				<md-table-cell md-label="Location" md-sort-by="location">
					<md-field>
						<md-input v-model="item.location">{{ item.location  }}</md-input>
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
		data() {
			return {
				rows: [{ id: 1,
						location: "Rijswijk",
						resource: "Doctors",
						quantity: 100
						},
						{
						id: 2,
						location: "Rijswijk",
						resource: "PPE",
						quantity: 1000
						},
						{
						id: 3,
						location: "Rijswijk",
						resource: "ventilators",
						quantity: 35
						}
					]
			};
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
