package com.xymiao.cms.controller.api;

import com.xymiao.cms.pojo.CmsContent;
import com.xymiao.cms.service.CmsContentService;
import com.xymiao.cms.util.ResponseBodyUtils;
import com.xymiao.cms.util.UserUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/content")
public class ApiContentController {
    private final static Logger logger = LoggerFactory.getLogger(ApiContentController.class);
    private  CmsContentService cmsContentService;
    @Autowired
    private  UserUtils userUtils;
    @Autowired
    public ApiContentController(CmsContentService cmsContentService) {
        this.cmsContentService = cmsContentService;
    }

    @PostMapping("list/{categoryId}")
    public ResponseBodyUtils<Object> listContent(@PathVariable String categoryId, @RequestParam(defaultValue = "1") Integer currPage){
        logger.info("根据分类{}，获取内容信息。", categoryId);
        List<CmsContent> cmsPage = cmsContentService.queryContentByCategory(categoryId);
        return ResponseBodyUtils.createBySuccess(cmsPage);
    }
    
    @PostMapping("get/{contentId}")
    public ResponseBodyUtils<Object> getContent(@PathVariable String contentId, @RequestParam(defaultValue = "1") Integer currPage){
        logger.info("根据内容主键 {}，获取内容信息。", contentId);
        CmsContent cmsContent = cmsContentService.getContent(contentId);
        return ResponseBodyUtils.createBySuccess(cmsContent);
    }
    /**
    * 插入新内容
    * @param cmsContent  内容的实体类
    * @return  返回插入成功的内容
    */
   @PostMapping(value="put")
   public ResponseBodyUtils<Object> putMenu(@RequestBody  CmsContent cmsContent){
       logger.info("新增内容 {}", cmsContent);
       cmsContent.setPublishUser(userUtils.getUserId());
       CmsContent cc = cmsContentService.saveContent(cmsContent);
       return ResponseBodyUtils.createBySuccessCodeMessage("内容保存成功！", cc);
   }
   
   /**
    * 编辑内容
    * @param cmsContent
    * @return
    */
   @PostMapping(value="edit")
   public ResponseBodyUtils<Object> editMenu(@RequestBody  CmsContent cmsContent){
       logger.info("获取修改内容：{}", cmsContent);
       int rows  = cmsContentService.updateCotent(cmsContent);
       String msg = rows > 0 ? "修改成功" : "修改失败";
       return ResponseBodyUtils.createBySuccessCodeMessage(msg, rows);
   }
   
}
