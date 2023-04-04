<template>
    <div order-manage>
        <div class="order-search">
        <el-form :inline="true" :model="searchBody" class="demo-form-inline">
  <el-form-item label="查询订单">
    <el-input v-model="searchBody.content" placeholder="请输入订单编号"></el-input>
    
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="queryOrderByOrderSerial">查询</el-button>
  </el-form-item>
</el-form>
       </div>
        <el-table
                :data="orderInfo"
                sytle="width: 100"
                class="leave-word-table"
            >
            <el-table-column>
      </el-table-column>
                <el-table-column
                    prop="id"
                    label="订单ID"
                    width="120">
                    </el-table-column>
                    <el-table-column
                    
                    prop="orderSerial"
                    label="订单编号"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    
                    prop="orderMain"
                    label="订单主体"
                    width="180">
                </el-table-column>
                <el-table-column
                   
                    prop="orderDesc"
                    label="订单描述"
                    width="250">
                    </el-table-column>
                    <el-table-column
                    
                    prop="orderNotes"
                    label="订单备注"
                    width="120">
                    </el-table-column>
                    <el-table-column
                    
                    prop="orderCheckState"
                    label="订单审核状态"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    
                    prop="orderTransState"
                    label="订单交易状态"
                    width="110">
                    </el-table-column>
                    <el-table-column
                  
                    prop="orderPrice"
                    label="订单价格"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    
                    prop="releasedUserAccount"
                    label="发布者账号"
                    width="150">
                    </el-table-column>
                    <el-table-column
                   
                    prop="acceptedUserAccount"
                    label="接收者账号"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    
                    prop="phoneNumber"
                    label="发布者手机号"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    fixed="right"
                    label="操作"
                    width="150">
                    <template slot-scope="scope">
                        <el-button @click="handleDelete(scope.row)" type="text" size="small" style="color: red;">删除</el-button>
                        <el-button @click="handleCheck(scope.row)" type="text" size="small">审核</el-button>
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
    name: 'OrderManage',
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
            resR:{
                message:'',
                code:'',
                data:''
            },
            searchBody:{
                content:'',
            },
            orderInfo:[{
                id:'',
                orderSerial:'',
                orderMain:'',
                orderDesc:'',
                orderNote:'',
                orderCheckState:'',
                orderTransState:'',
                orderPrice:'',
                releasedUserAccount:'',
                acceptedUserAccount:'',
                phoneNumber:'',
            }],
        }
    },
    created(){
        this.queryOrderNumber();
        this.queryOrderPage();    
    },
    methods:{
        handleCurrentChange(val) {
        this.currentPage = val;
        this.queryOrderPage();
      },
        queryOrderPage(){
            this.$axios.get("/order/page/"+this.currentPage+"/"+this.pageSize).then((response)=>{
                this.res = response.data;
                this.orderInfo = this.res.data;
                for(var i = 0;i < this.res.data.length;i++){
                    if(this.res.data[i].orderCheckState == '0'){
                        this.orderInfo[i].orderCheckState = '未审核'
                    }else{
                        this.orderInfo[i].orderCheckState = '已审核'
                    }
                    if(this.res.data[i].orderTransState == '0'){
                        this.orderInfo[i].orderTransState = '未接单'
                    }else if(this.res.data[i].orderTransState == '1'){
                        this.orderInfo[i].orderTransState = '已接单'
                    }else{
                        this.orderInfo[i].orderTransState = '已完成'
                    }
                    this.orderInfo[i].acceptedUserAccount = this.res.data[i].acceptedUser.userAccount;
                    this.orderInfo[i].releasedUserAccount = this.res.data[i].releasedUser.userAccount;
                }
            })
        },
        queryOrderNumber(){
            this.$axios.get("/order/count").then((response)=>{
                this.res = response.data;
                this.totalR = this.res.data;
            }).catch((response)=>{
                console.log(response);
            })
        },
        queryOrderByOrderSerial(){
            this.$axios.post("/order/query",JSON.stringify(this.searchBody)).then((response)=>{
                this.res = response.data;
                this.orderInfo = this.res.data;
                for(var i = 0;i < this.res.data.length;i++){
                    if(this.res.data[i].orderCheckState == '0'){
                        this.orderInfo[i].orderCheckState = '未审核'
                    }else{
                        this.orderInfo[i].orderCheckState = '已审核'
                    }
                    if(this.res.data[i].orderTransState == '0'){
                        this.orderInfo[i].orderTransState = '未接单'
                    }else if(this.res.data[i].orderTransState == '1'){
                        this.orderInfo[i].orderTransState = '已接单'
                    }else{
                        this.orderInfo[i].orderTransState = '已完成'
                    }
                    this.orderInfo[i].acceptedUserAccount = this.res.data[i].acceptedUser.userAccount;
                }    
            })
        },
        handleDelete(row){
            this.$axios.delete("/order/deleteById/"+row.id).then((response)=>{
                this.res = response.data;
                if(this.res.code == '200'){
                    this.$message.success("删除成功");
                    this.queryOrderPage();
                }else{
                    this.$message.error("删除失败，如果订单已接收，则无法删除");
                }
            }).catch((response)=>{
                console.log(response);
            })
        },
        handleCheck(row){
            this.$axios.put("/order/updateCheck/"+row.id).then((response)=>{
                this.res = response.data;
                if(this.res.code == '200'){
                    this.$message.success(this.res.message);
                    this.queryOrderPage();
                }else{
                    this.$message.error(this.res.message);
                }
            })
        }
        
    }
}
</script>

<style lang="less" scoped>
.order-search{
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
