import request from '../http/request'
import axios from "../http/request";

export  const userLogin = (data) => {
    return axios({url: '/login?username='+data.username+'&password='+data.username+'', method: 'post', data});
}