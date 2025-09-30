package com.xymiao.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.pojo.CmsComment;

import java.util.List;

public interface CmsCommentService {
    
    /**
     * 保存评论
     */
    CmsComment saveComment(CmsComment comment);
    
    /**
     * 根据内容ID分页查询评论
     */
    Page<CmsComment> getCommentsByContentId(Integer current, Integer size, String contentId);
    
    /**
     * 根据父评论ID查询回复
     */
    List<CmsComment> getRepliesByParentId(String parentId);
    
    /**
     * 审核评论
     */
    int approveComment(String commentId, String status);
    
    /**
     * 删除评论
     */
    int deleteComment(String commentId);
    
    /**
     * 根据ID获取评论
     */
    CmsComment getComment(String commentId);
    
    /**
     * 更新评论
     */
    int updateComment(CmsComment comment);
    
    /**
     * 统计内容的评论数
     */
    Long countCommentsByContentId(String contentId);
}