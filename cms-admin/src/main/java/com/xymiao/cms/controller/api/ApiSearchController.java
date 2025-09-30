package com.xymiao.cms.controller.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.pojo.CmsContent;
import com.xymiao.cms.service.CmsSearchService;
import com.xymiao.cms.util.ResponseBodyUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * 搜索API控制器
 */
@RestController
@RequestMapping("api/v1/search")
public class ApiSearchController {
    
    private final static Logger logger = LoggerFactory.getLogger(ApiSearchController.class);
    private final CmsSearchService cmsSearchService;
    
    public ApiSearchController(CmsSearchService cmsSearchService) {
        this.cmsSearchService = cmsSearchService;
    }
    
    /**
     * 内容搜索
     */
    @PostMapping("content")
    public ResponseBodyUtils<Page<CmsContent>> searchContent(@RequestParam String keyword,
                                                            @RequestParam(defaultValue = "1") Integer currPage,
                                                            @RequestParam(defaultValue = "10") Integer size) {
        logger.info("搜索内容，关键词: {}", keyword);
        Page<CmsContent> result = cmsSearchService.searchContent(keyword, currPage, size);
        return ResponseBodyUtils.createBySuccess(result);
    }
    
    /**
     * 根据标签搜索内容
     */
    @PostMapping("tag/{tagId}")
    public ResponseBodyUtils<Page<CmsContent>> searchByTag(@PathVariable String tagId,
                                                          @RequestParam(defaultValue = "1") Integer currPage,
                                                          @RequestParam(defaultValue = "10") Integer size) {
        logger.info("根据标签搜索内容，标签ID: {}", tagId);
        Page<CmsContent> result = cmsSearchService.searchContentByTag(tagId, currPage, size);
        return ResponseBodyUtils.createBySuccess(result);
    }
    
    /**
     * 根据分类搜索内容
     */
    @PostMapping("category/{categoryId}")
    public ResponseBodyUtils<Page<CmsContent>> searchByCategory(@PathVariable String categoryId,
                                                               @RequestParam(defaultValue = "1") Integer currPage,
                                                               @RequestParam(defaultValue = "10") Integer size) {
        logger.info("根据分类搜索内容，分类ID: {}", categoryId);
        Page<CmsContent> result = cmsSearchService.searchContentByCategory(categoryId, currPage, size);
        return ResponseBodyUtils.createBySuccess(result);
    }
    
    /**
     * 高级搜索
     */
    @PostMapping("advanced")
    public ResponseBodyUtils<Page<CmsContent>> advancedSearch(@RequestParam(required = false) String keyword,
                                                             @RequestParam(required = false) String categoryId,
                                                             @RequestParam(required = false) String tagId,
                                                             @RequestParam(required = false) String contentType,
                                                             @RequestParam(defaultValue = "1") Integer currPage,
                                                             @RequestParam(defaultValue = "10") Integer size) {
        logger.info("高级搜索，关键词: {}, 分类: {}, 标签: {}, 类型: {}", keyword, categoryId, tagId, contentType);
        Page<CmsContent> result = cmsSearchService.advancedSearch(keyword, categoryId, tagId, contentType, currPage, size);
        return ResponseBodyUtils.createBySuccess(result);
    }
}