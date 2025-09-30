import request from '../http/request'

// 获取友情链接详情
export const getLink = (id) => {
    return request({url: `/api/v1/link/get/${id}`, method: 'post'});
}

// 添加友情链接
export const addLink = (data) => {
    return request({url: '/api/v1/link/put', method: 'post', data});
}

// 编辑友情链接
export const editLink = (data) => {
    return request({url: '/api/v1/link/edit', method: 'post', data});
}

// 删除友情链接
export const deleteLink = (data) => {
    return request({url: '/api/v1/link/del', method: 'post', data});
}

// 查询所有友情链接
export const listLinks = () => {
    return request({url: '/api/v1/link/list', method: 'post'});
}

// 根据类型查询友情链接
export const getLinksByType = (linkType) => {
    return request({url: `/api/v1/link/type/${linkType}`, method: 'post'});
}

// 审核友情链接
export const approveLink = (data) => {
    return request({url: '/api/v1/link/approve', method: 'post', data});
}