
<template>
  <div class="right-map">
    <div class="map-container">
      <raise-map class="map"/>
    </div>
    <div class="headers">
      <h1>Zuid Holland</h1>
      <h2>Last update: {{ last_update }} 25.04.20</h2>
    </div>
  </div>
</template>

<script>
// Google map variant
//import raiseMap from '../../components/MapGoogle.vue'

// Leaflet map variant
import raiseMap from '../../components/MapLeaflet.vue'

export default {
  name: 'MapView',
  components: {
    raiseMap
  }
}
</script>

<style lang="scss">
  .right-map {
    margin: -3em -4em;
    
    .map {
      width: 100%;
      height: 100vh;
    }
    
    .headers {
      position: absolute;
      z-index: 3000;
      top: 3em;
      right: 4em;
      text-align: right;
      color: #0052aa;
    }
  }
</style>
