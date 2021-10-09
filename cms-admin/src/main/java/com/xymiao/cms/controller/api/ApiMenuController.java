package com.xymiao.cms.controller.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.service.CmsMenuService;
import com.xymiao.cms.util.ResponseBodyUtils;
import com.xymiao.cms.pojo.CmsMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单相关的业务功能接口
 */
@RestController
@RequestMapping("api/v1/menu")
public class ApiMenuController {
    private final static Logger logger = LoggerFactory.getLogger(ApiMenuController.class);
    private final CmsMenuService cmsMenuService;

    @Autowired
    public ApiMenuController(CmsMenuService cmsMenuService) {
        this.cmsMenuService = cmsMenuService;
    }

    /**
     * 获取单个菜单的详细信息
     * @param id 菜单主键
     * @return
     */
    @PostMapping("get/{id}")
    public ResponseBodyUtils<CmsMenu> getMenu(@PathVariable  String id){
        logger.info("获取菜单功能{}", id);
        CmsMenu cmsMenu = cmsMenuService.getMenu(id);
        return ResponseBodyUtils.createBySuccess(cmsMenu);
    }

    /**
     * 插入一个新的菜单
     * @param cmsMenu  菜单项目的实体类
     * @return  返回插入成功的菜单信息
     */
    @PostMapping(value="put")
    public ResponseBodyUtils<CmsMenu> putMenu(@RequestBody  CmsMenu cmsMenu){
        logger.info("获取菜单功能{}", cmsMenu);
        CmsMenu cm = cmsMenuService.saveMenu(cmsMenu);
        return ResponseBodyUtils.createBySuccess(cm);
    }

    /**
     * 编辑菜单信息
     * @param cmsMenu
     * @return
     */
    @PostMapping(value="edit")
    public ResponseBodyUtils<Object> editMenu(@RequestBody  CmsMenu cmsMenu){
        logger.info("获取修改菜单内容：{}", cmsMenu);
        int rows  = cmsMenuService.updateMenu(cmsMenu);
        return ResponseBodyUtils.createBySuccess(rows);
    }

    @PostMapping(value="del")
    public ResponseBodyUtils<Object> deleteMenu(@RequestBody  CmsMenu cmsMenu){
        logger.info("删除菜单信息：{}", cmsMenu);
        int rows  = cmsMenuService.delMenu(cmsMenu.getMenuId());
        return ResponseBodyUtils.createBySuccess(rows);
    }

    /**
     * 获得一组带有分页的菜单信息
     * @param module 模块类型
     * @param currPage 当前页
     * @return  菜单列表信息
     */
    @PostMapping("list/{module}")
    public ResponseBodyUtils<Page<CmsMenu>> listMenu(@PathVariable  String module, @RequestParam(defaultValue = "1") Integer currPage){
        logger.info("获取菜单列表功能， 根据模块： {}", module);
        Page<CmsMenu> cmsMenus = cmsMenuService.listMenu(module, "left_nav", currPage, 10);
        return ResponseBodyUtils.createBySuccess(cmsMenus);
    }

    /**
     * 加载菜单权限
     * @param module
     * @return
     */
    @PostMapping("show/{module}")
    public ResponseBodyUtils<List<CmsMenu>> showMenu(@PathVariable  String module){
        logger.info("获取菜单列表功能， 根据模块： {}", module);
        List<CmsMenu> cmsMenus = cmsMenuService.listMenuByUser("", module, "left_nav");
        return ResponseBodyUtils.createBySuccess(cmsMenus);
    }
}