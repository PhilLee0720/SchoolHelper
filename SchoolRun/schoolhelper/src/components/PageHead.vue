<template>
<div class="nav">
<div class="line"></div>
<el-menu 
  :default-active="activeIndex2"
  class="el-menu-demo"
  mode="horizontal"
  @select="handleSelect"
  background-color="#008c8c"
  text-color="#fff"
  active-text-color="#409EFF">
  <el-menu-item index="/">接收订单</el-menu-item>
  <el-menu-item index="/system-notice">系统公告</el-menu-item>
  <el-menu-item index="/order-release">发布订单</el-menu-item>
  <el-menu-item index="/myOrder">我的订单</el-menu-item>
  <el-menu-item index="/leave-word">在线留言</el-menu-item>
  <el-menu-item index="/personal-center/page">个人中心</el-menu-item>
  <el-menu-item index="/backend">后台管理</el-menu-item>
  <el-menu-item index="/login">{{this.$store.state.loginOrLogout}}</el-menu-item>
  <el-menu-item index="/register">注册</el-menu-item>
</el-menu>
</div>
</template>

<script>
import { RouterLink } from 'vue-router';

 export default{
    name: "PageHead",
    data() {
        return {
            activeIndex: "1",
            activeIndex2: "1",
        };
    },
    methods: {
        handleSelect(key, keyPath) {
          if(key == '/login'){
            if(this.$store.state.loginOrLogout == "注销"){
              this.logout();
              return;
            }
          }
          this.$router.push(key);
        },
        logout(){
            this.$axios.get("/user/logout").then((response)=>{
                this.res = response.data;
                sessionStorage.removeItem('userId');
                if(this.res.code == '200'){
                    this.$message.success("注销成功");
                    this.$store.commit('changeLoginOrLogout',"登录")
                    this.$router.push('/');
                }else{
                    this.$message.error("注销失败");
                }
            }).catch((response)=>{
                console.log(response);
            })
        }
    },
    components: { RouterLink }
}
</script>

<style scoped>
.nav{
  width: 100%;
  height: 40px;
  margin: 0 auto;
}
.el-menu-demo li{
  width:  6%;
  height: 40px;
  font-size: 10px;
  line-height: 40px;
  margin: 0 10px;
}
.el-menu-demo li:first-child{
  margin-left: 10%;
}
.el-menu-demo li:nth-child(8){
  margin-left: 10%;
}
</style>