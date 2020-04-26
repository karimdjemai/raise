
<template>
  <div class="map">
    <h1>Zuid Holland</h1>
    <h3>Last update: {{ last_update }}</h3>
    <raise-map />
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
