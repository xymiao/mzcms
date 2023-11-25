package com.xymiao.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xymiao.cms.pojo.CmsMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsMenuMapper extends BaseMapper<CmsMenu> {

    List<CmsMenu> queryMenuList(String menuModule);
}
