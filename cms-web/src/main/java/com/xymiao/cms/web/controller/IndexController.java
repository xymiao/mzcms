package com.xymiao.cms.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.pojo.CmsContent;
import com.xymiao.cms.pojo.CmsMenu;
import com.xymiao.cms.service.CmsContentService;
import com.xymiao.cms.service.CmsMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import com.xymiao.cms.web.BaseController;

@Controller
public class IndexController extends BaseController {
    private CmsMenuService cmsMenuService;
    private final CmsContentService cmsContentService;

    public IndexController(CmsMenuService cmsMenuService, CmsContentService cmsContentService) {
        this.cmsMenuService = cmsMenuService;
        this.cmsContentService = cmsContentService;
    }

    @RequestMapping("")
    public String index(Model model) {
        logger.info("进入首页");
        List<CmsMenu> list = cmsMenuService.listMenuByFront("frontend");
        model.addAttribute("list", list);
        //加载首页的内容
        Page<CmsContent> cmsContentList = cmsContentService.queryContentByModule(1, 10, "");
        model.addAttribute("cmsContentList", cmsContentList);
        return RETURN_THEME + "index";
    }
    @RequestMapping("page/{current}")
    public String page(@PathVariable(required = false) Integer current, Model model) {
        logger.info("进入首页分页页面");
        List<CmsMenu> list = cmsMenuService.listMenuByFront("frontend");
        model.addAttribute("list", list);
        //加载首页的内容
        current = current == null ? 1: current;
        Page<CmsContent> cmsContentList = cmsContentService.queryContentByModule(current, 10, "");
        model.addAttribute("cmsContentList", cmsContentList);
        return RETURN_THEME + "index";
    }
}