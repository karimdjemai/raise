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
      <l-marker :lat-lng="withPopup" v-if="showMarker">
        <l-popup>
          <div>
            {{ this.$store.state.mapFilterLocation }}
          </div>
        </l-popup>
      </l-marker>
      <l-feature-group>
        <l-circle
          v-for="circle in supplyCircles"
          :key="circle.id"
          :lat-lng="circle.position"
          :radius="circle.quantity"
          :color="supplyStyle.color"
          :fill-color="supplyStyle.color"
          :fill-opacity="supplyStyle.opacity">
          <l-popup>
            <div>
              <p class="title">
                {{ circle.loc_name }}
              </p>
               {{ "Resource: " + circle.resource }}
               <br>
               {{ "Quantity: " + circle.quantity }}
            </div>
          </l-popup>
        </l-circle>
      </l-feature-group>
      <l-feature-group>
        <l-circle
          withPopup=""
          v-for="circle in demandCircles"
          :key="circle.id"
          :lat-lng="circle.position"
          :radius="circle.quantity"
          :color="demandStyle.color"
          :fill-color="demandStyle.color"
          :fill-opacity="demandStyle.opacity">
         <l-popup>
            <div>
              <p class="title">
                {{ circle.loc_name }}
              </p>
               {{ "Resource: " + circle.resource }}
               <br>
               {{ "Quantity: " + circle.quantity }}
            </div>
          </l-popup>
        </l-circle>
      </l-feature-group>
    </l-map>
  </div>
</template>


<script>
import { latLng } from "leaflet";
import { LMap, LTileLayer, LMarker, LPopup, LTooltip, LCircle, LCircleMarker, LFeatureGroup } from "vue2-leaflet";

export default {
  name: "RaiseMap",
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LPopup,
    LTooltip,
    LCircle,
    LCircleMarker,
    LFeatureGroup
  },
  data() {
    return {
      zoom: 9,
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      attribution:
        '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      currentZoom: 9,
      currentCenter: this.$store.state.mapFilterLocation,
      mapOptions: {
        zoomSnap: 0.5
      },
      showMap: true,
      showMarker: false,
      supplyStyle: {color: 'blue', opacity: 1.0 },
      demandStyle: {color: 'darkred', opacity: 1.0 },
    }
  },
  mounted() {

  },
  computed: {
    center() {
       return this.$store.state.mapFilterLocation;
    },
    withPopup() {
       return this.$store.state.mapFilterLocation;
    },
    withTooltip() {
       return this.$store.state.mapFilterLocation;
    },
    supplyCircles() {
      return this.$store.state.mapSupplyValues;
    },
    demandCircles() {
      return this.$store.state.mapDemandValues;
     }
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
