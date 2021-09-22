import axios from "axios";
import {ElLoading, ElMessage} from "element-plus";
import { store } from '../store/index'

let instance = axios.create({
    baseURL: '/api',
    timeout: 10000, //设置超时
    headers: {
        'Content-Type': 'application/json;charset=UTF-8;',
        'Authorization': store.getters.userToken,
    },
    withCredentials: true,
});

let loading;
let requestCount = 0;
const showLoading = () => {
    if (requestCount === 0 && !loading) {
        loading = ElLoading.service({text: 'Loading', background: 'rgba(0, 0, 0, 0.7)', spinner: 'el-icon-loading'});
    }
    requestCount++;
};

const hideLoading = () => {
    requestCount--;
    if(requestCount == 0){
        loading.close();
    }
}

instance.interceptors.response.use(res => {
    // 成功响应的拦截
    return Promise.resolve(res.data)
}, err =>{
    // 失败响应的拦截
    console.log("错误" , err)
    if(err.response){
        // 失败响应的status需要在response中获得
        console.log(err.response)
        switch(err.response.status){
            // 对得到的状态码的处理，具体的设置视自己的情况而定
            case 401:
                console.log('未登录401')
                break
            case 403:
                console.log('未登录403')
                store.commit("userClean");
                break
            case 404:
                console.log('未登录404')
                break
            case 405:
                console.log('不支持的方法')
                break
            // case ...
            default:
                console.log('其他错误')
                break
        }
    }
    // 注意这里应该return promise.reject(),
    // 因为如果直接return err则在调用此实例时，响应失败了也会进入then(res=>{})而不是reject或catch方法
    return Promise.reject(err)
})

export default instance;