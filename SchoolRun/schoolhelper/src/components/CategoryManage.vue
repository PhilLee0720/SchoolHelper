<template>
    <div class="category-manage">
        <el-button type="primary" @click="dialogFormVisible = true">新增分类</el-button>
        <el-dialog title="添加分类" :visible.sync="dialogFormVisible">
  <el-form :model="form">
    <el-form-item label="分类名称" :label-width="formLabelWidth">
      <el-input v-model="form.categoryName" autocomplete="off"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="addCategory()" >确 定</el-button>
  </div>
</el-dialog>
        <el-table
                :data="categoryInfo"
                sytle="width: 100"
                class="leave-word-table"
            >
            
                <el-table-column
                    prop="id"
                    label="分类ID"
                    width="80">
                    </el-table-column>
                    <el-table-column
                    
                    prop="categoryName"
                    label="分类名称"
                    width="100">
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
    </div>
</template>

<script>
export default{
    name: 'CategoryManage',
    data(){
        return{
            res:{
                message:'',
                code:'',
                data:''
            },
            form:{
                categoryName:'',
            },
            dialogFormVisible: false,
            categoryInfo:[
                {
                    id:'',
                    categoryName:'',
                    createTime:''
                }
            ]
        }
    },
    created(){
        this.getCategoryInfo();
    },
    methods:{
        getCategoryInfo(){
            this.$axios.get("/category/get").then((response)=>{
                this.res = response.data;
                this.categoryInfo = this.res.data;
            }).catch((response)=>{
                console.log(response);
            })
        },
        addCategory(){
            this.$axios.post("/category/add",JSON.stringify(this.form)).then((repsonse)=>{
                this.res = repsonse.data;
                if(this.res.code == '200'){
                    this.$message.success(this.res.message);
                    this.dialogFormVisible = false;
                    this.getCategoryInfo();
                }else{
                    this.$message.error(this.res.message);
                    this.dialogFormVisible = false;
                }
            }).catch((response)=>{
                console.log(response);
            })
        },
        handleDelete(row){
            this.$axios.delete("/delete/"+row.id).then((response)=>{
                this.res = response.data;
                if(this.res.code == '200'){
                    this.$message.success(this.res.message);
                    this.getCategoryInfo();
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
    margin-top: 20px;
    background-color: #008c8c;
}

</style>