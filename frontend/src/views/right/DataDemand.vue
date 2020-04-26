<template>
	<div class="data-demand">
		<div class="header">
			<div class="heads">
				<h3>Existing: Name</h3>
				<h3>Last Updated: 24.04.2020</h3>
			</div>
			<md-button>Save</md-button>
		</div>
		<InputTable />
	</div>
</template>

<script>
	import InputTable from '../../components/DemandTable'
	
	
	export default {
		name: "DataDemand",
		components: {
			InputTable
		}
	}
</script>

<style lang="scss">
	.data-demand {
		.header {
			display: flex;
			justify-content: space-between;
			.heads {
				h3 {
					margin: 0.5em;
					color: #f2f2f2;
				}
			}
			
			.md-button {
				background-color: #f2f2f2;
				color: #54bbc1;
				text-transform: none;
				font-weight: bold;
				border-radius: 2em;
				height: 100%;
				padding: 1em;
			}
		}
	}
</style>
