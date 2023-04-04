<template>
    <div class="comment-manage">
        <div class="comment-search">
        <el-form :inline="true" :model="searchBody" class="demo-form-inline">
  <el-form-item label="查询评论">
    <el-input v-model="searchBody.content" placeholder="请输入订单编号"></el-input>
    
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="queryCommentLike()">查询</el-button>
  </el-form-item>
</el-form>
       </div>
        <el-table
                :data="commentInfo"
                sytle="width: 100"
                class="leave-word-table"
            >
            
                <el-table-column
                    prop="id"
                    label="评论ID"
                    width="80">
                    </el-table-column>
                    <el-table-column
                    
                    prop="commentContent"
                    label="评论内容"
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
    name: 'CommentManage',
    data(){
        return{
            pageSize:10,
            currentPage:1,
            totalR:'',
            searchBody:{
                content:''
            },
            res:{
                message:'',
                code:'',
                data:''
            },
            commentInfo:[
                {
                    id:'',
                    commentContent:'',
                    createTime:'',
                    userAccount:''
                }
            ],
        }
    },
    created(){
        this.queryCommentNumber();
        this.queryCommentPage();
    },
    methods:{
        handleCurrentChange(val) {
        this.currentPage = val;
        this.queryCommentPage();
      },
        queryCommentPage(){
            this.$axios.get("/comment/page/"+this.currentPage+"/"+this.pageSize).then((response)=>{
                this.res = response.data;
                this.commentInfo = this.res.data;
                for(var i = 0;i < res.data.length;i++){
                    this.commentInfo[i].id = this.res.data[i].id;
                    this.commentInfo[i].commentContent = this.res.data[i].commentContent;
                    this.commentInfo[i].createTime = this.res.data[i].createTime;
                    this.commentInfo[i].userAccount = this.res.data[i].releasedUser.userAccount;
                }
                
            }).catch((response)=>{
                console.log(response);
            })
        },
        queryCommentNumber(){
            this.$axios.get("/comment/count").then((response)=>{
                this.res = response.data;
                this.totalR  = this.res.data;
            }).catch((response)=>{
                console.log(response);
            })
        },
        queryCommentLike(){
            this.$axios.post("/comment/query",JSON.stringify(this.searchBody)).then((response)=>{
                this.res = response.data;
                this.commentInfo = this.res.data;
                for(var i = 0;i < res.data.length;i++){
                    this.commentInfo[i].id = this.res.data[i].id;
                    this.commentInfo[i].commentContent = this.res.data[i].commentContent;
                    this.commentInfo[i].createTime = this.res.data[i].createTime;
                    this.commentInfo[i].userAccount = this.res.data[i].releasedUser.userAccount;
                }
            })
        },
        handleDelete(row){
            this.$axios.delete("/comment/delete/"+row.id).then((response)=>{
                this.res = response.data;
                if(this.res.code == '200'){
                    this.$message.success(this.res.message);
                    this.queryCommentPage();
                }else{
                    this.$message.error(this.res.message);
                }
            }).catch((response)=>{
                console.log(response);
            })
        }
    }
}
</script>

<style lang="less" scoped>
.comment-manage{
    width: 100%;
    height: 100%;
   
}
.comment-search{
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