<template>
    <div class="backend">
        <div class="left">
            <div id="personal-center-tabs">
        <el-menu
      default-active="1"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#008c8c"
      text-color="white"
      active-text-color="#409eff">
      <el-menu-item index="1">
        <i class="el-icon-user-solid"></i>
        <span slot="title"><router-link to="/backend/user-manage">用户管理</router-link></span>
      </el-menu-item>
      <el-menu-item index="2">
        <i class="el-icon-warning"></i>
    <span slot="title"><router-link to="/backend/order-manage">订单管理</router-link></span>
      </el-menu-item>
      <el-menu-item index="3">
        <i class="el-icon-document"></i>
        <span slot="title"><router-link to="/backend/comment-manage">评论管理</router-link></span>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-document"></i>
        <span slot="title"><router-link to="/backend/notice-manage">公告管理</router-link></span>
      </el-menu-item>
      <el-menu-item index="5">
        <i class="el-icon-document"></i>
        <span slot="title"><router-link to="/backend/leaveword-manage">留言管理</router-link></span>
      </el-menu-item>
      <el-menu-item index="6">
        <i class="el-icon-document"></i>
        <span slot="title"><router-link to="/backend/category-manage">分类管理</router-link></span>
      </el-menu-item>
    </el-menu>
    </div>
        </div>
        <div class="right">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
export default{
    name:'backend'
}
</script>

<style scoped>
.backend{
    border: 1px solid red;
    width: 100%;
    height: 100%;
    text-align: left;
}
.left{
    border: 1px solid blue;
    width: 15%;
    height: 100%;
    text-align: center;
    display: inline-block;
    float: left;
}
.right{
  display: inline-block;
  width: 84%;
  height: 100%;
  float: left;
  margin-left: 10px;
}
</style>