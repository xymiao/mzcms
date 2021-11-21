import request from '../http/request'
import axios from "../http/request";

export  const listCategory = (data) => {
    return axios({url: '/api/v1/category/list/' + data.module , method: 'post', data});
}

export  const putCategory = (data) => {
    return axios({url: '/api/v1/category/put', method: 'post', data});
}

export  const editCategory= (data) => {
    return axios({url: '/api/v1/category/edit' , method: 'post', data});
}

export  const delCategory = (data) => {
    return axios({url: '/api/v1/category/del' , method: 'post', data});
}

export  const showCategory = (data) => {
    return axios({url: '/api/v1/category/show/' + data.module, method: 'post', data});
}
