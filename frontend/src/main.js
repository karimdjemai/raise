import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.min.css'

Vue.use(VueMaterial)

import * as VueGoogleMaps from 'vue2-google-maps'
 
Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyCFguorprJJ2cHkl_0C27ROgFObfruntjo',
    libraries: 'places,drawing,visualization',
    region: 'EU',
    language: 'en',
  },
  installComponents: true
})

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
