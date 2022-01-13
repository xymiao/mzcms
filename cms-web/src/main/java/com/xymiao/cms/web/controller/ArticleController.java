package com.xymiao.cms.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.pojo.CmsCategory;
import com.xymiao.cms.pojo.CmsContent;
import com.xymiao.cms.pojo.CmsMenu;
import com.xymiao.cms.service.CmsCategoryService;
import com.xymiao.cms.service.CmsContentService;
import com.xymiao.cms.service.CmsMenuService;
import com.xymiao.cms.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("archives")
public class ArticleController extends BaseController {

    private final CmsMenuService cmsMenuService;
    private final CmsContentService cmsContentService;
    private final CmsCategoryService cmsCategoryService;


    public ArticleController(CmsMenuService cmsMenuService, CmsContentService cmsContentService, CmsCategoryService cmsCategoryService) {
        this.cmsMenuService = cmsMenuService;
        this.cmsContentService = cmsContentService;
        this.cmsCategoryService = cmsCategoryService;
    }

    @RequestMapping("{id}")
    public String detail(Model model, @PathVariable String id) {
        logger.info("进入文章内容");
        List<CmsMenu> list = cmsMenuService.listMenuByFront("frontend");
        model.addAttribute("list", list);
        CmsContent cmsContent = cmsContentService.getContent(id);
        model.addAttribute("cmsContent", cmsContent);
        if(cmsContent != null){
            List<CmsCategory> categoryList = cmsCategoryService.listByContentId(cmsContent.getContentId());
            if(!categoryList.isEmpty()){
                model.addAttribute("categoryName",categoryList.get(0).getName());
            }
        }
        return RETURN_THEME + "archives";
    }

    @RequestMapping("category/{id}")
    public String category(Model model, @PathVariable String id) {
        logger.info("进入分类列表");
        pageCategory(1, id, model);
        return RETURN_THEME + "category";
    }
    private String addCategory(String id, Model model){
        CmsCategory cmsCategory = cmsCategoryService.getCategoryByUrl(id);
        model.addAttribute("category", cmsCategory);
        model.addAttribute("categoryName", cmsCategory.getName());
        model.addAttribute("categoryId", cmsCategory.getCategoryId());
        if(cmsCategory != null){
            Map<String, CmsCategory> map = new HashMap<>();
            map.put(cmsCategory.getCategoryId(), cmsCategory);
            model.addAttribute("categoryMap", map);
        }
        return cmsCategory.getCategoryId();
    }
    @RequestMapping("category/{id}/{subId}")
    public String categorySub(Model model, @PathVariable String id, @PathVariable String subId) {
        logger.info("进入子分类列表");
        pageCategorySub(1, id, subId, model);
        return RETURN_THEME + "category";
    }

    @RequestMapping("date/{year}/{month}")
    public String date(Model model, @PathVariable String year, @PathVariable String month) {
        logger.info("进入日期归档");
        List<CmsMenu> list = cmsMenuService.listMenuByFront("frontend");
        model.addAttribute("list", list);
        model.addAttribute("categoryName", year + "年"+ month + "月");

        Page<CmsContent> cmsContentList =  cmsContentService.queryContentByModule(1, 10, "");
        model.addAttribute("cmsContentList", cmsContentList);
        return RETURN_THEME + "category";
    }

    @RequestMapping("category/{id}/page/{current}")
    public String pageCategory(@PathVariable(required = false) Integer current,@PathVariable String id, Model model) {
        logger.info("进入分类的分页页面");
        List<CmsMenu> list = cmsMenuService.listMenuByFront("frontend");
        model.addAttribute("list", list);

        model.addAttribute("id", id);
        String encodeId = "", categoryId = "";
        try {
            encodeId = URLEncoder.encode(id, "utf-8");
            categoryId = addCategory(encodeId, model);
        } catch (UnsupportedEncodingException e) {
        }

        //加载分页的内容
        current = current == null ? 1: current;
        Page<CmsContent> cmsContentList =  cmsContentService.queryContentByModuleAndId(current, 10, "", categoryId);
        model.addAttribute("cmsContentList", cmsContentList);
        return RETURN_THEME + "category";
    }

    @RequestMapping("category/{id}/{subId}/page/{current}")
    public String pageCategorySub(@PathVariable(required = false) Integer current,@PathVariable String id,@PathVariable String subId, Model model) {
        logger.info("进入子分类的分页页面");
        List<CmsMenu> list = cmsMenuService.listMenuByFront("frontend");
        model.addAttribute("list", list);

        model.addAttribute("id", id);

        addCategory(subId, model);
        CmsCategory cmsCategory = cmsCategoryService.getCategoryByUrl(id);
        model.addAttribute("category", cmsCategory);

        model.addAttribute("subId", subId);
        CmsCategory subCmsCategory = cmsCategoryService.getCategoryByUrl(subId);
        model.addAttribute("subCategory", subCmsCategory);
        model.addAttribute("categoryName", subCmsCategory.getName());
        model.addAttribute("categoryId", subCmsCategory.getCategoryId());
        //加载分页的内容
        current = current == null ? 1: current;
        Page<CmsContent> cmsContentList =  cmsContentService.queryContentByModuleAndId(1, 10, "", subCmsCategory.getCategoryId());
        model.addAttribute("cmsContentList", cmsContentList);
        return RETURN_THEME + "category";
    }

}
