<template>

  <div style="height: 500px; width: 100%" class="big-rounded-corners">
    
    <md-field style="margin: 4px 0!important;">
      <GmapAutocomplete style="background: white;" @place_changed="setPlace" class="md-input" placeholder="  Enter Location">
        </GmapAutocomplete>
      <md-button style="background: white; width: 32px; min-width: 32px; height: 32px;" @click="useAndZoomPlace" class="md-icon-button md-raised">
        <md-icon>add</md-icon>
      </md-button>
    </md-field>

    <GmapMap ref="mymap" style="width: 100%; height: 440px"  :zoom="zoom" :center="center" :options="options">

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


    <br/>
  </div>
</template>


<script>
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
      zoom: 4,
      center: {lat: 49.8817161, lng: 12.3303441},
      last_update: '2020-04-26'
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