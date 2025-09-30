package com.xymiao.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.pojo.CmsContent;

public interface CmsSearchService {
    
    /**
     * 搜索内容
     * @param keyword 关键词
     * @param current 当前页
     * @param size 页面大小
     * @return 搜索结果
     */
    Page<CmsContent> searchContent(String keyword, Integer current, Integer size);
    
    /**
     * 根据标签搜索内容
     * @param tagId 标签ID
     * @param current 当前页
     * @param size 页面大小
     * @return 内容列表
     */
    Page<CmsContent> searchContentByTag(String tagId, Integer current, Integer size);
    
    /**
     * 根据分类搜索内容
     * @param categoryId 分类ID
     * @param current 当前页
     * @param size 页面大小
     * @return 内容列表
     */
    Page<CmsContent> searchContentByCategory(String categoryId, Integer current, Integer size);
    
    /**
     * 高级搜索
     * @param keyword 关键词
     * @param categoryId 分类ID
     * @param tagId 标签ID
     * @param contentType 内容类型
     * @param current 当前页
     * @param size 页面大小
     * @return 搜索结果
     */
    Page<CmsContent> advancedSearch(String keyword, String categoryId, String tagId, 
                                   String contentType, Integer current, Integer size);
}