<template>
  <div class="home">
    <h1>Home</h1>
    <h3>Daily Overview: Netherlands</h3>
    <div class="filter">
      <p>Ventilators</p>
      <md-icon>arrow_drop_down</md-icon>
    </div>
    
    <div class="switch">
      <template v-if="before">
        <img src='../../assets/before.png' alt="test" />
      </template>
      <template v-else>
        <img src='../../assets/after.png' alt="test" />
      </template>
    </div>
  
    <img src='../../assets/map.png' class="map-pic"/>
  </div>
</template>

<script>

export default {
  name: 'Home',
  components: {
  },
  
  computed: {
    before() {
      return this.$store.state.graph_before
    }
  }
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
    
    .map-pic {
      position: absolute;
      bottom: 2em;
      width: 900px;
    }
  }
</style>
