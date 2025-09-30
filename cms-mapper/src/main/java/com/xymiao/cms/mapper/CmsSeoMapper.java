package com.xymiao.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xymiao.cms.pojo.CmsSeo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CmsSeoMapper extends BaseMapper<CmsSeo> {
    
    /**
     * 根据内容ID和类型查询SEO信息
     */
    @Select("SELECT * FROM cms_seo WHERE content_id = #{contentId} AND content_type = #{contentType}")
    CmsSeo selectByContentIdAndType(@Param("contentId") String contentId, @Param("contentType") String contentType);
}