import Vue from 'vue'
import VueRouter from 'vue-router'
import wHome from '../views/left/Home.vue'
import tHome from '../views/right/Home.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    components: {
      left_page: wHome,
      right_page: tHome
    }
  },
  // {
  //   path: '/',
  //   name: 'Map',
  //   components: {
  //     white_page: wMap,
  //     turky_page: tMap
  //   }
  // },
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
