import request from '../http/request'
import axios from "../http/request";

export  const list = (data) => {
    return axios({url: '/api/v1/site/list/' + data.module, method: 'post', data});
}

export  const put = (data) => {
    return axios({url: '/api/v1/site/put' , method: 'post', data});
}

export  const edit = (data) => {
    return axios({url: '/api/v1/site/edit' , method: 'post', data});
}

export  const del = (data) => {
    return axios({url: '/api/v1/site/del' , method: 'post', data});
}

