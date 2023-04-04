<template>
<div id="my-comment">
    <div v-for="item in comments " id="my-comment-info" :key="item.id">
        <div id="user-released-info">
            <span class="label">用户昵称:</span>
            <span class="content">{{item.userNickName}}</span>
            <span class="label">发布时间:</span>
            <span class="content">{{item.createTime}}</span>
        </div>
        <div id="user-released-info-content">
            {{item.commentContent}}
        </div>
        <div id="user-released-info-delete">
            <el-button @click="deleteComment(item.id)" type="primary" icon="el-icon-delete"></el-button>
        </div>
    </div>
</div>  
</template>
<script>
export default{
    name: 'MyComment',
    data(){
        return{
            res:{
                message:'',
                code:'',
                data:''
            },
            resD:{

            },
            comments:[
                {
                    id:'',
                    commentContent:'',
                    userNickName:'',
                    createTime:''
                }
            ]
        }
    },
    created(){
        this.queryComments();
    },
    methods:{
        queryComments(){
            this.$axios.get("/comment/query/"+sessionStorage.getItem("userId")).then((response)=>{
                this.res = response.data;
                this.comments = this.res.data;
                for(var i = 0;i < this.res.data.length;i++){
                    this.comments[i].userNickName = this.res.data[i].acceptedUser.userNickName;
                }               
            })
        },
        deleteComment(id){
            this.$axios.delete("/comment/delete/"+id).then((response)=>{
                this.resD = response.data;
                if(this.resD.code == '200'){
                    this.$message({
                        message: this.resD.message,
                        type: 'success'
                    });
                this.queryComments();
                }else{
                    this.$message.error(this.resD.message);
                }
            })
        }
    }
}
</script>
<style scoped>
#my-comment{
    width: 900x;
    border: 1px solid #409eff;
    margin: 10px 10px;
    border-radius: 4px;
    box-sizing: border-box;
}
#my-comment-info{
    width: 900x;
    border: 1px solid white;
    margin: 10px 10px;
    border-radius: 4px;
    box-sizing: border-box;

}
#user-released-info{
    width: 999px;
    height: 25px;
    border-top: none;
    border-left: none;
    border-right: none;
}
#user-released-info .label{
    float: left;
    color: white;
    font-size: 12px;
    line-height: 25px;
    margin-left: 200px ;
}
#user-released-info .content{
    float: left;
    color: white;
    font-size: 12px;
    line-height: 25px;
    margin-left: 20px ;
    height: 25px;
    border: none;
    
}
#user-released-info-content{
    width: 870px;
    height: 51px;
    text-align: left;
    word-break: break-all;
    overflow: hidden;
}
#user-released-info-delete{
    width: 56px;
    height: 40px;
    position: relative;
    top: -30px;
    left: 910px;
}
</style>
