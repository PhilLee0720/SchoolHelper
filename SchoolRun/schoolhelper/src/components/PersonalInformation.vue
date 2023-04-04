<template>
<div id="personal-info">
    <h1 style="color: white;">个人信息</h1>
    <div class="personal-info-form">
        <el-form  ref="form" :model="form" label-width="80px">
  <el-form-item label="用户昵称">
    <el-input v-model="form.userNickName" placeholder="请输入用户昵称" ></el-input>
  </el-form-item>
  <el-form-item label="用户姓名">
    <el-input v-model="form.userName" placeholder="请输入价格"></el-input>
  </el-form-item>
  <el-form-item label="性别">
    <el-radio v-model="form.gender" label="1" value="1">男</el-radio>
  <el-radio v-model="form.gender" label="2" value="2">女</el-radio>
  </el-form-item>
  <el-form-item label="学院"> 
    <el-input v-model="form.institute" placeholder="请输入学院"></el-input>
  </el-form-item>
  <el-form-item label="专业"> 
    <el-input v-model="form.speciality" placeholder="请输入专业"></el-input>
  </el-form-item>
  <el-form-item label="年级">
    <el-input v-model="form.grade" placeholder="请输入年级"></el-input>
  </el-form-item>
  <el-form-item label="班级">
    <el-input v-model="form.classNumber" placeholder="请输入班级"></el-input>
  </el-form-item>
  <el-form-item label="联系电话">
    <el-input v-model="form.phoneNumber" placeholder="请输入联系电话"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button class="button" type="primary" @click="saveUser">保存</el-button>
    <el-button>取消</el-button>
  </el-form-item>
</el-form>
    </div>
</div>
</template>

<script>
export default{
    name: 'PersonalInfomation',
    data() {
      return {
        form: {
          userNickName: '',
          userName:'',
          gender: '',
          institute:'',
          grade:'',
          speciality:'',
          classNumber:'',
          phoneNumber: ''
        },
        res:{
          message:'',
          code:'',
          data:''
        },
        resU:{
          message:'',
          code:'',
          data:''
        }
      }
    },
    created(){
      this.queryUserInfo();
    },
    methods:{
      queryUserInfo(){
        this.$axios.get("/user/get/"+sessionStorage.getItem("userId")).then((response)=>{
          this.res = response.data;
          this.form.userNickName = this.res.data.userNickName;
          this.form.userName = this.res.data.userName;
          this.form.institute = this.res.data.institute;
          this.form.speciality = this.res.data.speciality;
          this.form.gender = this.res.data.gender;
          this.form.grade = this.res.data.grade;
          this.form.institute = this.form.institute;
          this.form.classNumber = this.res.data.classNumber;
          this.form.phoneNumber = this.res.data.phoneNumber;

        })
      },
      saveUser(){
        this.$axios.post("/user/update/"+sessionStorage.getItem("userId"),JSON.stringify(this.form)).then((response)=>{
          this.resU = response.data;
          if(this.resU.code == "200"){
            this.$message({
                        message: this.resU.message,
                        type: 'success'
                    });
          }else{
            this.$message.error(this.resU.message);
          }
        })
      }
    }
}
</script>

<style lang="less" scoped>
#personal-info{
    width: 1000px;
    border: 1px solid red;
    margin: 0 auto;
}
.personal-info-form{
    text-align: left;
}
.button{
    margin-left: 350px;
}
</style>