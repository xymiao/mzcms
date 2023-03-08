import axios from "../http/request";

export  const userLogin = (data) => {
    return axios({url: '/login?username='+data.username+'&password='+data.password+'', method: 'post', data});
}