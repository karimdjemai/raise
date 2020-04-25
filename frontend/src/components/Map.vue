<template>

  <div style="height: 500px; width: 100%">

    <GmapMap ref="mymap" style="width: 70%; height: 500px;" :zoom="zoom" :center="center" :options="options">
      <GmapMarker v-for="(marker, index) in markers"
        :key="index"
        :position="marker.position"
        />
      <GmapMarker
        v-if="this.place"
        label="★"
        :position="{
          lat: this.place.geometry.location.lat(),
          lng: this.place.geometry.location.lng(),
        }"
        />
    </GmapMap>

    <GmapAutocomplete @place_changed="setPlace">
      </GmapAutocomplete>
    <button @click="useAndZoomPlace">Add</button>
    <br/>
  </div>
</template>


<script>
// import { latLng } from "leaflet";
// import { LMap, LTileLayer, LMarker, LPopup, LTooltip, LGeoJson } from "vue2-leaflet";
// import { OpenStreetMapProvider } from 'leaflet-geosearch';
// import VGeosearch from 'vue2-leaflet-geosearch';
// import { InfoControl, ReferenceChart, ChoroplethLayer } from 'vue-choropleth'

//var geojson_url = "data/europe_medium.json";
//import Boundaries from '../assets/europe_medium.json'

import {gmapApi} from 'vue2-google-maps'

export default {
  name: "RaiseMap",
  components: {
    
  },
  data() {
    return {
      markers: [],
      place: null,
      options: {
        zoomControl: true,
        mapTypeControl: false,
        scaleControl: false,
        streetViewControl: false,
        rotateControl: false,
        fullscreenControl: false,
        disableDefaultUI: false,
        maxZoom: 11, 
      },
      zoom: 4.25,
      center: {lat: 49.8817161, lng: 12.3303441}
    }
  },
  computed: {
    google: gmapApi
  },
  methods: {
    setDescription(description) {
          this.description = description;
        },
    setPlace(place) {
      this.place = place;
    },
    usePlace(place) {

      if (this.place) {
        this.markers.push({
          position: {
            lat: this.place.geometry.location.lat(),
            lng: this.place.geometry.location.lng(),
          }
        });

        this.place = null;
      }
    },
    useAndZoomPlace(place){
      if (this.place) {
        let pos = { position: {
            lat: this.place.geometry.location.lat(),
            lng: this.place.geometry.location.lng(),
          }
        };
        this.markers.push(pos);
        //TODO get polygonal geometry of place (nominatim)
        // and zoom to region
        
        if (this.markers.length > 1) {
          var bounds = new google.maps.LatLngBounds();
          console.log(bounds);
          for (var i=0; i< this.markers.length; i++) {
            let newLatLng = this.markers[i];
            console.log(newLatLng);
            bounds.extend( newLatLng.position ); 
          }
          this.$refs.mymap.$mapObject.fitBounds(bounds);
        }
        else {
          this.center = pos.position;
        }
        this.place = null;
      }
    }
  }
};
</script>

<style>

</style>