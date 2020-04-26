<template>
  <div class="map-left">
    <h1>Map</h1>
    <h3>Set filters to view resources in an area.</h3>
  
    <md-list class="filters">
      <md-list-item>
        <span class="desc">Scale</span>
        <md-field>
          <md-input placeholder="Choose option..."></md-input>
        </md-field>
      </md-list-item>
      <md-list-item>
        <span class="desc">Location</span>
        <md-field>
          <md-autocomplete v-model="selectedPlace" placeholder="Type here..." :md-options="formattedPlaces" 
                    @md-changed="getPlaces" 
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
        <span class="desc">Risk</span>
        <md-field>
          <md-input placeholder="Choose option..."></md-input>
        </md-field>
      </md-list-item>
      <md-list-item>
        <span class="desc">View</span>
        <md-field>
          <md-input placeholder="Type here..."></md-input>
        </md-field>
      </md-list-item>
    </md-list>
  </div>
</template>

<script>
import { OpenStreetMapProvider } from 'leaflet-geosearch';
import { GoogleProvider } from 'leaflet-geosearch';

export default {
  name: 'MapLeft',
  components: {
  },
  data() {
    return {
      places: [],
      selectedPlace: null,
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
    }
  },
  watch: {
    places(oldValue, newValue) {
      try {
        // store coordinate to store
        console.log(newValue);
        console.log("places() - "+newValue[0].y);
        this.$store.commit('setMapFilterLocation', [newValue[0].y, newValue[0].x]);
      } 
      catch (ex) {

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
    },
  }
}
</script>
<style lang="scss">
  .map-left {
    h3 {
      color: #0052aa;
    }
    
    .md-list {
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
    /* clear button on line with this */
    .md-autocomplete .md-button {
       top: 0px;
    }
  }
</style>
