<template>
  <div class="map-left">
    <h1>Map</h1>
    <!--<ul>
      <li style="list-style-type:none;"><h2>Scale</h2></li>
      <li style="list-style-type:none;"><h2>Location</h2></li>
      <li style="list-style-type:none;"><h2>Resource</h2></li>
      <li style="list-style-type:none;"><h2>Risk Group</h2></li>
      <li style="list-style-type:none;"><h2>View</h2></li>
    </ul-->
    
    <h3>Set filters to view recources in an area.</h3>
  
    <md-list class="filters">
      <md-list-item>
        <span class="desc">Scale</span>
        <md-field>
          <md-input placeholder="Choose option..."></md-input>
        </md-field>
      </md-list-item>
      <md-list-item>
        <span class="desc">Location</span>
        <md-field>
          <md-input placeholder="Type here..."></md-input>
        </md-field>
      </md-list-item>
      <md-list-item>
        <span class="desc">Resource</span>
        <md-field>
          <md-input placeholder="Choose option..."></md-input>
        </md-field>
      </md-list-item>
      <md-list-item>
        <span class="desc">Risk</span>
        <md-field>
          <md-input placeholder="Choose option..."></md-input>
        </md-field>
      </md-list-item>
      <md-list-item>
        <span class="desc">View</span>
        <md-field>
          <md-input placeholder="Type here..."></md-input>
        </md-field>
      </md-list-item>
    </md-list>
  </div>
</template>

<script>

export default {
  name: 'Home',
  components: {
  }
}
</script>
<style lang="scss">
  .map-left {
    h3 {
      color: #0052aa;
    }
    
    .md-list {
      .md-list-item {
        background-color: #fff;
        margin: 1em;
        border-radius: 1em;
        
        .md-list-item-content {
          min-height: 0;
          display: flex;
          justify-content: space-between;
          
          .desc {
            display: inline-block;
            width: 50%;
            font-weight: bold;
            color: #54bbc1;
          }
          
          .md-field {
            margin: 0;
            padding: 0;
            min-height: 0;
            width: 50%;
            
            .md-input {
              font-size: 10pt;
              color: #b3b3b3;
              font-weight: 500;
            }
          }
        }
      }
    }
    
  }
</style>
