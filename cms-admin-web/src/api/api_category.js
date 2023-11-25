import request from '../http/request'

export  const listCategory = (data) => {
    return request({url: '/api/v1/category/list/' + data.module , method: 'post', data});
}

export  const putCategory = (data) => {
    return request({url: '/api/v1/category/put', method: 'post', data});
}

export  const editCategory= (data) => {
    return request({url: '/api/v1/category/edit' , method: 'post', data});
}

export  const delCategory = (data) => {
    return request({url: '/api/v1/category/del' , method: 'post', data});
}

export  const showCategory = (data) => {
    return request({url: '/api/v1/category/show/' + data.module, method: 'post', data});
}
