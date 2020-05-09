<script>
	import { Bar } from 'vue-chartjs'
	
	export default {
		extends: Bar,
		props: {
			labels: {
				type: Array,
				required: false,
				default() {
					return []
				}
			},
			
			datasets: {
				type: Array,
				required: true
			}
		},
		
		data() {
			return {
				chartdata: {
					labels: this.labels,
					datasets: this.datasets,
				},
				
				options: {
					responsive: true,
					maintainAspectRatio: false,
				},
			}
		},
		
		computed: {
			myStyles () {
				return {
					height: `${this.height}px`,
					position: 'relative'
				}
			}
		},
		
		mounted () {
			this.renderChart(this.chartdata, this.options)
		}
	}
</script>

<style lang="scss">

</style>
