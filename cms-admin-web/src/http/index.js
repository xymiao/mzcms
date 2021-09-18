import axios from "axios";
import {ElLoading, ElMessage} from "element-plus";

let instance = axios.create({
    baseURL: '/api',
    timeout: 10000, //设置超时
    headers: {
        'Content-Type': 'application/json;charset=UTF-8;',
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


export default instance;