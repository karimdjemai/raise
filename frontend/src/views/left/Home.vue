<template>
  <div class="home">
    <h1>Home</h1>
    <h3 class="">Daily Overview: {{ selectedPlace }} </h3>         

    <div class="filter">
      <p>Ventilators</p>
      <md-icon>arrow_drop_down</md-icon>
    </div>
    <div class="chart-container">
      <template v-if="before">
        <img src='../../assets/before.png' alt="test" />
      </template>
      <template v-else>
        <img src='../../assets/after.png' alt="test" />
      </template>
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
    .filter {
      background-color: #fff;
      width: 30%;
      padding: 0.3rem;
      text-align: center;
      border-radius: 1em;
      color: #B3B3B3 !important;
      display: flex;
      justify-content: center;
      align-items: baseline;
      
      p {
        margin: 0;
      }
      
      .md-icon {
        padding: 0;
        margin: auto 0
      }
    }
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
    }
    .map-container {
      margin: 1em;
    }
   
    .leaflet-container {
      border-radius: 1.5em 0 0 1.5em;
    }
    
  }
</style>
