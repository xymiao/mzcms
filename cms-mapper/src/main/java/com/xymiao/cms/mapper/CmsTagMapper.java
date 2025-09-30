package com.xymiao.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xymiao.cms.pojo.CmsTag;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CmsTagMapper extends BaseMapper<CmsTag> {
    
    /**
     * 根据内容ID查询标签
     */
    @Select("SELECT t.* FROM cms_tag t INNER JOIN cms_content_tag ct ON t.tag_id = ct.tag_id WHERE ct.content_id = #{contentId} AND t.del_flag = '0'")
    List<CmsTag> selectTagsByContentId(@Param("contentId") String contentId);
    
    /**
     * 根据标签名查询标签
     */
    @Select("SELECT * FROM cms_tag WHERE tag_name = #{tagName} AND del_flag = '0'")
    CmsTag selectByTagName(@Param("tagName") String tagName);
    
    /**
     * 根据标签slug查询标签
     */
    @Select("SELECT * FROM cms_tag WHERE tag_slug = #{tagSlug} AND del_flag = '0'")
    CmsTag selectByTagSlug(@Param("tagSlug") String tagSlug);
    
    /**
     * 更新标签使用次数
     */
    @Update("UPDATE cms_tag SET use_count = use_count + #{count} WHERE tag_id = #{tagId}")
    int updateUseCount(@Param("tagId") String tagId, @Param("count") int count);
    
    /**
     * 查询热门标签
     */
    @Select("SELECT * FROM cms_tag WHERE del_flag = '0' ORDER BY use_count DESC LIMIT #{limit}")
    List<CmsTag> selectHotTags(@Param("limit") int limit);
}