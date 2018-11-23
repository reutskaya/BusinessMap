import Vue from 'vue'
import VueRouter from 'vue-router'
import Header from './components/Header'
import Map from './components/Map'
import page404 from './components/page404'

Vue.use(VueRouter);

export default new VueRouter({
  mode: 'history', //Убираем решетку из URLa
  routes: [
    {
      path: '/',
      name: 'home',
      component: Header
    },

    {
      path:'/map',
      name: 'map',
      component: Map
    },

    {
      path:'/head',
      name: 'head',
      component: Header
    },

    //Любой левый URL кидает на Home
    { path: "*", component: page404}
  ],
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { x: 0, y: 0 }
    }
  }
})
