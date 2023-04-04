<template>
<div id="order-a-s">
    <h1 style="color: white;">订单接收情况</h1>
    <div class="order-a-user-info">
        <span class="title">接收当前订单用户信息:</span>
    <el-descriptions class="margin-top" :column="3" :size="small" >
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        用户昵称
      </template>
      {{user.userNickName}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        用户名
      </template>
      {{user.userName}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        手机号
      </template>
      {{user.phoneNumber}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        学院
      </template>
      {{user.institute}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-office-building"></i>
        专业
      </template>
      {{user.speciality}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-office-building"></i>
        年级
      </template>
      {{user.grade}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-office-building"></i>
      </template>
      {{user.classNumber}}
    </el-descriptions-item>
  </el-descriptions>
    </div>
  <div class="title">订单完成情况:</div>
  <div id="o-f-situation">
    <span class="text">{{text}}</span>
  </div>
  <div class="comment-area">
    <span class="comment-area-title">
      评论当前用户:
    </span>
    <el-form>
      <el-input  v-model="comment.commentContent" placeholder="请输入评论">
        
      </el-input>
      <el-button  class="button" type="primary" @click="addComment">提交</el-button>
    </el-form>
  </div>
</div>
</template>

<script>
export default{
    name: 'OrderAcceptedSituation',
    data(){
      return{
        comment:{
          commentContent: ''
        },
        text:  '',
        user:{
          userId:'',
          userName:'',
          userNickName:'',
          orderSerial:'',
          institute:'',
          speciality:'',
          grade:'',
          phoneNumber:'',
          classNumber:'',
        },
        orderTransActionState:'',
        res:{
          message:'',
          code:'',
          data:''
        },
        resOTA:{
          message:'',
          code:'',
          data:''
        },
        resC:{
          message:'',
          code:'',
          data:''
        },
        orderId:'',
        
      }
    },
    created(){
      this.queryUserInfo();
      this.queryTransState();
    },
    methods:{
      queryUserInfo(){
        this.$axios.get("/user/getByOrderId/"+this.$store.state.orderId).then((response)=>{
          this.res = response.data;
          this.user.userId = this.res.data.id;
          this.user.userName = this.res.data.userName;
          this.user.userNickName = this.res.data.userNickName;
          this.user.institute = this.res.data.institute;
          this.user.speciality = this.res.data.speciality;
          this.user.grade = this.res.data.grade;
          this.user.phoneNumber = this.res.data.phoneNumber;
          this.user.classNumber = this.res.data.classNumber;
        })
      },
      queryTransState(){
        this.$axios.get("/order/getTransState/"+this.$store.state.orderId).then((response)=>{
          this.resOTA = response.data;
          this.orderTransActionState = this.resOTA.data;
          if(this.orderTransActionState == 0){
            this.text = "未接收";
          }else if(this.orderTransActionState == 1){
            this.text = "已接收"
          }else if(this.orderTransActionState == 2){
            this.text = "已完成"
          }
        })
      },
      addComment(){
        this.$axios.post("/comment/addAA/"+this.user.userId+"/"+this.$store.state.orderId,JSON.stringify(this.comment)).then((response)=>{
          this.resC = response.data;
          if(this.resC.code == "200"){
            this.$message.success(this.resC.message);
          }else{
            this.$message.error(this.resC.message);
          }
        }).catch((response)=>{
          console.log(response);
        })
      }

    }
}
</script>

<style lang="less" scoped>
.my-label {
    background: #E1F3D8;
  }

  .my-content {
    background: #FDE2E2;
  }
#order-a-s{
    width: 1000px;
    height: 600px;
    margin: 10px auto;
}
.order-a-user-info{
    width: 100%;
    text-align: left;
    
}
.order-a-user-info .title{
    color: #409eff;
    font-size: 20px;
}
/deep/.order-a-user-info .el-descriptions__body{
    background-color: #008c8c;
    color: white;
    margin-top: 20px;
}
#o-f-situation{
  height: 40px;
  border: 1px solid #409eff;
  background-color: lightgreen;
}
#o-f-situation .text{
  font-size: 40px;
  line-height: 40px;
}
.comment-area{  
  text-align: left;
  height: 200px;
  margin-top: 15px;
}
.comment-area-title{
  color: #409eff;
    font-size: 20px;
}
.button{
  margin-top: 5px;
}
.title{
  text-align: left;
  color: #409eff;
  font-size: 20px;
}
</style>