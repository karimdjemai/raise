<template>
  <div class="home">
    <h1>Home</h1>
    <h3>Daily Overview: {{ selectedPlace }} </h3>
    <div class="chart-container">
      <BarChart :datasets="chartData" :labels="chartLabels" height="200px"/>
    </div>
    
    <div class="map-container">
      <raise-map class="map" />
    </div>
  </div>
  
</template>

<script>
// Leaflet map variant
import raiseMap from '../../components/MapLeaflet.vue'
import BarChart from '../../components/BarChart'

export default {
  name: 'Home',
  components: {
    BarChart,
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
    chartData() {
      return [
        {
          label: 'Demand',
          backgroundColor: '#71DBC4',
          data: [40, 30, 40, 80, 30, 55]
        },
        {
          label: 'Availability',
          backgroundColor: '#0052aa',
          data: [40, 30, 20, 70, 30, 60]
        },
        
      ]
    },
    chartLabels() {
      return [
        'Groningen',
        'Friesland',
        'Drenthe',
        'Overjissel',
        'Flevoland',
        'Gelderland'
      ]
    }
  },
}
</script>
<style lang="scss">
  .home {
    height: 100%;
    
    .map-container {
      margin-top: 3em;
      .map {
        width: 100%;
        height: 35vh;
        
        .leaflet-container {
          border-radius: 1.5em;
        }
      }
    }
    
    .chart-container {
      margin: 1em -4em 1em 0em;
      overflow: scroll;
    }
  }
</style>
