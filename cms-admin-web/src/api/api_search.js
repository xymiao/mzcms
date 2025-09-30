import request from '../http/request'

// 内容搜索
export const searchContent = (keyword, currPage = 1, size = 10) => {
    return request({
        url: `/api/v1/search/content?keyword=${keyword}&currPage=${currPage}&size=${size}`, 
        method: 'post'
    });
}

// 根据标签搜索内容
export const searchByTag = (tagId, currPage = 1, size = 10) => {
    return request({
        url: `/api/v1/search/tag/${tagId}?currPage=${currPage}&size=${size}`, 
        method: 'post'
    });
}

// 根据分类搜索内容
export const searchByCategory = (categoryId, currPage = 1, size = 10) => {
    return request({
        url: `/api/v1/search/category/${categoryId}?currPage=${currPage}&size=${size}`, 
        method: 'post'
    });
}

// 高级搜索
export const advancedSearch = (params) => {
    const {keyword = '', categoryId = '', tagId = '', contentType = '', currPage = 1, size = 10} = params;
    return request({
        url: `/api/v1/search/advanced?keyword=${keyword}&categoryId=${categoryId}&tagId=${tagId}&contentType=${contentType}&currPage=${currPage}&size=${size}`, 
        method: 'post'
    });
}