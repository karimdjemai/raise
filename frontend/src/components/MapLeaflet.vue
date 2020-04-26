<template>

  <div>
    <l-map ref="raiseMap"
      v-if="showMap"
      :zoom="zoom"
      :center="center"
      :options="mapOptions"
      @update:center="centerUpdate"
      @update:zoom="zoomUpdate"
    >
      <l-tile-layer
        :url="url"
        :attribution="attribution"
      />
      <l-marker :lat-lng="withPopup">
        <l-popup>
          <div>
            {{ this.$store.state.mapFilterLocation }}
          </div>
        </l-popup>
      </l-marker>
    </l-map>
  </div>
</template>


<script>
import { latLng } from "leaflet";
import { LMap, LTileLayer, LMarker, LPopup, LTooltip } from "vue2-leaflet";

export default {
  name: "RaiseMap",
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LPopup,
    LTooltip,
  },
  data() {
    return {
      zoom: 9,
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      attribution:
        '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      currentZoom: 9,
      currentCenter: latLng(this.$store.state.mapFilterLocation),
      mapOptions: {
        zoomSnap: 0.5
      },
      showMap: true,
    }
  },
  mounted() {

  },
  computed: {
    center() {
       return latLng(this.$store.state.mapFilterLocation);
    },
    withPopup() {
       return latLng(this.$store.state.mapFilterLocation);
    },
    withTooltip() {
       return latLng(this.$store.state.mapFilterLocation);
    },
  },
  methods: {
    zoomUpdate(zoom) {
      this.currentZoom = zoom;
    },
    centerUpdate(center) {
      this.currentCenter = center;
    },
  }
};
</script>

<style>

  /*.leaflet-control-geosearch form input {*/
  /*  height: 1.75rem!important;*/
  /*}*/
  
  .leaflet-container {
    border-radius: 1.5em 0 0 1.5em;
  }
</style>
