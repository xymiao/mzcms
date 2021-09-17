package com.xymiao.cms.service;

import com.xymiao.pojo.cms.CmsMenu;

import java.util.List;

public interface CmsMenuService {
    /**
     * 获取一个菜单详细信息
     * @param menuId 菜单主键
     * @return 存在的菜单信息
     */
    CmsMenu getMenu(String menuId);

    /**
     * 根据类型加载对应的菜单信息
     * @param type  定义的类型
     * @return 菜单列表信息
     */
    List<CmsMenu> listMenu(String type);

    /**
     * 修改菜单信息
     * @param cmsMenu 菜单实体类
     * @return 修改受影响的行数
     */
    int updateMenu(CmsMenu cmsMenu);

    /**
     * 根据业务主键删除对应的菜单信息
     * @param menuId 菜单主键
     * @return 受影响的行数
     */
    int delMenu(String menuId);
}
