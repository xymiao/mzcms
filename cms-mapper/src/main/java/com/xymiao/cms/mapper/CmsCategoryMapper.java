package com.xymiao.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xymiao.cms.pojo.CmsCategory;

import java.util.List;


public interface CmsCategoryMapper extends BaseMapper<CmsCategory> {

    List<CmsCategory> queryCategoryList(String menuModule);
}
