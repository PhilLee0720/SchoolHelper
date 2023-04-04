<template>
<div id="login">
  <div class="login-title">
    登录
  </div>
    <div class="login-item">
<el-form  :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item  label="账号"  prop="account">
    <el-input type="text" v-model="ruleForm.account" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="密码" prop="pass">
    <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
  <div></div>
</el-form>
    </div>
</div>
</template>

<script>
export default{
    name: 'Login',
    data() {
      var validateAccount = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入账号'));
        } else {
          callback();
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          account: '',
          pass: ''
        },
        res: {
          message: '',
          code: '',
          data: ''
        },
        rules: {
          pass: [
            { validator: validateAccount, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass, trigger: 'blur' }
          ]
        }
      };
    },
    created(){
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // alert('submit!');
            this.$axios.get("/user/login?userAccount="+this.ruleForm.account+"&userPassword="+this.ruleForm.pass).then((response)=>{
              this.res = response.data;
              sessionStorage.setItem('userId',this.res.data);
              this.$store.commit('changeLoginOrLogout',"注销");
              console.log(this.$store.state.loginOrLogout);
                if(this.res.code == '200'){
                    this.$alert(this.res.message.toString(), '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                    this.$message({
                    type: 'info'
                });
              }
            });  
            this.$router.push('/personal-center/page');
                  }else{
                    this.$alert(this.res.message.toString(), '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                    this.$message({
                    type: 'info'
                });
          }
        })
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    },
      
    }

</script>

<style scoped>
.login-title{
  font-size: 50px;
  color: whitesmoke;
}
#login{
    width: 600px;
    height: 600px;
    box-sizing: border-box;
    width: 0 auto;
    display: inline-block;
}
.login-item{
    width: 400px;
    height: 300px;
    display: inline-block;
    margin: 0 auto;
    margin-top: 50px;
    margin-left: -90px;
    box-sizing: border-box;
}
.demo-ruleForm{
    color: blanchedalmond;
}
</style>