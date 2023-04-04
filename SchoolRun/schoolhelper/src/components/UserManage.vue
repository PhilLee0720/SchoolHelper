<template>
    <div class="user-manage">
       <div class="user-search">
        <el-form :inline="true" :model="searchBody" class="demo-form-inline">
  <el-form-item label="查询用户">
    <el-input v-model="searchBody.content" placeholder="请输入用户账户"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="queryUser()">查询</el-button>
  </el-form-item>
</el-form>
       </div>
        <el-table
                :data="userInfo"
                sytle="width: 100"
                class="leave-word-table"
            >
            <el-table-column>
      </el-table-column>
                <el-table-column
                    fixed
                    prop="id"
                    label="用户ID"
                    width="120">
                    </el-table-column>
                    <el-table-column
                    fixed
                    prop="userNickName"
                    label="用户昵称"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    fixed
                    prop="userName"
                    label="用户名"
                    width="80">
                </el-table-column>
                <el-table-column
                    fixed
                    prop="gender"
                    label="性别"
                    width="80">
                    </el-table-column>
                    <el-table-column
                    fixed
                    prop="institute"
                    label="学院"
                    width="120">
                    </el-table-column>
                    <el-table-column
                    fixed
                    prop="speciality"
                    label="专业"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    fixed
                    prop="classNumber"
                    label="班级"
                    width="80">
                    </el-table-column>
                    <el-table-column
                    fixed
                    prop="userAccount"
                    label="用户账户"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    fixed
                    prop="userPassword"
                    label="用户密码"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    fixed
                    prop="role"
                    label="用户角色"
                    width="80">
                    </el-table-column>
                    <el-table-column
                    fixed
                    prop="phoneNumber"
                    label="用户电话号码"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    fixed
                    label="操作"
                    width="150">
                    <template slot-scope="scope">
                        <el-button @click="handleDelte(scope.row)" type="text" size="small" style="color: red;">删除</el-button>
                        <el-button @click="handleCheck(scope.row)" type="text" size="small">提升为管理员</el-button>
                    </template>
                    </el-table-column>
            </el-table>
            <div class="leave-word-info-page-button">
                <el-pagination
                @current-change="handleCurrentChange"
                :current-page.sync="currentPage"
                :page-size="pageSize"
                layout="total,prev, pager, next, jumper"
                :total="totalR">
                </el-pagination>
            </div>
    </div>
    </template>
<script>
export default{
    name: 'UserManage',
    data(){
        return{
            totalR:'',
            pageSize:10,
            currentPage: 1,
            res:{
                message:'',
                code:'',
                data:''
            },
            resR:{
                message:'',
                code:'',
                data:''
            },
            searchBody:{
                content:'',
            },
            userInfo:[
                {
                    id:'',
                    userNickName:'',
                    userName:'',
                    userAccount:'',
                    userPassword:'',
                    gender:'',
                    institute:'',
                    speciality:'',
                    classNumber:'',
                    role:'',
                    phoneNumber:''
                }
            ],
        }
    },
    created(){
        this.queryUserCount();
        this.queryUserList();
    },
    methods:{
        handleCurrentChange(val) {
        this.currentPage = val;
        this.queryUserList();
      },
        queryUserList(){
            this.$axios.get('/user/getUserList/'+this.currentPage+"/"+this.pageSize).then((response)=>{
                this.res = response.data;
                this.userInfo = this.res.data;
                for(var i = 0;i < this.res.data.length;i++){
                    this.userInfo[i].id = this.res.data[i].id;
                    this.userInfo[i].userNickName = this.res.data[i].userNickName;
                    this.userInfo[i].userName = this.res.data[i].userName;
                    this.userInfo[i].userAccount = this.res.data[i].userAccount;
                    this.userInfo[i].userPassword = this.res.data[i].userPassword;
                    this.userInfo[i].gender = this.res.data[i].gender;
                    this.userInfo[i].institute = this.res.data[i].institute;
                    this.userInfo[i].speciality = this.res.data[i].speciality;
                    this.userInfo[i].classNumber = this.res.data[i].classNumber;
                    this.userInfo[i].role = this.res.data[i].role;
                    this.userInfo[i].phoneNumber = this.res.data[i].phoneNumber;
                }
            }).catch((response)=>{
                console.log(response);
            })
        },
        queryUserCount(){
            this.$axios.get("/user/count").then((response)=>{
                this.resR = response.data;
                this.totalR = this.resR.data;
            }).catch((response)=>{
                console.log(response);
            })
        },
        queryUser(){
            this.$axios.post("/user/query",JSON.stringify(this.searchBody)).then((response)=>{
                this.res = response.data;
                this.userInfo = this.res.data;
                for(var i = 0;i < this.res.data.length;i++){
                    this.userInfo[i].id = this.res.data[i].id;
                    this.userInfo[i].userNickName = this.res.data[i].userNickName;
                    this.userInfo[i].userName = this.res.data[i].userName;
                    this.userInfo[i].userAccount = this.res.data[i].userAccount;
                    this.userInfo[i].userPassword = this.res.data[i].userPassword;
                    this.userInfo[i].gender = this.res.data[i].gender;
                    this.userInfo[i].institute = this.res.data[i].institute;
                    this.userInfo[i].speciality = this.res.data[i].speciality;
                    this.userInfo[i].classNumber = this.res.data[i].classNumber;
                    this.userInfo[i].role = this.res.data[i].role;
                    this.userInfo[i].phoneNumber = this.res.data[i].phoneNumber;
                }
            }).catch((response)=>{
                console.log(response);
            })     
        },
        handleDelte(row){
            this.$axios.delete("/user/delete/"+row.id).then((response)=>{
                this.res = response.data;
                if(this.res.code == '200'){
                    this.$message.success("删除成功");
                    this.queryUserList();
                }else{
                    this.$message.success("删除失败");
                }
            }).catch((response)=>{
                console.log(response);
            })
        },
        handleCheck(row){
            this.$axios.put("/user/check/"+row.id).then((response)=>{
                this.res = response.data;
                if(this.res.code == '200'){
                    this.$message.success("已审核");
                    this.queryUserList();
                }else{
                    this.$message.error("审核失败");
                }
            })
        }
    }
}
</script>

<style lang="less" scoped>
.user-manage{
    width: 100%;
    height: 100%;
}
.user-search{
    margin-left: 415px;
}
/deep/.el-table th.el-table__cell{
    background-color: #409eff;
}
/deep/.el-table  tr{
    background-color: #008c8c;
    color: black;
}
/deep/.el-table{
    background-color: #008c8c;
}
</style>