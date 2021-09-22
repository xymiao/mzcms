import request from '../http/request'
import axios from "../http/request";

export  const listMenu = (data) => {
    return axios({url: '/api/v1/menu/list/' + data.module + "?currPage=" + data.currPage, method: 'post', data});
}

export  const putMenu = (data) => {
    return axios({url: '/api/v1/menu/put' , method: 'post', data});
}
