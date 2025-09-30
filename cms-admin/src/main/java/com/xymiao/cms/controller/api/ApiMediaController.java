package com.xymiao.cms.controller.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.pojo.CmsMedia;
import com.xymiao.cms.service.CmsMediaService;
import com.xymiao.cms.util.ResponseBodyUtils;
import com.xymiao.cms.util.UserUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 媒体文件管理API控制器
 */
@RestController
@RequestMapping("api/v1/media")
public class ApiMediaController {
    
    private final static Logger logger = LoggerFactory.getLogger(ApiMediaController.class);
    private final CmsMediaService cmsMediaService;
    private final UserUtils userUtils;
    
    public ApiMediaController(CmsMediaService cmsMediaService, UserUtils userUtils) {
        this.cmsMediaService = cmsMediaService;
        this.userUtils = userUtils;
    }
    
    /**
     * 上传文件
     */
    @PostMapping("upload")
    public ResponseBodyUtils<CmsMedia> uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("上传文件: {}", file.getOriginalFilename());
        CmsMedia media = cmsMediaService.uploadFile(file, userUtils.getUserId());
        return ResponseBodyUtils.createBySuccessCodeMessage("文件上传成功！", media);
    }
    
    /**
     * 获取媒体文件详情
     */
    @PostMapping("get/{id}")
    public ResponseBodyUtils<CmsMedia> getMedia(@PathVariable String id) {
        logger.info("获取媒体文件详情，ID: {}", id);
        CmsMedia media = cmsMediaService.getMedia(id);
        return ResponseBodyUtils.createBySuccess(media);
    }
    
    /**
     * 编辑媒体文件信息
     */
    @PostMapping("edit")
    public ResponseBodyUtils<Object> editMedia(@RequestBody CmsMedia media) {
        logger.info("编辑媒体文件: {}", media);
        int rows = cmsMediaService.updateMedia(media);
        return ResponseBodyUtils.createBySuccess(rows);
    }
    
    /**
     * 删除媒体文件
     */
    @PostMapping("del")
    public ResponseBodyUtils<Object> deleteMedia(@RequestBody CmsMedia media) {
        logger.info("删除媒体文件: {}", media);
        int rows = cmsMediaService.deleteMedia(media.getMediaId());
        return ResponseBodyUtils.createBySuccess(rows);
    }
    
    /**
     * 分页查询媒体文件
     */
    @PostMapping("list")
    public ResponseBodyUtils<Page<CmsMedia>> listMedia(@RequestParam(defaultValue = "1") Integer currPage,
                                                      @RequestParam(defaultValue = "10") Integer size,
                                                      @RequestParam(required = false) String mediaType) {
        logger.info("查询媒体文件列表，媒体类型: {}", mediaType);
        Page<CmsMedia> mediaPage = cmsMediaService.queryMediaList(currPage, size, mediaType);
        return ResponseBodyUtils.createBySuccess(mediaPage);
    }
    
    /**
     * 查询用户的媒体文件
     */
    @PostMapping("user/{userId}")
    public ResponseBodyUtils<List<CmsMedia>> getUserMedia(@PathVariable String userId) {
        logger.info("查询用户媒体文件，用户ID: {}", userId);
        List<CmsMedia> mediaList = cmsMediaService.getMediaByUserId(userId);
        return ResponseBodyUtils.createBySuccess(mediaList);
    }
}