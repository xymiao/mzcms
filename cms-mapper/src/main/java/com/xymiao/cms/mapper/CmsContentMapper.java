package com.xymiao.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xymiao.cms.pojo.CmsContent;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsContentMapper extends BaseMapper<CmsContent> {

    void updateViewCount(String id);
}
