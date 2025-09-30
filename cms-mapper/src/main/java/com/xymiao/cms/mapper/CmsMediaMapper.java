package com.xymiao.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.pojo.CmsMedia;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CmsMediaMapper extends BaseMapper<CmsMedia> {
    
    /**
     * 根据媒体类型分页查询
     */
    @Select("SELECT * FROM cms_media WHERE media_type = #{mediaType} AND del_flag = '0' ORDER BY created DESC")
    Page<CmsMedia> selectByMediaType(Page<CmsMedia> page, @Param("mediaType") String mediaType);
    
    /**
     * 根据用户ID查询媒体文件
     */
    @Select("SELECT * FROM cms_media WHERE user_id = #{userId} AND del_flag = '0' ORDER BY created DESC")
    List<CmsMedia> selectByUserId(@Param("userId") String userId);
    
    /**
     * 根据文件类型查询媒体文件
     */
    @Select("SELECT * FROM cms_media WHERE file_type = #{fileType} AND del_flag = '0' ORDER BY created DESC")
    List<CmsMedia> selectByFileType(@Param("fileType") String fileType);
}