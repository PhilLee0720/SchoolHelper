<template>
  <div id="app">
    <div class="head">
      <el-image
      style="width: 100%; height: 120px"
      :src="url"
      :fit="fit"></el-image>
    </div>
    <ComponentPH/>
    <router-view/>
  </div>
</template>

<script>
import router from './router';
import PageHead from './components/PageHead.vue';
import login from './components/Login.vue';
import Register from  './components/Register.vue';
export default {
  name: 'App',
  router, 
  components: { 
    router, 
    ComponentPH: PageHead,
    ComponentL: login,
    ComponentR: Register
  },
    data(){
      return{
        url: require('@/assets/IMG_5376.png')
    }
  }
}

</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-color: #008c8c;
  width: 100%;
  height:960px;
}
#app a{
  text-decoration: none;
}
.head{
  height: 120px;
  margin-top: -8px;
  box-sizing: border-box;
}
</style>
