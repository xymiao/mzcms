package com.xymiao.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xymiao.cms.pojo.CmsMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsMenuMapper extends BaseMapper<CmsMenu> {

    List<CmsMenu> queryMenuList(String menuModule);
}
