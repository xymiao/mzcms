import request from '../http/request'
import axios from "../http/request";

export  const listMenu = (data) => {
    return axios({url: '/api/v1/menu/list/' + data.module + "?currPage=" + data.currPage, method: 'post', data});
}

export  const putMenu = (data) => {
    return axios({url: '/api/v1/menu/put' , method: 'post', data});
}

export  const editMenu = (data) => {
    return axios({url: '/api/v1/menu/edit' , method: 'post', data});
}

export  const delMenu = (data) => {
    return axios({url: '/api/v1/menu/del' , method: 'post', data});
}

export  const showMenu = (data) => {
    return axios({url: '/api/v1/menu/show/' + data.module, method: 'post', data});
}
