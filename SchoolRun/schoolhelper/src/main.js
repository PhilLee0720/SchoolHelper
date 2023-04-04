// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import myaxios  from './axios'
import  qs from 'qs'
import store from './store'
Vue.use(ElementUI)
Vue.config.productionTip = false
Vue.prototype.$axios = myaxios;
Vue.prototype.$qs = qs;
Vue.prototype.$bus = new Vue();
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App),
  components: { App },
  template: '<App/>'
})
