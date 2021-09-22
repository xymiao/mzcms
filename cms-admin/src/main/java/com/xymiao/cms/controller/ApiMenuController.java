package com.xymiao.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.service.CmsMenuService;
import com.xymiao.cms.util.ResponseBodyUtils;
import com.xymiao.pojo.cms.CmsMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/menu")
public class ApiMenuController {
    private final static Logger logger = LoggerFactory.getLogger(ApiMenuController.class);
    private final CmsMenuService cmsMenuService;

    @Autowired
    public ApiMenuController(CmsMenuService cmsMenuService) {
        this.cmsMenuService = cmsMenuService;
    }

    @PostMapping("get/{id}")
    public ResponseBodyUtils<CmsMenu> getMenu(@PathVariable  String id){
        logger.info("获取菜单功能{}", id);
        CmsMenu cmsMenu = cmsMenuService.getMenu(id);
        return ResponseBodyUtils.createBySuccess(cmsMenu);
    }

    @PostMapping(value="put")
    public ResponseBodyUtils<CmsMenu> putMenu(@RequestBody  CmsMenu cmsMenu){
        logger.info("获取菜单功能{}", cmsMenu);
        CmsMenu cm = cmsMenuService.saveMenu(cmsMenu);
        return ResponseBodyUtils.createBySuccess(cm);
    }

    @PostMapping("list/{module}")
    public ResponseBodyUtils<Page<CmsMenu>> listMenu(@PathVariable  String module, @RequestParam(defaultValue = "1") Integer currPage){
        logger.info("获取菜单列表功能， 根据模块： {}", module);
        Page<CmsMenu> cmsMenus = cmsMenuService.listMenu(module, "left_nav", currPage, 10);
        return ResponseBodyUtils.createBySuccess(cmsMenus);
    }
}