<template>
    <div class="leave-word">
        <div class="leave-word-info">
            <div class="leave-word-info-title">用户留言</div>
            <el-table
                :data="tableData"
                sytle="width: 100"
                class="leave-word-table"
            >
                <el-table-column
                    fixed
                    prop="createTime"
                    label="日期"
                    width="120">
                    </el-table-column>
                    <el-table-column
                    fixed
                    prop="userNickName"
                    label="发布者"
                    width="80">
                    </el-table-column>
                    <el-table-column
                    fixed
                    prop="leaveWordContent"
                    label="留言内容">
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
    <div id="leave-word-act">
        <div class="leave-word-act-title"></div>
        <div class="leave-word-input">
            <el-form :model="leaveWord" ref="leaveWord">
                    <el-input v-model="leaveWord.leaveWordContent" type="textarea" placeholder="请输入留言"></el-input>
                <div class="leave-word-button">
                    <el-button @click="submitLeaveWord(leaveWord)" type="primary">提交我的留言</el-button>
                </div>
            </el-form>
        </div>
    </div>
    </div>
</template>

<script>
export default{
    name: 'LeaveWord',
     data() {
        return {
        pageSize: 4,
          res:{
            message:'',
            code:'',
            data:''
          },
          resT:{
            message:'',
            code:'',
            data:''
          },
          resS:{
            message:'',
            code:'',
            data:''
          },
          leaveWord:{
            leaveWordContent:'',
          },
          tableData: [{
            id:'',
            leaveWordContent:'',
            createTime:'',
            userNickName:''
           
       }],
          currentPage: 1,
          totalR: '',
        }
      },
      created(){
        this.queryTotal();
        this.queryLeaveWord();
      },
      methods:{
        handleCurrentChange(val) {
        this.currentPage = val;
        this.queryLeaveWord();
      },
        queryLeaveWord(){
            this.$axios.get("/leaveword/listPage/"+this.currentPage+"/"+this.pageSize).then((response)=>{
                this.res = response.data;
                this.tableData = this.res.data;
                for(var i = 0;i < 4;i++){
                    this.tableData[i].userNickName = this.res.data[i].releasedUser.userNickName;
                }

            }).catch((response)=>{
                console.log(response);
            })
        },
        queryTotal(){
            this.$axios.get("/leaveword/getTotal").then((response)=>{
                this.resT = response.data;
                this.totalR = this.resT.data;
            }).catch((response)=>{
                console.log(response);
            })
        },
        submitLeaveWord(leaveWord){
            console.log(JSON.stringify(leaveWord));
            this.$axios.post("/leaveword/add",JSON.stringify(leaveWord)).then((response)=>{
                this.resS = response.data;
                if(this.resS.code === 200){
                    this.$alert("发布成功", '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                    this.$message({
                    type: 'info'
                });
                this.leaveWord.leaveWordContent = "";
                this.queryLeaveWord();
              }
            });  
            }
        }).catch((response)=>{
            console.log(response);
        })
        }
    }
}
</script>
<style lang="less" scoped>
.leave-word-info-page-button{
    width: 800px;
    height: 30px;
    border: 1p solid #409eff;
    border-radius: 4px;
    margin: 0 auto;
    margin-bottom: 20px;
}
.leave-word-info-title{
    padding-top: 10px;
    font-size: 20px;
    color: aliceblue;
}
.leave-word{
    width: 1200px;
    background-color: #008c8c;
    margin: 0 auto;
}
/deep/.leave-word-info .el-table {
    margin-top: 30px;
    margin-bottom: 30px;
}
/deep/.leave-word-info .el-table thead{
    color: white;
}
/deep/.leave-word-info .border-radius{
    border-radius: 40px;
}
/deep/.leave-word-info .el-table  tr{
    background-color: #008c8c;
    color: black;
}
/deep/.leave-word-info .el-table th.el-table__cell{
    background-color: #409eff;
}
.el-textarea .el-textarea__inner{
    min-height: 170px;
}
.leave-word-info{
    width: 1200px;
    border: 1px solid #409eff;
    margin: 0 auto;;
    margin-top: 20px;
    border-radius: 40px;
    box-sizing: border-box;
}
#leave-word-act{
    width: 1200px;
    margin: 10px auto;
    border: 1px solid #409eff;
    border-radius: 40px;
}
.leave-word-input{
    margin-top: 40px;
}
.leave-word-act-title{
    width: 30px;
    height: 20px;
}
.leave-word-button{
    margin-top: 60px;
}
</style>
