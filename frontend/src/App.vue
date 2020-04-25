<template>
  <md-app id="app">
    <md-app-drawer md-permanent="full" md-fixed>
      <md-toolbar md-elevation="0">
        <md-avatar>
          <img src="./assets/avatar.png" alt="Avatar">
        </md-avatar>
        <h2>Hi, Amber</h2>
        <h3>Zuid, Holland</h3>
      </md-toolbar>
    
      <md-list>
        <md-list-item>
          <span class="md-list-item-text">Home</span>
        </md-list-item>
      
        <md-list-item>
          <span class="md-list-item-text">Resources</span>
        </md-list-item>
      
        <md-list-item>
          <span class="md-list-item-text">Data</span>
        </md-list-item>
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
      
      <RaiseLogo id="raise-logo"/>

    </md-app-drawer>
    
    <md-app-content>
      <router-view />
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
    min-height:100vh;
    width: 180px;
  }
  
  
  .md-app-drawer {
    background-color: #0052aa;
    color: #fff;
    width: 200px;
    padding: 1.8em;
    
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
      
      .md-list-item-text {
        display: inline;
        text-align: center;
        font-weight: bold;
        font-size: 18px;
        margin: 1rem 0;
      }
    }
    
    text-align: center;
    #raise-logo {
      fill: #99badd;
      width: 50px;
      height: auto;
      display: inline-block;
      margin-top: 2em;
      margin-bottom: 0;
    }
  }
  
  
  //#f2f2f2
</style>
