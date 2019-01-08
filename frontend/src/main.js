// Импортируем зависимости
import Vue from 'vue'
import App from './App'


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
  render: h => h(App)
}).$mount('#app');
