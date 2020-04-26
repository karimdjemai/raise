<template>
	<div class="data-left">
		<h1>Data</h1>
		<h3>Create new or choose existing template to input data.</h3>
		<md-list class="options">
			<md-list-item>
				<span class="desc">Template</span>
				<md-field>
					<md-input placeholder="Choose option..."></md-input>
				</md-field>
			</md-list-item>
			<md-list-item>
				<span class="desc">Scale</span>
				<md-field>
					<md-input placeholder="Type here..."></md-input>
				</md-field>
			</md-list-item>
			<md-list-item>
				<span class="desc">Location</span>
				<md-field>
				<md-autocomplete v-model="selectedPlace" placeholder="Type here..." :md-options="formattedPlaces" @md-changed="getPlaces" 
							@md-opened="getPlaces" :md-open-on-focus="false">
				</md-autocomplete>
				</md-field>
			</md-list-item>
			<md-list-item>
				<span class="desc">Resource</span>
				<md-field>
					<md-input placeholder="Choose option..."></md-input>
				</md-field>
			</md-list-item>
			<md-list-item>
				<span class="desc">Risk Group</span>
				<md-field>
					<md-input placeholder="Type here..."></md-input>
				</md-field>
			</md-list-item>
		</md-list>
		
		<md-list class="right-nav">
			<router-link to="/data/demand" tag="md-list-item">
				<span class="md-list-item-text">Demand</span>
			</router-link>
			
			<router-link to="/data/availability" tag="md-list-item">
				<span class="md-list-item-text">Availability</span>
			</router-link>
		</md-list>
	</div>
</template>

<script>
import { OpenStreetMapProvider } from 'leaflet-geosearch';
import { GoogleProvider } from 'leaflet-geosearch';

export default {
	name: "Data",
	data() {
		return {
		places: [],
		provider: new GoogleProvider({
						params: {
						key: 'AIzaSyCFguorprJJ2cHkl_0C27ROgFObfruntjo',
						}
		})
		}
	},
	computed: {
		formattedPlaces() {
			return this.places.map(p=>p.label);
		},
		selectedPlace: {
			get() {
				return this.$store.state.dataTemplateLocation;
			},
			set(value) {
				this.$store.commit('setDataTemplateLocation', value);
			}
		} 
	},
	methods: {
		getPlaces(searchTerm) {
		console.log(searchTerm);

		// const result = {
		//         x: Number,                      // lon,
		//         y: Number,                      // lat,
		//         label: String,                  // formatted address
		//         bounds: [
		//           [Number, Number],             // s, w - lat, lon
		//           [Number, Number],             // n, e - lat, lon
		//         ],
		//         raw: {},                        // raw provider result
		//       }
		this.provider.search({ query: searchTerm})
						.then(function(result) { 
						console.log(result);
						this.places = result;
						}.bind(this));
		}
	}
}
</script>

<style lang="scss">
	.data-left {
		.options {
			.md-list-item {
				background-color: #fff;
				margin: 1em;
				border-radius: 1em;
				
				.md-list-item-content {
					min-height: 0;
					display: flex;
					justify-content: space-between;
					
					.desc {
						display: inline-block;
						width: 50%;
						font-weight: bold;
						color: #54bbc1;
					}
					
					.md-field {
						margin: 0;
						padding: 0;
						min-height: 0;
						width: 100%;
						
						.md-input {
							font-size: 10pt;
							color: #b3b3b3;
							font-weight: 500;
						}
					}
				}
			}
		}
		
		.right-nav {
			position: absolute;
			right: -4em;
			
			.md-list-item-content {
				min-height: 0;
				padding: 1em 1.6em;
			}
			
			.md-list-item {
				margin: {
					top: 0.5rem;
					bottom: 0.5rem;
					left: 0;
				}
				
				.md-list-item-text {
					color: #54bbc1;
					display: inline;
					text-align: center;
					padding-left: 0rem;
					font-weight: bold;
					font-size: 13pt;
					border-radius: 0.8rem;
					vertical-align: center;
				}
			}
			
			.router-link-exact-active {
				.md-list-item-text {
					background-color: #54bbc1;
					color: #f2f2f2;
				}
			}
		}
		/* clear button on line with this */
		.md-autocomplete .md-button {
		top: 0px;
		}
	}
</style>
