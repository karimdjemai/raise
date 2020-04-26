import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.min.css'

Vue.use(VueMaterial)

// map part
// Google Maps
// import * as VueGoogleMaps from 'vue2-google-maps'
 
// Vue.use(VueGoogleMaps, {
//   load: {
//     key: 'AIzaSyCFguorprJJ2cHkl_0C27ROgFObfruntjo',
//     libraries: 'places,drawing,visualization',
//     region: 'EU',
//     language: 'en',
//   },
//   installComponents: true
// })

// Leaflet/OSM alternative 
import 'leaflet/dist/leaflet.css';

// start fix for missing markers
import { Icon } from 'leaflet';

delete Icon.Default.prototype._getIconUrl;
Icon.Default.mergeOptions({
  iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
  iconUrl: require('leaflet/dist/images/marker-icon.png'),
  shadowUrl: require('leaflet/dist/images/marker-shadow.png'),
});
// end fix for missing markers

// end map part

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
