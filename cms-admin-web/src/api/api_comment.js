import request from '../http/request'

// 获取评论详情
export const getComment = (id) => {
    return request({url: `/api/v1/comment/get/${id}`, method: 'post'});
}

// 添加评论
export const addComment = (data) => {
    return request({url: '/api/v1/comment/put', method: 'post', data});
}

// 审核评论
export const approveComment = (data) => {
    return request({url: '/api/v1/comment/approve', method: 'post', data});
}

// 删除评论
export const deleteComment = (data) => {
    return request({url: '/api/v1/comment/del', method: 'post', data});
}

// 分页查询内容评论
export const listComments = (contentId, currPage = 1, size = 10) => {
    return request({url: `/api/v1/comment/list/${contentId}?currPage=${currPage}&size=${size}`, method: 'post'});
}

// 查询评论回复
export const getReplies = (parentId) => {
    return request({url: `/api/v1/comment/replies/${parentId}`, method: 'post'});
}