package com.xymiao.cms.controller.api;

import com.xymiao.cms.service.CmsCategoryService;
import com.xymiao.cms.util.ResponseBodyUtils;
import com.xymiao.cms.pojo.CmsCategory;
import com.xymiao.cms.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 内容分类相关的业务功能接口
 */
@RestController
@RequestMapping("api/v1/category")
public class ApiCategoryController {
    private final static Logger logger = LoggerFactory.getLogger(ApiCategoryController.class);
    private final CmsCategoryService cmsCategoryService;
    @Autowired
    private UserUtils userUtils;

    @Autowired
    public ApiCategoryController(CmsCategoryService cmsCategoryService) {
        this.cmsCategoryService = cmsCategoryService;
    }

    /**
     * 获取单个菜单的详细信息
     * @param id 菜单主键
     * @return
     */
    @PostMapping("get/{id}")
    public ResponseBodyUtils<CmsCategory> getMenu(@PathVariable  String id){
        logger.info("获取分类功能{}", id);
        CmsCategory cmsCategory = cmsCategoryService.getCategory(id);
        return ResponseBodyUtils.createBySuccess(cmsCategory);
    }

    /**
     * 插入一个新的菜单
     * @param cmsCategory  菜单项目的实体类
     * @return  返回插入成功的菜单信息
     */
    @PostMapping(value="put")
    public ResponseBodyUtils<CmsCategory> putMenu(@RequestBody  CmsCategory cmsCategory){
        logger.info("获取修改分类功能{}", cmsCategory);
        cmsCategory.setUserId(userUtils.getUserId());
        CmsCategory cc = cmsCategoryService.saveCategory(cmsCategory);
        return ResponseBodyUtils.createBySuccess(cc);
    }

    /**
     * 编辑菜单信息
     * @param cmsCategory
     * @return
     */
    @PostMapping(value="edit")
    public ResponseBodyUtils<Object> editMenu(@RequestBody  CmsCategory cmsCategory){
        logger.info("获取修改分类内容：{}", cmsCategory);
        int rows  = cmsCategoryService.updateCategory(cmsCategory);
        return ResponseBodyUtils.createBySuccess(rows);
    }

    @PostMapping(value="del")
    public ResponseBodyUtils<Object> deleteMenu(@RequestBody  CmsCategory cmsCategory){
        logger.info("删除分类信息：{}", cmsCategory);
        int rows  = cmsCategoryService.delCategory(cmsCategory.getCategoryId());
        return ResponseBodyUtils.createBySuccess(rows);
    }

    /**
     * 获得一组带有分页的菜单信息
     * @param module 模块类型
     * @param currPage 当前页
     * @return  菜单列表信息
     */
    @PostMapping("list/{module}")
    public ResponseBodyUtils<List<CmsCategory>> listMenu(@PathVariable  String module, @RequestParam(defaultValue = "1") Integer currPage){
        logger.info("获取分类列表功能， 根据模块： {}", module);
        List<CmsCategory> cmsPage = cmsCategoryService.queryCategoryList();
        return ResponseBodyUtils.createBySuccess(cmsPage);
    }

    /**
     * 加载菜单权限
     * @param module
     * @return
     */
    @PostMapping("show/{module}")
    public ResponseBodyUtils<List<CmsCategory>> showMenu(@PathVariable  String module){
        logger.info("获取分类列表功能， 根据模块： {}", module);
        List<CmsCategory> cmsCategories = cmsCategoryService.listMenuByUser("", module, "left_nav");
        return ResponseBodyUtils.createBySuccess(cmsCategories);
    }
}