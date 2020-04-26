<template>
  <md-app id="app">
    <md-app-drawer md-permanent="full">
      <md-toolbar md-elevation="0">
        <md-avatar>
          <img src="./assets/avatar.png" alt="Avatar">
        </md-avatar>
        <h2>Hi, Amber</h2>
        <h3>Zuid, Holland</h3>
      </md-toolbar>
      
      <md-list>
        <router-link to="/" tag="md-list-item">
          <span class="md-list-item-text">Home</span>
        </router-link>
      
        <router-link to="/map" tag="md-list-item">
          <span class="md-list-item-text">Map</span>
        </router-link>
  
        <router-link to="/data/demand" tag="md-list-item" :class="{
          'router-link-exact-active': $router.currentRoute.name.includes('Data')
        }">
          <span class="md-list-item-text">Data</span>
        </router-link>
        
        <md-list-item>
          <span class="md-list-item-text">Track</span>
        </md-list-item>
      
        <md-list-item>
          <span class="md-list-item-text">Messages</span>
        </md-list-item>
        
        <md-list-item>
          <span class="md-list-item-text">Settings</span>
        </md-list-item>
      </md-list>
      
      <div>
        <RaiseLogo id="raise-logo"/>
      </div>
    </md-app-drawer>
    
    <md-app-content>
      <div class="left_view">
        <div class="left_content">
          <router-view name="left_page"/>
        </div>
      
        <div class="right_view">
          <router-view name="right_page"/>
        </div>
      </div>
    </md-app-content>
  </md-app>
</template>

<script>
  import RaiseLogo from './assets/raise_logo.svg'
  
  export default {
    name: 'App',
    
    components: {
      RaiseLogo
    }
  }
</script>



<style lang="scss">
  #app {
    font-family: Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    height:100vh; //todo better way
    padding: 0 !important;
  }
  
  
  .md-app-drawer {
    background-color: #0052aa;
    color: #f2f2f2;
    padding: 1.8em;
    width: 180px !important;
    
    
    .md-toolbar {
      align-content: center;
      
      .md-avatar {
        width: 75px;
        height: auto;
        margin-bottom: 0.8em;
      }
      
      h2 {
        font-size: 0.9rem;
        margin: auto;
        font-weight: bold;
      }
      
      h3 {
        font-size: 0.6rem;
        margin: auto;
        font-weight: bold;
      }
    }
    
    .md-list {
      margin-top: 2em;
      
      .md-list-item-content {
        min-height: 0;
        padding: 1em 1.6em;
      }
      
      .md-list-item {
        margin: {
          top:0.5rem;
          bottom:0.5rem;
          left: 0;
        }
        
        //width: 145%; //todo: later
      }
      
      .router-link-exact-active {
        .md-list-item-text {
        background-color: #f2f2f2;
          color: #0052aa;
        }
      }
      
      .md-list-item-text {
        color: #f2f2f2;
        display: inline;
        text-align: center;
        padding-left: 0rem;
        font-weight: bold;
        font-size: 13pt;
        border-radius: 0.8rem;
        vertical-align: center;
      }
    }
    
    text-align: center;
    #raise-logo {
      fill: #99badd;
      width: 50px;
      height: auto;
      display: inline;
      margin-top: 2em;
      margin-bottom: 0;
      position: relative;
    }
  }
  
  .md-app-container {
    background-color: black;
  }
  
  .md-app-content {
    padding: 0 !important;
    box-sizing: border-box;
    background-color: #0052aa;
    border: none !important;
    
    .left_view {
      position: absolute;
      padding: 3em 4em;
      border-radius: 1.5em;
      background-color: #f2f2f2;
      width: 100%;
      height: 100%;
      display: flex;
      flex-direction: revert;
      
      
      .left_content {
        position: relative;
        flex-grow: 1;
        margin-right: 4em;
        h1 {
          color: #0052aa;
          font-size: 35pt;
        }
        h2, h3 {
          color: #0052aa;
        }
      }
      
      .right_view {
        position: relative;
        margin: {
          top: -3em;
          right: -4em;
          bottom: -3em;
        }
        
        padding: 3em 4em;
        border-radius: 1.5em 0 0 1.5em;
        background-color: #54bbc1;
        width: 65%;
        
        h1 {
          color: #0052aa;
          //margin: 1.5em;
          font-size: 35pt;
        }
      }
    }
  }
  .vue-map-container {
      border-radius: 1.5em 1.5em;
  }
  
  .rounded-corners-left {
    border-radius: 1em 0 0 1em;
  }
  .rounded-corners-right {
    border-radius: 0 1em 1em 0;
  }

  /* fix geosearch search glasses */
  .leaflet-control-geosearch a.leaflet-bar-part:after {
    top: 6px;
    left: 6px;
    height: 12px;
    width: 12px;
    border-radius: 50%;
    border: 2px solid #555;
  }
  .leaflet-control-geosearch a.leaflet-bar-part:before {
    top: 18px;
    left: 15px;
    width: 7px;
    border-top: 2px solid #555;
    transform: rotateZ(45deg);
  }

</style>
