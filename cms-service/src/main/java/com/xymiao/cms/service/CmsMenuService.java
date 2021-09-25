package com.xymiao.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.pojo.CmsMenu;

/**
 * 菜单相关的业务处理
 */
public interface CmsMenuService {
    /**
     * 保存菜单信息
     * @param cmsMenu 菜单实体类
     * @return 返回保存的信息
     */
    CmsMenu saveMenu(CmsMenu cmsMenu);
    /**
     * 获取一个菜单详细信息
     * @param menuId 菜单主键
     * @return 存在的菜单信息
     */
    CmsMenu getMenu(String menuId);

    /**
     * 根据模块和类型加载菜单信息
     * @param module  模块主键
     * @param type  类型属性
     * @return 对应的菜单信息
     */
    Page<CmsMenu> listMenu(String module, String type, Integer current, Integer size);

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
