<template>
    <div id="my-order">
        <span class="title">我的订单</span>
        <div class="order-control-button">
            <el-button @click="setFlagA" type="primary" round>我接收的订单</el-button>
            <el-button @click="setFlagP" type="primary" round>我发布的顶单</el-button>
        </div>
        
            <div v-for="(item,index) in orders"  :key="item.id" id="my-order-content">
            <div class="top">
                <span class="category">{{item.categoryName}}</span>
                <span class="time">{{item.createTime}}</span>
                <span class="check-mark">审核状态:</span>
                <span class="check-result">{{item.orderCheckResult}}</span>
                <span v-if="seen" class="a-mark">接收状态:</span>
                <span v-if="seen" class="a-result">{{item.orderAcceptedResult}}</span>
            </div>
            <div class="bottom">
                <div class="order-title">{{item.orderMain}}</div>
                <div class="order-do-button">
                    <el-button @click="goToMyOrder(item.id)" type="primary">查看</el-button>
                    <el-button type="success" v-if="!(seen)" @click="finishOrder(item.id,index)" :disabled="item.disabledFlag">完成</el-button>
                    <el-button @click="deleteMyOrder(item.id)" v-if="seen" type="danger" >撤销</el-button>
                    <el-button @click="goToAS(item.id)"    v-if="seen" type="primary" class="checkC-button">查看接收人信息</el-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default{
    name: 'MyOrder',
    data(){
        return{
            seen:false,
            orderDetailPage: 0,
            res:{
                message:'',
                code:'',
                data:''
            },
            resD:{
                message:'',
                code:'',
                data:''
            },
            resF:{
                message:'',
                code:'',
                data:''
            },
            orders:[{
                id:'',
                orderMain:'',
                createTime:'',
                orderCheckState:'',
                categoryName:'',
                orderCheckResult:'',
                orderAcceptedState:'',
                orderAcceptedResult:'',
                disabledFlag: false,
            }]
        }
    },
    created(){
        this.queryOrderInfoIAccepted();
    },
    methods:{
        deleteMyOrder(orderId){
            this.$axios.delete("/order/deleteById/"+orderId).then((response)=>{
                this.resD = response.data;
                if(this.resD.code == "200"){
                    this.$message({
                        message: this.resD.message,
                        type: 'success'
                    });
                    this.queryOrderInfoReleased();
                }else{
                    this.$message.error(this.resD.message);
                }
            })   
        },
        goToMyOrder(id){
            if(this.seen == true){
                this.orderDetailPage = 1;
            }
            this.$store.commit('populateOrderId',id);
            this.$store.commit('populateOrderDetailPage',this.orderDetailPage);
            this.$router.replace("/order-detail");
        },
        goToAS(id){
            this.$store.commit('populateOrderId',id);
            this.$router.replace("/order-accepted-situation");
        },
        setFlagA(){
            this.queryOrderInfoIAccepted();
            this.seen=false;
            
        },
        setFlagP(){
            this.queryOrderInfoReleased();
            this.seen=true;
        },
        queryOrderInfoIAccepted(){
            this.$axios.get("/order/getA/"+sessionStorage.getItem("userId")).then((response)=>{
               this.res = response.data;
               this.orders = this.res.data; 
               
               for(var i = 0;i < this.res.data.length;i++){
                 this.orders[i].categoryName = this.res.data[i].category.categoryName;
                 if(this.orders[i].orderCheckState === 1){
                    this.orders[i].orderCheckResult = "通过"
                 }else{
                    this.orders[i].orderCheckResult = "未通过"
                 }
               }
            }) 
        },
        queryOrderInfoReleased(){
            this.$axios.get("/order/getR/"+sessionStorage.getItem("userId")).then((response)=>{
                this.res = response.data;
                this.orders = this.res.data;
                for(var i = 0;i < this.res.data.length;i++){
                    this.orders[i].categoryName = this.res.data[i].category.categoryName;
                    this.orders[i].orderAcceptedState = this.res.data[i].orderTransState;
                    if(this.orders[i].orderAcceptedState ===1){
                        this.orders[i].orderAcceptedResult = "已接收";
                    }else{
                        this.orders[i].orderAcceptedResult = "未接收";
                    }
                    if(this.orders[i].orderCheckState === 1){
                        this.orders[i].orderCheckResult = "通过"
                    }else{
                     this.orders[i].orderCheckResult = "未通过"
                    }
                }
            })
        },
        finishOrder(id,index){
            if(this.orders[index].disabledFlag == true){
                return;
            }
            this.$axios.put("/order/finish/"+id).then((response)=>{
                this.resF = response.data;
                if(this.resF.code == '200'){
                    this.$message.success(this.resF.message);
                    this.orders[index].disabledFlag = true;
                }else{
                    this.$message.error(this.resF.message);
                } 
            })
        }
    }
}
</script >

<style lang="less" scoped>
.title{
    font-size: 25px;
    color: #409eff;
    
}
#my-order{
    width: 100%;
    margin: 10px auto;
    background-color: #008c8c;
}
#my-order-content{
    width: 1000px;
    height: 100px;
    margin: 10px auto;
    
}
.order-control-button{
    width: 400px;
    height: 43px;
    margin: 10px auto;
}
.top{
    height: 30px;
    text-align: left;
    color: white;
}
.time{
    margin-left: 20px;
}
.check-mark{
    margin-left: 666px;
}
/deep/.order-do-button .checkC-button{
    position: relative;
    top: -40px;
    right: 170px;
}
.a-mark{
   position: relative;
   top: -20px;
   left: 700px;
}
.a-result{
    position: relative;
    top: -20px;
    left: 700px;
}
.order-title{
    text-align: left;
    width: 800px;
    color: white;
    font-size: 30px;
    line-height: 50px;
    display: inline-block;
    float: left;
}
.order-do-button{
    margin-top: 15px;
}
</style>