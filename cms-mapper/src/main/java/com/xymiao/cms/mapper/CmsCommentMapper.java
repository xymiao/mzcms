package com.xymiao.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.pojo.CmsComment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CmsCommentMapper extends BaseMapper<CmsComment> {
    
    /**
     * 根据内容ID分页查询评论
     */
    @Select("SELECT * FROM cms_comment WHERE content_id = #{contentId} AND del_flag = '0' ORDER BY created DESC")
    Page<CmsComment> selectCommentsByContentId(Page<CmsComment> page, @Param("contentId") String contentId);
    
    /**
     * 根据父评论ID查询子评论
     */
    @Select("SELECT * FROM cms_comment WHERE parent_id = #{parentId} AND del_flag = '0' ORDER BY created ASC")
    List<CmsComment> selectRepliesByParentId(@Param("parentId") String parentId);
    
    /**
     * 更新评论状态
     */
    @Update("UPDATE cms_comment SET comment_status = #{status} WHERE comment_id = #{commentId}")
    int updateCommentStatus(@Param("commentId") String commentId, @Param("status") String status);
    
    /**
     * 统计内容的评论数
     */
    @Select("SELECT COUNT(*) FROM cms_comment WHERE content_id = #{contentId} AND comment_status = '1' AND del_flag = '0'")
    Long countCommentsByContentId(@Param("contentId") String contentId);
}