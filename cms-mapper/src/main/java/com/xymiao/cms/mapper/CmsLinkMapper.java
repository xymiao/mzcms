package com.xymiao.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xymiao.cms.pojo.CmsLink;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CmsLinkMapper extends BaseMapper<CmsLink> {
    
    /**
     * 根据链接类型查询友情链接
     */
    @Select("SELECT * FROM cms_link WHERE link_type = #{linkType} AND link_status = '1' AND del_flag = '0' ORDER BY sort_order ASC")
    List<CmsLink> selectByLinkType(@Param("linkType") String linkType);
    
    /**
     * 根据状态查询友情链接
     */
    @Select("SELECT * FROM cms_link WHERE link_status = #{status} AND del_flag = '0' ORDER BY created DESC")
    List<CmsLink> selectByStatus(@Param("status") String status);
    
    /**
     * 更新友情链接状态
     */
    @Update("UPDATE cms_link SET link_status = #{status} WHERE link_id = #{linkId}")
    int updateLinkStatus(@Param("linkId") String linkId, @Param("status") String status);
}