import request from '../http/request'

// 获取标签详情
export const getTag = (id) => {
    return request({url: `/api/v1/tag/get/${id}`, method: 'post'});
}

// 添加标签
export const addTag = (data) => {
    return request({url: '/api/v1/tag/put', method: 'post', data});
}

// 编辑标签
export const editTag = (data) => {
    return request({url: '/api/v1/tag/edit', method: 'post', data});
}

// 删除标签
export const deleteTag = (data) => {
    return request({url: '/api/v1/tag/del', method: 'post', data});
}

// 查询所有标签
export const listTags = () => {
    return request({url: '/api/v1/tag/list', method: 'post'});
}

// 查询热门标签
export const getHotTags = (limit = 10) => {
    return request({url: `/api/v1/tag/hot?limit=${limit}`, method: 'post'});
}

// 查询内容的标签
export const getContentTags = (contentId) => {
    return request({url: `/api/v1/tag/content/${contentId}`, method: 'post'});
}