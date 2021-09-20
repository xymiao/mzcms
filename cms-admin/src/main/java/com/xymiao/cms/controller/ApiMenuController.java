package com.xymiao.cms.controller;

import com.xymiao.cms.service.CmsMenuService;
import com.xymiao.cms.util.ResponseBodyUtils;
import com.xymiao.pojo.cms.CmsMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ApiMenuController {
    private final static Logger logger = LoggerFactory.getLogger(ApiMenuController.class);
    private final CmsMenuService cmsMenuService;

    @Autowired
    public ApiMenuController(CmsMenuService cmsMenuService) {
        this.cmsMenuService = cmsMenuService;
    }

    @GetMapping("api/v1/menu/t")
    public ResponseBodyUtils<CmsMenu> menutest(String id){
        logger.info("获取菜单功能{}", id);

        CmsMenu cmsMenu = cmsMenuService.getMenu(id);
        return ResponseBodyUtils.createBySuccess(cmsMenu);
    }
}