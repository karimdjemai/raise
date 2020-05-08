<template>
  <div class="home">
    <h1>Home</h1>
    <h3 class="">Daily Overview: {{ selectedPlace }} </h3>

    <div class="chart-container">
    
    </div>
    <div class="map-container">
      <raise-map class="map" />
    </div>
  </div>
  
</template>

<script>
// Leaflet map variant
import raiseMap from '../../components/MapLeaflet.vue'

export default {
  name: 'Home',
  components: {
    raiseMap
  },
  data() {
    return {
      selectedPlace: 'Netherlands',
    }
  },
  computed: {
    before() {
      return this.$store.state.graph_before
    },
  },
}
</script>
<style lang="scss">
  .home {
    .switch {
      max-width: 400px;
      img {
        position: absolute;
        left: -1em;
        max-width: none;
        width: 800px;
      }
    }

    .map {
      width: 100%;
      height: 35vh;
    }
    .chart-container {
      margin: 1em;
      margin-right:-4em;
      overflow: scroll;
      height: 200px;
      background-color: red;
    }
    .map-container {
      margin: 1em;
    }
   
    .leaflet-container {
      border-radius: 1.5em;
    }
    
  }
</style>
