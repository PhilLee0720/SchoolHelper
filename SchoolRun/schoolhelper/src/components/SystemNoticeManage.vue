<template>
    <div class="notice-manage">
        <div class="notice-add">
            <el-button type="primary" @click="dialogFormVisible = true">发布公告</el-button>

<el-dialog title="添加公告" :visible.sync="dialogFormVisible">
  <el-form :model="form">
    <el-form-item label="公告内容" :label-width="formLabelWidth">
      <el-input v-model="form.noticeContent" autocomplete="off"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="addNotice()">确 定</el-button>
  </div>
</el-dialog>
       </div>
        <el-table
                :data="noticeInfo"
                sytle="width: 100"
                class="leave-word-table"
            >
            
                <el-table-column
                    prop="id"
                    label="公告ID"
                    width="80">
                    </el-table-column>
                    <el-table-column
                    
                    prop="noticeContent"
                    label="公告内容"
                    width="1200">
                    </el-table-column>
                    <el-table-column
                    
                    prop="createTime"
                    label="发布时间"
                    width="180">
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
    name: 'SystemNoticeManage',
    data(){
        return{
            pageSize:10,
            currentPage:1,
            totalR:'',
            res:{
                message:'',
                code:'',
                data:''
            },
            searchBody:{
                content:''
                
            },
            noticeInfo:[
                {
                    id:'',
                    noticeContent:'',
                    creatTime:''
                }
            ],
            dialogFormVisible: false,
            form:{
                noticeContent:'',
            }
        }
    },
    created(){
        this.getNoiceCount();
        this.getNoticePage();
    },
    methods:{
        getNoticePage(){
            this.$axios.get("/notice/page/"+this.currentPage+"/"+this.pageSize).then((response)=>{
                this.res = response.data;
                this.noticeInfo = this.res.data;
            }).catch((response)=>{
                console.log(response);
            })
        },
        getNoiceCount(){
            this.$axios.get("/notice/count").then((response)=>{
                this.res = response.data;
                this.totalR = this.res.data;
            }).catch((response)=>{
                console.log(response);
            })
        },
        addNotice(){
            this.$axios.post("/notice/add",JSON.stringify(this.form)).then((response)=>{
                this.res = response.data;
                if(this.res.code == '200'){
                    this.$message.success(this.res.message);
                    this.dialogFormVisible = false;
                    this.getNoticePage();
                }else{
                    this.$message.error(this.res.message);
                    this.dialogFormVisible = false;
                }
            })
        },
        handleDelete(row){
            this.$axios.delete("/delete/"+row.id).then((response)=>{
                this.res = response.data;
                if(this.res.code == '200'){
                    this.$message.success(this.res.message);
                    this.getNoticePage();
                }else{
                    this.$message.error(this.res.message);
                }
            })
        }
    }
}
</script>

<style lang="less" scoped>
/deep/.el-table th.el-table__cell{
    background-color: #409eff;
}
/deep/.el-table  tr{
    background-color: #008c8c;
    color: black;
}
/deep/.el-table{
    background-color: #008c8c;
    margin-top: 20px;
}
</style>