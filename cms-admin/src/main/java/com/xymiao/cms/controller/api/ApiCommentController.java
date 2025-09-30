package com.xymiao.cms.controller.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.pojo.CmsComment;
import com.xymiao.cms.service.CmsCommentService;
import com.xymiao.cms.util.ResponseBodyUtils;
import com.xymiao.cms.util.UserUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论管理API控制器
 */
@RestController
@RequestMapping("api/v1/comment")
public class ApiCommentController {
    
    private final static Logger logger = LoggerFactory.getLogger(ApiCommentController.class);
    private final CmsCommentService cmsCommentService;
    private final UserUtils userUtils;
    
    public ApiCommentController(CmsCommentService cmsCommentService, UserUtils userUtils) {
        this.cmsCommentService = cmsCommentService;
        this.userUtils = userUtils;
    }
    
    /**
     * 获取评论详情
     */
    @PostMapping("get/{id}")
    public ResponseBodyUtils<CmsComment> getComment(@PathVariable String id) {
        logger.info("获取评论详情，ID: {}", id);
        CmsComment comment = cmsCommentService.getComment(id);
        return ResponseBodyUtils.createBySuccess(comment);
    }
    
    /**
     * 添加评论
     */
    @PostMapping("put")
    public ResponseBodyUtils<CmsComment> addComment(@RequestBody CmsComment comment) {
        logger.info("添加评论: {}", comment);
        comment.setUserId(userUtils.getUserId());
        CmsComment savedComment = cmsCommentService.saveComment(comment);
        return ResponseBodyUtils.createBySuccessCodeMessage("评论添加成功！", savedComment);
    }
    
    /**
     * 审核评论
     */
    @PostMapping("approve")
    public ResponseBodyUtils<Object> approveComment(@RequestBody CmsComment comment) {
        logger.info("审核评论: {}", comment);
        int rows = cmsCommentService.approveComment(comment.getCommentId(), comment.getCommentStatus());
        return ResponseBodyUtils.createBySuccess(rows);
    }
    
    /**
     * 删除评论
     */
    @PostMapping("del")
    public ResponseBodyUtils<Object> deleteComment(@RequestBody CmsComment comment) {
        logger.info("删除评论: {}", comment);
        int rows = cmsCommentService.deleteComment(comment.getCommentId());
        return ResponseBodyUtils.createBySuccess(rows);
    }
    
    /**
     * 分页查询内容的评论
     */
    @PostMapping("list/{contentId}")
    public ResponseBodyUtils<Page<CmsComment>> listComments(@PathVariable String contentId, 
                                                           @RequestParam(defaultValue = "1") Integer currPage,
                                                           @RequestParam(defaultValue = "10") Integer size) {
        logger.info("查询内容评论列表，内容ID: {}", contentId);
        Page<CmsComment> comments = cmsCommentService.getCommentsByContentId(currPage, size, contentId);
        return ResponseBodyUtils.createBySuccess(comments);
    }
    
    /**
     * 查询评论回复
     */
    @PostMapping("replies/{parentId}")
    public ResponseBodyUtils<List<CmsComment>> getReplies(@PathVariable String parentId) {
        logger.info("查询评论回复，父评论ID: {}", parentId);
        List<CmsComment> replies = cmsCommentService.getRepliesByParentId(parentId);
        return ResponseBodyUtils.createBySuccess(replies);
    }
}