<template>
<div id="order-release">
    <h1 style="color:white">订单发布</h1>
<div id="order-release-item">
  <el-form  ref="form" :model="form" :rules="rules"  label-width="80px">
  <el-form-item label="订单主体" prop="orderMain">
    <el-input v-model="form.orderMain" placeholder="请输入订单主要内容"></el-input>
  </el-form-item>
  <el-form-item label="订单描述" prop="orderDesc">
    <el-input v-model="form.orderDesc" placeholder="请输入订单描述"></el-input>
  </el-form-item>
  <el-form-item label="订单备注" prop="orderNote">
    <el-input v-model="form.orderNote" placeholder="请输入订单备注"></el-input>
  </el-form-item>
  <el-form-item label="价格" prop="orderPrice">
    <el-input  v-model.number="form.orderPrice" placeholder="请输入价格"></el-input>
  </el-form-item>
  <el-form-item label="出发时间" prop="beginTime">
    <el-date-picker
      v-model="form.beginTime"
      type="datetime"
      placeholder="选择日期时间">
    </el-date-picker>
  </el-form-item>
  <el-form-item label="取件地点" prop="doAddress"> 
    <el-input v-model="form.doAddress" placeholder="请输入取件地点"></el-input>
  </el-form-item>
  <el-form-item label="送达地点" prop="sendAddress">
    <el-input v-model="form.sendAddress" placeholder="请输入地点"></el-input>
  </el-form-item>
  <el-form-item label="物品重量" prop="goodsWeight">
    <el-input v-model.number="form.goodsWeight" placeholder="请输入物品重量"></el-input>
  </el-form-item>
  <el-form-item label="所属分类" prop="categoryId">
    <el-select v-model="form.categoryId" placeholder="请选择所属分类">
      <el-option v-for="i in categorys" :key="i.id" :label="i.categoryName" :value="i.id"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="联系电话" prop="phoneNumber">
    <el-input v-model="form.phoneNumber" placeholder="请输入联系电话"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="releaseOrder('form')">立即创建</el-button>
    <el-button>取消</el-button>
  </el-form-item>
</el-form>
</div>
</div>
</template>

<script>
export default{
    name: 'OrderRelease',
    data() {
      return {
        form: {
          orderMain: '',
          orderPrice:'',
          orderDesc:'',
          orderNote:'',
          doAddress:'',
          beginTime:'',
          sendAddress: '',
          goodsWeight: '',
          phoneNumber: '',
          categoryId:''
        },
        categoryId: '',
        categorys:[
            {id:'',
            categoryName:''}
          ],
        resC:{
          message: '',
          code: '',
          data: ''
        },
        resR:{
          message: '',
          code: '',
          data: ''
        },
        rules:{
          orderMain:[
            {required: true, message: '请输入订单主要内容', trigger: 'blur' },
            { min: 10, max: 50, message: '长度在10到50个字符', trigger: 'blur' }
          ],
          orderDesc:[
          {required: true, message: '请输入订单具体内容', trigger: 'blur' },
            { min: 10, max: 200, message: '长度在 10 到 200 个字符', trigger: 'blur' }
          ],
          orderNote:[
            { min: 10,max: 50,message: '长度在10到50个字符',trigger: 'blur'}
          ],
          price:[
            {required: true,message: "请输入订单价格",trigger: 'blur'},
            {required: true,message: "价格必须为整数",trigger: 'blur'}
          ],
          beginTime:[
          { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
          ],
          doAddress:[
            {required: true,message: "请输入取件地点",trigger: 'blur'}
          ],
          sendAddress:[
            {required: true,message: "请输入送达地点",trigger: 'blur'}
          ],
          goodsWeight:[
            {required: true, message: '请输入物品重量', trigger: 'blur' },
            {type: 'number',message:'物品重量必须为数值',trigger:'blur'}
          ],
          categoryId:[
          {required:true, message: '请选择分类', trigger: 'change' }
          ],
          phoneNumber:[
          {required: true,message: '请输入电话号码', trigger: 'blur'}
          ]
          
          
        }
      }
    },
    created(){
      this.queryCategory();
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },
      queryCategory(){
            this.$axios.get("/category/get").then((response)=>{
                this.resC=response.data;
                this.categorys = this.resC.data;
            }).catch((response)=>{
                console.log(response);
            })
        },
      releaseOrder(formName){
        this.categoryId = this.form.categoryId;
        this.$refs[formName].validate((valid) => {
          if (valid) {
          console.log(JSON.stringify(this.form));
          this.$axios.post("/order/add/"+sessionStorage.getItem("userId")+"/"+this.categoryId,JSON.stringify(this.form)).then((response)=>{
          this.resR = response.data;
          if(this.resR.code == "200"){
            this.$alert(this.resR.message.toString(), '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                    this.$message({
                    type: 'info'
                });
              }
            });  
          }else{
            this.$alert(this.resR.message.toString(), '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                    this.$message({
                    type: 'info'
                });
              }
            }); 
          }
        })
          } else {
            alert("error submit!!!");
            return false;
          }
        });
      }
      
    }
    
}
</script>
<style scoped>
#order-release{
    width: 800px;

    margin: 0 auto;
}

</style>