package com.xymiao.cms.service;

import com.xymiao.cms.mapper.CmsMenuMapper;
import com.xymiao.pojo.cms.CmsMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CmsMenuServiceImpl")
public class CmsMenuServiceImpl  implements  CmsMenuService{
    private  CmsMenuMapper cmsMenuMapper;
    @Autowired
    public CmsMenuServiceImpl(CmsMenuMapper cmsMenuMapper) {
        this.cmsMenuMapper = cmsMenuMapper;
    }

    @Override
    public CmsMenu getMenu(String menuId) {
        return cmsMenuMapper.selectById(menuId);
    }

    @Override
    public List<CmsMenu> listMenu(String type) {
        return null;
    }

    @Override
    public int updateMenu(CmsMenu cmsMenu) {
        return 0;
    }

    @Override
    public int delMenu(String menuId) {
        return 0;
    }
}
