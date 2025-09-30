import request from '../http/request'

// 上传文件
export const uploadFile = (file) => {
    const formData = new FormData();
    formData.append('file', file);
    return request({
        url: '/api/v1/media/upload', 
        method: 'post', 
        data: formData,
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
}

// 获取媒体文件详情
export const getMedia = (id) => {
    return request({url: `/api/v1/media/get/${id}`, method: 'post'});
}

// 编辑媒体文件信息
export const editMedia = (data) => {
    return request({url: '/api/v1/media/edit', method: 'post', data});
}

// 删除媒体文件
export const deleteMedia = (data) => {
    return request({url: '/api/v1/media/del', method: 'post', data});
}

// 分页查询媒体文件
export const listMedia = (currPage = 1, size = 10, mediaType = '') => {
    return request({
        url: `/api/v1/media/list?currPage=${currPage}&size=${size}&mediaType=${mediaType}`, 
        method: 'post'
    });
}

// 查询用户的媒体文件
export const getUserMedia = (userId) => {
    return request({url: `/api/v1/media/user/${userId}`, method: 'post'});
}