package com.xymiao.cms.controller;

import com.xymiao.cms.service.CmsMenuService;
import com.xymiao.pojo.cms.CmsMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ApiMenuController {
    private final CmsMenuService cmsMenuService;
    @Autowired
    public ApiMenuController(CmsMenuService cmsMenuService) {
        this.cmsMenuService = cmsMenuService;
    }

    @GetMapping("api/v1/menu/t")
    public String menutest(String id){
        CmsMenu cmsMenu = cmsMenuService.getMenu(id);
        return cmsMenu != null ? cmsMenu.toString() : "无数据";
    }
}