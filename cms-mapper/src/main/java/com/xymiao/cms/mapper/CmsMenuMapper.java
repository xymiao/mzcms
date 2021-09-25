package com.xymiao.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xymiao.cms.pojo.CmsMenu;

import java.util.List;


public interface CmsMenuMapper extends BaseMapper<CmsMenu> {

    List<CmsMenu> queryMenuList(String menuModule);
}
