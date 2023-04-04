// 导入安装的axios
import axios from 'axios'
// 导入elementUI错误消息提示
import { Message } from 'element-ui'
// 导入路由
axios.defaults.headers.post['Content-Type']='application/json';
axios.defaults.withCredentials=true;


// 创建axios请求实例,并暴
const myaxios = axios.create({
    baseURL: 'http://localhost:8081',
    timeout: 1000,
    headers: { 'X-Custom-Header': 'foobar' },
    withCredentials: true
});

// 添加请求拦截器
myaxios.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  if(sessionStorage.getItem('userId') == null){
    console.log(sessionStorage.getItem('userId'));
    Message.error("请登录");
  }
  return config;
}, function (error) {
  // 对请求错误做些
  return Promise.reject(error);
});
myaxios.interceptors.response.use(function (response) {
  // 2xx 范围内的状态码都会触发该函数。
  // 对响应数据做点什么
  return response;
}, function (error) {
  // 超出 2xx 范围的状态码都会触发该函数。
  // 对响应错误做点什么
  return Promise.reject(error);
});
// 添加响应拦截器
//暴露接口
export default myaxios;