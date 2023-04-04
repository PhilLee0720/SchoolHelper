<template>
<div id="personal-page">
    <div id="personal-page-info">
        <div id="personal-page-info-head">
            <div class="personal-page-info-head-avatar">
               
</div>
<div class="logout" @click="logout">注销</div>
<div class="personal-page-info-desc">
    <el-descriptions class="margin-top" title="个人信息" :column="3" :size="size">
    <el-descriptions-item label="用户昵称">{{this.userInfo.userNickName}}</el-descriptions-item>
    <el-descriptions-item label="用户名">{{this.userInfo.userName}}</el-descriptions-item>
    <el-descriptions-item label="性别">{{this.genderRes}}</el-descriptions-item>
    <el-descriptions-item label="学院">{{this.userInfo.institute}}</el-descriptions-item>
    <el-descriptions-item label="专业">{{this.userInfo.speciality}}</el-descriptions-item>
    <el-descriptions-item label="年级">{{this.userInfo.grade}}</el-descriptions-item>
    <el-descriptions-item label="班级">{{this.userInfo.classNumber}}</el-descriptions-item>
    <el-descriptions-item label="联系电话">{{this.userInfo.phoneNumber}}</el-descriptions-item>
  </el-descriptions>
            </div>
        </div>
        <div class="modi-info">
        <router-link to="/personal-center/per-info">
        修改信息
        </router-link>
    </div>
    </div>
</div>
</template>
<script>
export default{
    name: 'PersonalCenterPage',
    data(){
        return{
            res:{
                message:'',
                code:'',
                data:''
            },
            userInfo:{
                userNickName:'',
                userName:'',
                gender:'',
                institute:'',
                speciality:'',
                grade:'',
                classNumber:'',
                phoneNumber:''
            },
            genderRes:'',
        }
    },
    created(){
        this.queryUserInfo();
    },
    methods:{
        queryUserInfo(){
            this.$axios.get("/user/get/"+sessionStorage.getItem("userId")).then((response)=>{
                this.res = response.data;
                this.userInfo = this.res.data;
                if(this.userInfo.gender == 0){
                    this.genderRes = '女';
                }else{
                    this.genderRes = '男'
                }
            }).catch((response)=>{
                console.log(response);
            })
        },
        logout(){
            this.$axios.get("/user/logout").then((response)=>{
                this.res = response.data;
                sessionStorage.removeItem('userId');
                if(this.res.code == '200'){
                    this.$message.success("注销成功");
                    this.$router.push('/');
                }else{
                    this.$message.error("注销失败");
                }
            }).catch((response)=>{
                console.log(response);
            })
        }
    }
}
</script>

<style lang="less" scoped>
.personal-page-info-desc{
    width: 896px;
    height: 200px;
    display: inline-block;
    float: left;
}
/deep/.personal-page-info-desc .el-descriptions__body {
    background-color: #008c8c;
}
#personal-page{
    width: 1042px;
    height: 598px;
}

#personal-page-info{
    width: 1000px;
    height: 400px;
    margin: 0 auto;

}
.logout{
    font-size: 14px;
    color: white;
    position: relative;
    top: 13px;
    left: 356px;
}
#personal-page-info-head{
    width: 1000px;
    height: 200px;
}
.personal-page-info-head-avatar{
    width: 100px;
    height: 199px;
    display: inline-block;
    float: left;
}
.modi-info a{
    color: white;
    text-decoration:underline;
}
</style>