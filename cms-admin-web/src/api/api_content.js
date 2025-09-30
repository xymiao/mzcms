import request from '../http/request'
import axios from "../http/request";

export  const listContent = (data) => {
    return axios({url: '/api/v1/content/list/' + data.categoryId , method: 'post', data});
}

export  const getContent = (data) => {
    return axios({url: '/api/v1/content/get/' + data.contentId , method: 'post', data});
}

export  const putContent = (data) => {
    return axios({url: '/api/v1/content/put', method: 'post', data});
}

export  const editContentPost= (data) => {
    return axios({url: '/api/v1/content/edit' , method: 'post', data});
}

export  const delContent = (data) => {
    return axios({url: '/api/v1/content/del' , method: 'post', data});
}

export  const showContent = (data) => {
    return axios({url: '/api/v1/content/show/' + data.module, method: 'post', data});
}

// 新增：保存内容及标签
export const saveContentWithTags = (data) => {
    return axios({url: '/api/v1/content/put-with-tags', method: 'post', data});
}

// 新增：更新内容及标签
export const updateContentWithTags = (data) => {
    return axios({url: '/api/v1/content/edit-with-tags', method: 'post', data});
}
