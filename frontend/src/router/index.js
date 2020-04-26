import Vue from 'vue'
import VueRouter from 'vue-router'
import lHome from '../views/left/Home.vue'
import rHome from '../views/right/Home.vue'
import lMap from '../views/left/Map.vue'
import rMap from '../views/right/Map.vue'
import lData from '../views/left/Data'
import rDataDemand from '../views/right/DataDemand'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    components: {
      left_page: lHome,
      right_page: rHome
    }
  },
  {
    path: '/Map',
    name: 'Map',
    components: {
      left_page: lMap,
      right_page: rMap
    }
  },
  {
    path: '/data/demand',
    name: 'Data',
    components: {
      left_page: lData,
      right_page: rDataDemand
    }
  },
  {
    path: '/data/availability',
    name: 'Data',
    components: {
      left_page: lData,
      right_page: rDataDemand
    }
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/left/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
