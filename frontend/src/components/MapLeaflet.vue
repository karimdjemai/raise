<template>

  <div style="height: 500px; width: 100%">
    <div style="height: 200px overflow: auto;">
    </div>
    <l-map
      v-if="showMap"
      :zoom="zoom"
      :center="center"
      :options="mapOptions"
      style="height: 80%"
      @update:center="centerUpdate"
      @update:zoom="zoomUpdate"
    >
      <l-tile-layer
        :url="url"
        :attribution="attribution"
      />
      <l-marker :lat-lng="withPopup">
        <l-popup>
          <div @click="innerClick">
            I am a popup
            <p v-show="showParagraph">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque
              sed pretium nisl, ut sagittis sapien. Sed vel sollicitudin nisi.
              Donec finibus semper metus id malesuada.
            </p>
          </div>
        </l-popup>
      </l-marker>
      <v-geosearch :options="geosearchOptions" ></v-geosearch>
    </l-map>
  </div>
</template>


<script>
import { latLng } from "leaflet";
import { LMap, LTileLayer, LMarker, LPopup, LTooltip } from "vue2-leaflet";
import { OpenStreetMapProvider } from 'leaflet-geosearch';
import { GoogleProvider } from 'leaflet-geosearch';

import VGeosearch from 'vue2-leaflet-geosearch';

export default {
  name: "RaiseMap",
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LPopup,
    LTooltip,
    VGeosearch
  },
  data() {
    return {
      zoom: 9,
      center: latLng(52.030433, 4.474871), //netherlands
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      attribution:
        '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      withPopup: latLng(52.030433, 4.474871),
      withTooltip: latLng(52.030433, 4.474871),
      currentZoom: 9,
      currentCenter: latLng(52.030433, 4.474871),
      showParagraph: false,
      mapOptions: {
        zoomSnap: 0.5
      },
      showMap: true,
      geosearchOptions: { // Important part Here
        //provider: new OpenStreetMapProvider(),
        provider: new GoogleProvider({ 
                  params: {
                    key: 'AIzaSyCFguorprJJ2cHkl_0C27ROgFObfruntjo',
                  }
        })
      }
  
    };
  },
  methods: {
    zoomUpdate(zoom) {
      this.currentZoom = zoom;
    },
    centerUpdate(center) {
      this.currentCenter = center;
    },
    showLongText() {
      this.showParagraph = !this.showParagraph;
    },
    innerClick() {
      alert("Click!");
    }
  }
};
</script>

<style>

  .leaflet-control-geosearch form input {
    height: 1.75rem!important;
  }
</style>