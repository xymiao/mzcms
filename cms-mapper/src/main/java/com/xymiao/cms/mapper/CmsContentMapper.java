package com.xymiao.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xymiao.cms.pojo.CmsContent;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsContentMapper extends BaseMapper<CmsContent> {

    void updateViewCount(String id);
    
    /**
     * 更新内容评论数
     */
    @Update("UPDATE cms_content SET comment_num = #{commentNum} WHERE content_id = #{contentId}")
    int updateCommentCount(@Param("contentId") String contentId, @Param("commentNum") Long commentNum);
}
