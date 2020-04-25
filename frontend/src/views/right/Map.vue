
<template>
  <div class="map">
    <p>MapView</p>
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
