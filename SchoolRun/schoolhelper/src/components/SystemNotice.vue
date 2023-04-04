<template>
<div id="sys-not" >
    <div class="sys-out">
        <div class="noti" v-for="item in notice" :key="item.id">
        <div class="content">{{item.noticeContent}}</div>
        <div class="datetime">发布时间：<span>{{item.createTime}}</span></div>
        </div>
</div>
</div>
</template>

<script>
export default{
    name: 'SystemNotice',
    data(){
        return{
            res:{
                message:'',
                code:'',
                data:''
            },
            notice:[
                {
                    id:'',
                    noticeContent:'',
                    createTime:''
                }
            ]
        }
    },
    created(){
        this.queryNotice();
    },
    methods:{
        queryNotice(){
            this.$axios.get("/notice/get").then((response)=>{
                this.res = response.data;
                this.notice = this.res.data; 
            }).catch((response)=>{
                console.log(response);
            })
        }   
    }
}
</script>

<style>
#sys-not{
    width: 100%;
    border: 3px solid #409eff;
    margin: 50px auto;
    border-radius: 5px;
    color: white;
    box-sizing: border-box;
}
.noti{
    width: 1000px;
    height: 100px;
    border: 1px solid #409eff;
    margin: 20px auto;
    border-radius: 5px;
    margin-bottom: 0px;
    box-sizing: border-box;
}
.content{
    height: 80px;
    border: 1px solid #409eff;
    font-size: 8px;
    box-sizing: border-box;
    text-align: left;
}
.datetime{
    color: black;
    font-family: Arial, Helvetica, sans-serif;
    font-size: 10px;
}
.sys-out{
    background-color: #008c8c;
}

</style>