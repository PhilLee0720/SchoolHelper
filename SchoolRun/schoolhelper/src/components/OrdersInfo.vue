<template>
<div id="search">
    <el-form :inline="true"  class="demo-form-inline">
        <el-form-item>
            <el-input type="text" size="small" maxlength="20" v-model="searchBody.content" placeholder="请输入订单编号"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="searchOrder">查询</el-button>
        </el-form-item>
    </el-form>
    <el-tabs  v-model="activeName" type="card" @tab-click="handleClick" style="height: 60px;">
    <el-tab-pane label="分类"></el-tab-pane>
    <el-tab-pane class="first-category" ></el-tab-pane>
    <el-tab-pane v-for="item in labels" :label="item.categoryName" :key="item.id"  :name="item.id"></el-tab-pane>
    </el-tabs>
    <div id="orders">
        <div  class="order" v-for="item in orderInfos" :key="item.id" >
            <div class="left">
                <span class="order-des">{{item.orderMain}}</span>
                <span class="ca-name"></span>
                <div class="note">
                    <span>订单描述：</span>
                    <span>{{item.orderDesc}}</span>
                </div>
            </div>
            <div class="right">
                <el-button @click="acceptOrder(item.id)" type="primary">接单</el-button>
                <span class="price"><span>报价：</span>{{item.orderPrice}}￥</span>
                <span class="create-time"><span>发布时间：</span>{{item.beginTime}}</span>
            </div>
    </div>
    </div>
</div>
</template>

<script>
export default{
    name: 'OrdersInfo',
    data(){
        return{
            searchBody:{
                content:'',
            },
            orderInfos:[{
                id:'',
                orderMain:'',
                orderDesc:'',
                orderPrice:'',
                beginTime:''
            }],
            activeName: '0',
            labels:[
             {
                id:'',
                categoryName:''
             }   
            ],
            res:{
                message:'',
                code:'',
                data:''
            },
            resO:{
                message:'',
                code:'',
                data:''
            },
            resA:{
                message:'',
                code:'',
                data:''
            }
        }   
    },
    created(){
        this.queryCategory(),
        this.queryOrder()
    },
    methods: {
        queryCategory(){
            this.$axios.get("/category/get").then((response)=>{
                this.res=response.data;
                this.labels = this.res.data;
            }).catch((response)=>{
                console.log(response);
            })
        },
        queryOrder(){
            this.$axios.get("/order/get").then((response)=>{
                this.resO = response.data;
                this.orderInfos = this.resO.data;
            }).catch((response)=>{
                console.log(response);
            })
        },
        queryOrderByCategoryId(id){
             this.$axios.get("order/getByCid/"+id).then((response)=>{
                 this.resO = response.data;
                this.orderInfos = this.resO.data;
             }).catch((response)=>{
                 console.log(response);
             })
            },
        handleClick(tab){
            if(tab.name == null){
                this.queryOrder();
            }
            this.queryOrderByCategoryId(tab.name);
        },
        searchOrder(){
            if(this.searchBody.content == ''){
                this.$message.error("请先输入");
                return;
            }
            this.$axios.post("/order/search",JSON.stringify(this.searchBody)).then((response)=>{
                this.resO = response.data;
                this.orderInfos = this.resO.data;
                if(this.resO.code != '200'){
                    this.$message.error(this.resO.message);
                }
            })
        },
        acceptOrder(id){
            this.$axios.put("/order/accept/"+id).then((response)=>{
                this.resA = response.data;
                if(this.resA.code == "200"){
                    this.$message.success(this.resA.message);
                    this.queryOrder();
                }else{
                    this.$message.error(this.resA.message);
                }
            })
        }
    }
}
</script>

<style>
.note{
    width:180px;
    height: 100px;
    border:1px solid #409eff;
    font-size: 5px;
    word-break:break-all;
    overflow: hidden;
    text-align: left;
}
.note span{
}
.note::after{
    display: block;
    content: '';
    clear: both;
}
#search{
    width: 100%;
    margin-top: 60px;
    /* line-height: 40px; */
    color: white;
    background-color: #008c8c;
}
.demo-form-inline{
    margin-left: -400px;
    margin-top: 3px;
}
.first-category{
    margin-left: 100px;
}
#orders{
    width: 1000px;
    margin: 0 auto;
    background-color: #008c8c;
}
#orders::after{
    display: block;
    clear: both;
    content: "";
}
#orders .order{
    width: 300px;
    height: 150px;
    float: left;
    margin: 30px 15px;
    background-color: #008c8c;
    border-radius: 4px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12);
}
#orders .order .left{
    width: 180px;
    height: 120px;
    float: left;
    margin: 8px 5px;
}
#orders .order .right{
    width: 100px;
    height: 120px;
    font-size: 5px;
    float: left;
    margin: 8px 5px;
}
#orders .order .left .order-des{
    float: left;
    margin-left: 20px;
    overflow: hidden;
}
#orders .order .left .le-word{
    margin-top: 20px;
}
.right .price{
    text-align: left;
    display: block;
    position: relative;
    top: 20px;
}
.right .create-time{
    text-align: left;
    display: block;
    position: relative;
    top: 40px;
}

</style>