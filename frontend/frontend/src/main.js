// Импортируем зависимости
import Vue from 'vue'
import App from './App'
import BootstrapVue from 'bootstrap-vue'
import VueRouter from 'vue-router'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import About from './components/About'
import Header from './components/Header'
import page404 from './components/page404'

Vue.use(VueRouter);
Vue.use(BootstrapVue);
// инициализируем роуты
const routes = [
  { path: '/about', component: About },
  { path: '/', component: Header },
  { path: "*", component: page404}
];
const router = new VueRouter({
  routes,
  mode: 'history'
});

// register globally
import YmapPlugin from 'vue-yandex-maps'
Vue.use(YmapPlugin);

// or for a single instance
import { yandexMap, ymapMarker } from 'vue-yandex-maps'
new Vue({
  components: { yandexMap, ymapMarker }
});

// Создаем экземпляр vue
new Vue({
// Определяем селектор для корневого компонента
  el: '#app',
  // передаем шаблон корневому компаненту
  template: '<App/>',
  // Объявляем компоненты, к которым может обращаться корневой компонент;
  components: { App },
  // Передаем в роутер экземпляр Vue
  router: router
}).$mount('#app');
