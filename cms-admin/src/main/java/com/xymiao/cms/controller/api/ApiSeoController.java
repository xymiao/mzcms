package com.xymiao.cms.controller.api;

import com.xymiao.cms.pojo.CmsSeo;
import com.xymiao.cms.service.CmsSeoService;
import com.xymiao.cms.util.ResponseBodyUtils;
import com.xymiao.cms.util.UserUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * SEO管理API控制器
 */
@RestController
@RequestMapping("api/v1/seo")
public class ApiSeoController {
    
    private final static Logger logger = LoggerFactory.getLogger(ApiSeoController.class);
    private final CmsSeoService cmsSeoService;
    private final UserUtils userUtils;
    
    public ApiSeoController(CmsSeoService cmsSeoService, UserUtils userUtils) {
        this.cmsSeoService = cmsSeoService;
        this.userUtils = userUtils;
    }
    
    /**
     * 获取SEO信息详情
     */
    @PostMapping("get/{id}")
    public ResponseBodyUtils<CmsSeo> getSeo(@PathVariable String id) {
        logger.info("获取SEO信息详情，ID: {}", id);
        CmsSeo seo = cmsSeoService.getSeo(id);
        return ResponseBodyUtils.createBySuccess(seo);
    }
    
    /**
     * 根据内容ID和类型获取SEO信息
     */
    @PostMapping("content/{contentId}/{contentType}")
    public ResponseBodyUtils<CmsSeo> getSeoByContent(@PathVariable String contentId, @PathVariable String contentType) {
        logger.info("根据内容获取SEO信息，内容ID: {}, 类型: {}", contentId, contentType);
        CmsSeo seo = cmsSeoService.getSeoByContentIdAndType(contentId, contentType);
        return ResponseBodyUtils.createBySuccess(seo);
    }
    
    /**
     * 设置内容的SEO信息
     */
    @PostMapping("set")
    public ResponseBodyUtils<CmsSeo> setSeoForContent(@RequestBody CmsSeo seo) {
        logger.info("设置内容SEO信息: {}", seo);
        seo.setUserId(userUtils.getUserId());
        CmsSeo savedSeo = cmsSeoService.setSeoForContent(seo.getContentId(), seo.getContentType(), seo);
        return ResponseBodyUtils.createBySuccessCodeMessage("SEO信息设置成功！", savedSeo);
    }
    
    /**
     * 编辑SEO信息
     */
    @PostMapping("edit")
    public ResponseBodyUtils<Object> editSeo(@RequestBody CmsSeo seo) {
        logger.info("编辑SEO信息: {}", seo);
        int rows = cmsSeoService.updateSeo(seo);
        return ResponseBodyUtils.createBySuccess(rows);
    }
    
    /**
     * 删除SEO信息
     */
    @PostMapping("del")
    public ResponseBodyUtils<Object> deleteSeo(@RequestBody CmsSeo seo) {
        logger.info("删除SEO信息: {}", seo);
        int rows = cmsSeoService.deleteSeo(seo.getSeoId());
        return ResponseBodyUtils.createBySuccess(rows);
    }
}