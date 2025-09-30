package com.xymiao.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xymiao.cms.pojo.CmsContentTag;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CmsContentTagMapper extends BaseMapper<CmsContentTag> {
    
    /**
     * 删除内容的所有标签关联
     */
    @Delete("DELETE FROM cms_content_tag WHERE content_id = #{contentId}")
    int deleteByContentId(@Param("contentId") String contentId);
    
    /**
     * 删除标签的所有内容关联
     */
    @Delete("DELETE FROM cms_content_tag WHERE tag_id = #{tagId}")
    int deleteByTagId(@Param("tagId") String tagId);
}