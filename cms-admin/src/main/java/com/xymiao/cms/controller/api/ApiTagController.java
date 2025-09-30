package com.xymiao.cms.controller.api;

import com.xymiao.cms.pojo.CmsTag;
import com.xymiao.cms.service.CmsTagService;
import com.xymiao.cms.util.ResponseBodyUtils;
import com.xymiao.cms.util.UserUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签管理API控制器
 */
@RestController
@RequestMapping("api/v1/tag")
public class ApiTagController {
    
    private final static Logger logger = LoggerFactory.getLogger(ApiTagController.class);
    private final CmsTagService cmsTagService;
    private final UserUtils userUtils;
    
    public ApiTagController(CmsTagService cmsTagService, UserUtils userUtils) {
        this.cmsTagService = cmsTagService;
        this.userUtils = userUtils;
    }
    
    /**
     * 获取标签详情
     */
    @PostMapping("get/{id}")
    public ResponseBodyUtils<CmsTag> getTag(@PathVariable String id) {
        logger.info("获取标签详情，ID: {}", id);
        CmsTag tag = cmsTagService.getTag(id);
        return ResponseBodyUtils.createBySuccess(tag);
    }
    
    /**
     * 添加标签
     */
    @PostMapping("put")
    public ResponseBodyUtils<CmsTag> addTag(@RequestBody CmsTag tag) {
        logger.info("添加标签: {}", tag);
        tag.setUserId(userUtils.getUserId());
        CmsTag savedTag = cmsTagService.saveTag(tag);
        return ResponseBodyUtils.createBySuccessCodeMessage("标签添加成功！", savedTag);
    }
    
    /**
     * 编辑标签
     */
    @PostMapping("edit")
    public ResponseBodyUtils<Object> editTag(@RequestBody CmsTag tag) {
        logger.info("编辑标签: {}", tag);
        int rows = cmsTagService.updateTag(tag);
        return ResponseBodyUtils.createBySuccess(rows);
    }
    
    /**
     * 删除标签
     */
    @PostMapping("del")
    public ResponseBodyUtils<Object> deleteTag(@RequestBody CmsTag tag) {
        logger.info("删除标签: {}", tag);
        int rows = cmsTagService.deleteTag(tag.getTagId());
        return ResponseBodyUtils.createBySuccess(rows);
    }
    
    /**
     * 查询所有标签
     */
    @PostMapping("list")
    public ResponseBodyUtils<List<CmsTag>> listTags() {
        logger.info("查询所有标签");
        List<CmsTag> tags = cmsTagService.queryAllTags();
        return ResponseBodyUtils.createBySuccess(tags);
    }
    
    /**
     * 查询热门标签
     */
    @PostMapping("hot")
    public ResponseBodyUtils<List<CmsTag>> getHotTags(@RequestParam(defaultValue = "10") Integer limit) {
        logger.info("查询热门标签，数量: {}", limit);
        List<CmsTag> tags = cmsTagService.getHotTags(limit);
        return ResponseBodyUtils.createBySuccess(tags);
    }
    
    /**
     * 查询内容的标签
     */
    @PostMapping("content/{contentId}")
    public ResponseBodyUtils<List<CmsTag>> getContentTags(@PathVariable String contentId) {
        logger.info("查询内容标签，内容ID: {}", contentId);
        List<CmsTag> tags = cmsTagService.getTagsByContentId(contentId);
        return ResponseBodyUtils.createBySuccess(tags);
    }
}