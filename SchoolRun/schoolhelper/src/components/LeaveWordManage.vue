<template>
    <div class="leaveword-manage">
        <div class="leaveword-search">
        <el-form :inline="true" :model="searchBody" class="demo-form-inline">
  <el-form-item label="查询留言">
    <el-input v-model="searchBody.content" ></el-input>
    
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="queryCommentLike()">查询</el-button>
  </el-form-item>
</el-form>
       </div>
        <el-table
                :data="leavewordInfo"
                sytle="width: 100"
                class="leave-word-table"
            >
            
                <el-table-column
                    prop="id"
                    label="留言ID"
                    width="80">
                    </el-table-column>
                    <el-table-column
                    
                    prop="leaveWordContent"
                    label="留言内容"
                    width="500">
                    </el-table-column>
                    <el-table-column
                    
                    prop="createTime"
                    label="发布时间"
                    width="180">
                </el-table-column>
                <el-table-column
                   
                    prop="userAccount"
                    label="发布者账户"
                    width="250">
                    </el-table-column>
                    <el-table-column
                    
                    label="操作"
                    width="150">
                    <template slot-scope="scope">
                        <el-button @click="handleDelete(scope.row)" type="text" size="small" style="color: red;">删除</el-button>
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
    name: 'LeaveWordManage',
    data(){
        return{
            pageSize:10,
            currentPage:1,
            totalR:'',
            searchBody:{
                content:'',
            },
            res:{
                message:'',
                code:'',
                data:''
            },
            leavewordInfo:[{
                id:'',
                leaveWordContent:'',
                createTime:'',
                userAccount:'',
            }]
        }
    },
    created(){
        this.getLeaveWordPage();
        this.getLeaveWordCount();
    },
    methods:{
        handleCurrentChange(val) {
        this.currentPage = val;
        this.queryCommentPage();
      },
        getLeaveWordPage(){
            this.$axios.get("/leaveword/listPage/"+this.currentPage+"/"+this.pageSize).then((response)=>{
                this.res = response.data;
                this.leavewordInfo = this.res.data;
                for(var i = 0;i < this.res.data.length;i++){
                    this.leavewordInfo[i].userAccount = this.res.data[i].releasedUser.userAccount;
                }
            })
        },
        getLeaveWordCount(){
            this.$axios.get("/leaveword/getTotal").then((response)=>{
                this.res = response.data;
                this.totalR = this.res.data;
            }).catch((response)=>{
                console.log(response);   
            })
        },
        handleDelete(row){
            this.$axios.delete("/leaveword/delete/"+row.id).then((response)=>{
                this.res = response.data;
                if(this.res.code == '200'){
                    this.$message.success(this.res.message);
                    this.getLeaveWordPage();
                }else{
                    this.$message.error(this.res.message);
                }
            })
            
        }
    }
    
}
</script>

<style lang="less" scoped>
.leaveword-search{
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