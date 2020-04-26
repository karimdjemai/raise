<template>
	<div class="input-table">
		<md-table>
			<md-table-row>
				<md-table-head md-numeric>No.</md-table-head>
				<md-table-head>Location</md-table-head>
				<md-table-head>Resource</md-table-head>
				<md-table-head>Quantitiy</md-table-head>
			</md-table-row>
			
			<md-table-row v-for="n in 10" :key="n">
				<md-table-cell md-numeric>
					{{n}}
				</md-table-cell>
				<md-table-cell>
					<md-field>
						<md-input></md-input>
					</md-field>
				</md-table-cell>
				<md-table-cell>
					<md-field>
						<md-input></md-input>
					</md-field>
				</md-table-cell>
				<md-table-cell>
					<md-field>
						<md-input></md-input>
					</md-field>
				</md-table-cell>
			</md-table-row>
		</md-table>
	</div>
</template>

<script>
	export default {
		name: "InputTable",
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
