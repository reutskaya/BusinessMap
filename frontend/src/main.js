// Импортируем зависимости
import Vue from 'vue'
import App from './App'
import router from './router'


//Bootstrap
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
Vue.use(BootstrapVue);

//Yandex-Maps
import YmapPlugin from 'vue-yandex-maps'
Vue.use(YmapPlugin);

// Создаем экземпляр vue
new Vue({
// Определяем селектор для корневого компонента
  el: '#app',
  // передаем шаблон корневому компоненту
  template: '<App/>',
  // Объявляем компоненты, к которым может обращаться корневой компонент;
  components: { App },
  // Передаем в роутер экземпляр Vue
  router
}).$mount('#app');
