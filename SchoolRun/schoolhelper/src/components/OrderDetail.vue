<template>
<div id="order-detail">
    <div od="order-detail">
        <span class="title">订单详情:</span>
    <div class="order-detail-content">
        <div class="order-detail-content-left">
        <el-descriptions class="margin-top"  :column="1" :size="small">
    <el-descriptions-item>
      <template slot="label">
        跑腿费
      </template>
      {{order.price}}￥
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        出发时间
      </template>
      {{order.beginTime}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        取件地点
      </template>
      {{order.doAddress}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        送达地点
      </template>
      {{order.sendAddress}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        物品总量
      </template>
      {{order.goodsWeight}}
    </el-descriptions-item>
  </el-descriptions>
        </div>
        <div class="order-detail-content-right">
            <el-descriptions class="margin-top"  :column="1" :size="small">
    <el-descriptions-item>
      <template slot="label">
        所属分类
      </template>
      {{order.categoryName}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        联系电话
      </template>
      {{order.phoneNumber}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        发布人
      </template>
      {{order.releasedUserNickName}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        发布时间
      </template>
      {{order.createTime}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        订单编号
      </template>
      {{order.orderSerial}}
    </el-descriptions-item>
  </el-descriptions>
        </div>
    </div>
    </div>
    <div id="order-note">
        <span class="title">备注:</span>
        <div class="order-note-content">
          {{order.orderNotes}}
        </div>
    </div>
<div id="order-comment">
    <span class="title">评论区:</span>
    <div class="comment-area">
        <el-form>
            <el-input class="comment-area-input"
  type="text"
  :rows="5"
  placeholder="请输入内容"
  v-model="comment.commentContent">
</el-input>
        </el-form>
        <el-button class="button" @click="addComment(comment)" type="primary" round>提交评论</el-button>
    </div>
    <div class="comment-history">
        <span class="title">
        历史评论:
        </span>
        <div class="comment-history-content" v-for="item in comments" :key="item.id">
            {{item.commentContent}}
            <span class="time-title">评论时间:</span>
            <span class="time">{{item.createTime}}</span>
        </div>
    </div>
</div>
</div>
</template>
<script>
export default{
    name: 'OrderDetail',
    data() {
      return {
        comment:{
          commentContent:'',
        },
        small:'',
        fit:'',
        res:{
          message:'',
          code:'',
          data:''
        },
        resAddComment:{
          message:'',
          code:'',
          data:''
        },
        resC:{
          message:'',
          code:'',
          data:''
        },
        comments:[{
          id:'',
          commentContent:'',
          createTime:''
        }],
        order:{
          id:'',
          orderSerial:'',
          orderMain:'',
          orderDesc:'',
          orderNotes:'',
          price:'',
          beginTime:'',
          doAddress:'',
          sendAddress:'',
          goodsWeight:'',
          phoneNumber:'',
          createTime:'',
          categoryName:'',
          releasedUserNickName:''
        },
        accquiredId:'',
        orderDetailBelong:'',
      }
    },
    created(){
        this.accquiredId = this.$store.state.orderId;
        this.orderDetailBelong = this.$store.state.orderDetailPage;
        this.queryOrder(this.accquiredId);
        this.queryComment();

    },
    methods:{
      queryOrder(val){
        this.$axios.get("/order/getOrder/"+val).then((response)=>{
          this.res = response.data;
          this.order.orderSerial = this.res.data.orderSerial;
          this.order.orderMain = this.res.data.orderMain;
          this.order.orderDesc = this.res.data.orderDesc;
          this.order.orderNotes = this.res.data.orderNotes;
          this.order.price = this.res.data.orderPrice;
          this.order.beginTime = this.res.data.beginTime;
          this.order.doAddress = this.res.data.doAddress;
          this.order.sendAddress = this.res.data.sendAddress;
          this.order.goodsWeight = this.res.data.goodsWeight;
          this.order.phoneNumber = this.res.data.phoneNumber;
          this.order.createTime = this.res.data.createTime;
          this.order.categoryName = this.res.data.category.categoryName;
          this.order.releasedUserNickName = this.res.data.releasedUser.userNickName;
        })
      },
      addComment(comment){
        if(this.orderDetailBelong == 0){
          console.log(this.order.releasedUserNickName);
          console.log(this.comment.commentContent);
          this.$axios.post("/comment/addA/"+this.accquiredId+"/"+this.order.releasedUserNickName,JSON.stringify(comment)).then((response)=>{
            this.resAddComment = response.data;
            if(this.resAddComment.code == '200'){
              this.$message({
                        message: this.resAddComment.message,
                        type: 'success'
                    });
            }else{
              this.$alert(this.resAddComment.message.toString(), '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                    this.$message({
                    type: 'info'
                });
              }
            })
            }
          })  
        }else if(this.orderDetailBelong == 1){
          this.$axios.post("/comment/addR/"+this.accquiredId+"/"+this.order.releasedUserNickName,JSON.stringify(this.comment)).then((response)=>{
            this.resAddComment = response.data;
            if(this.resAddComment.code == '200'){
              this.$message({
                        message: this.resAddComment.message,
                        type: 'success'
                    });
            }else{
              this.$alert(this.resAddComment.message.toString(), '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                    this.$message({
                    type: 'info'
                });
              }
            })
            }
          })  
        }
        
      },
      queryComment(){
        this.$axios.get("/comment/getCommentByOrderId/"+this.accquiredId).then((response)=>{
          this.resC = response.data;
          this.comments  = this.resC.data;
          })
      }
    }
    
}
</script>
<style lang="less"  scoped>
#order-detail{
    width: 1200px;
    margin: 10px auto;
    text-align: left;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12)
    
}
.title{
    font-size: 25px;
    color: #409eff;
}
.order-detail-content{
    width: 100%;
    height: 173px;
    background-color: rgb(226, 221, 221);
}
.order-detail-content-left{
    width: 400px;
    height: 100%;
    display: inline-block;
    float: left;
}
.order-detail-content-right{
    width: 400px;
    height: 100%;
    display: inline-block;
    float: left;
    margin-left: 395px;
}
/deep/.order-detail-content .el-descriptions__body{
    background-color: rgb(226, 221, 221);
}

/deep/.comment-area  .el-input_inner{
  height: 200px;
}
.button{
    margin-left: 300px;
    margin-top: 20px;
}
#order-comment{
    width: 100%;
    margin-top: 30px;
}
#order-comment .comment-area{
    width: 800px;
}
.order-note-content{
    width: 100%;
    height: 50px;
    color: white;
    font-size: 15px;
    word-break: break-all;
    overflow: hidden;
}
.comment-history-content{
    width: 800px;
    height: 50px;
    font-size: 10px;
    color: white;
    word-break: break-all;
    overflow: hidden;
    margin: 5px 0;
}
.time{
    font-size: 10px;
    color: white;
    position: relative;
    bottom: -23px;
    right: -160px;
}
.time-title{
    font-size: 10px;
    color: white;
    position: relative;
    bottom: -23px;
    right: -150px;
}
</style>