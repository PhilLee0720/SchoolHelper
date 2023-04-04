<template>
<div id="personal-center">
    <div id="personal-center-tabs">
        <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#008c8c"
      text-color="white"
      active-text-color="#409eff">
      <el-menu-item index="1">
        <i class="el-icon-user-solid"></i>
        <span slot="title"><router-link to="/personal-center/page">个人主页</router-link></span>
      </el-menu-item>
      <el-menu-item index="2">
        <i class="el-icon-warning"></i>
    <span slot="title"><router-link to="/personal-center/pwmodi">修改密码</router-link></span>
      </el-menu-item>
      <el-menu-item index="3">
        <i class="el-icon-document"></i>
        <span slot="title"><router-link to="/personal-center/comment">我的评论</router-link></span>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-document"></i>
        <span slot="title"><router-link to="/personal-center/per-info">个人信息</router-link></span>
      </el-menu-item>
    </el-menu>
    </div>
    <div id="personal-center-content">
      <router-view></router-view>
    </div>
</div>

</template>

<script>
export default{
    name: 'PersonalCenter',
}
</script>

<style scoped>
#personal-center{
    width: 100%;
    background-color: #008c8c;
}
#personal-center::after{
  display: block;
  content: '';
  clear: both;
}
#personal-center-tabs{
    width: 280px;
    display: inline-block;
    float: left;
}
#personal-center-content{
    width: 1042px;
    display: inline-block;
    float: left;
    margin-left: 150px;
}
a{
  color: white;
}
</style>