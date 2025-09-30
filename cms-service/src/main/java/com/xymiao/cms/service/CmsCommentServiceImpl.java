package com.xymiao.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.mapper.CmsCommentMapper;
import com.xymiao.cms.pojo.CmsComment;
import com.xymiao.common.exception.BusinessException;
import com.xymiao.common.utils.IdsUtils;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("cmsCommentServiceImpl")
public class CmsCommentServiceImpl implements CmsCommentService {
    
    private final CmsCommentMapper cmsCommentMapper;
    
    public CmsCommentServiceImpl(CmsCommentMapper cmsCommentMapper) {
        this.cmsCommentMapper = cmsCommentMapper;
    }
    
    @Override
    public CmsComment saveComment(CmsComment comment) {
        LocalDateTime currDate = LocalDateTime.now();
        
        comment.setCommentId(IdsUtils.getId());
        comment.setCreated(currDate);
        comment.setCommentStatus("0"); // 默认待审核
        comment.setDelFlag("0");
        
        int rows = cmsCommentMapper.insert(comment);
        if (rows <= 0) {
            throw new BusinessException("评论保存失败！");
        }
        return comment;
    }
    
    @Override
    public Page<CmsComment> getCommentsByContentId(Integer current, Integer size, String contentId) {
        Page<CmsComment> page = new Page<>(current, size);
        return cmsCommentMapper.selectCommentsByContentId(page, contentId);
    }
    
    @Override
    public List<CmsComment> getRepliesByParentId(String parentId) {
        return cmsCommentMapper.selectRepliesByParentId(parentId);
    }
    
    @Override
    public int approveComment(String commentId, String status) {
        return cmsCommentMapper.updateCommentStatus(commentId, status);
    }
    
    @Override
    public int deleteComment(String commentId) {
        CmsComment comment = new CmsComment();
        comment.setCommentId(commentId);
        comment.setDelFlag("1");
        return cmsCommentMapper.updateById(comment);
    }
    
    @Override
    public CmsComment getComment(String commentId) {
        return cmsCommentMapper.selectById(commentId);
    }
    
    @Override
    public int updateComment(CmsComment comment) {
        comment.setUpdated(LocalDateTime.now());
        return cmsCommentMapper.updateById(comment);
    }
    
    @Override
    public Long countCommentsByContentId(String contentId) {
        return cmsCommentMapper.countCommentsByContentId(contentId);
    }
}