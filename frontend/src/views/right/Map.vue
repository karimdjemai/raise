
<template>
  <div class="map">
    <h1>Zuid Holland</h1>
    <h3>Last update: {{ last_update }}</h3>
    <raise-map />
  </div>
</template>

<script>
import raiseMap from '../../components/Map.vue'

export default {
  name: 'MapView',
  components: {
    raiseMap
  }
}
</script>
