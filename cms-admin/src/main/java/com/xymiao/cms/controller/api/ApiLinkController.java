package com.xymiao.cms.controller.api;

import com.xymiao.cms.pojo.CmsLink;
import com.xymiao.cms.service.CmsLinkService;
import com.xymiao.cms.util.ResponseBodyUtils;
import com.xymiao.cms.util.UserUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 友情链接管理API控制器
 */
@RestController
@RequestMapping("api/v1/link")
public class ApiLinkController {
    
    private final static Logger logger = LoggerFactory.getLogger(ApiLinkController.class);
    private final CmsLinkService cmsLinkService;
    private final UserUtils userUtils;
    
    public ApiLinkController(CmsLinkService cmsLinkService, UserUtils userUtils) {
        this.cmsLinkService = cmsLinkService;
        this.userUtils = userUtils;
    }
    
    /**
     * 获取友情链接详情
     */
    @PostMapping("get/{id}")
    public ResponseBodyUtils<CmsLink> getLink(@PathVariable String id) {
        logger.info("获取友情链接详情，ID: {}", id);
        CmsLink link = cmsLinkService.getLink(id);
        return ResponseBodyUtils.createBySuccess(link);
    }
    
    /**
     * 添加友情链接
     */
    @PostMapping("put")
    public ResponseBodyUtils<CmsLink> addLink(@RequestBody CmsLink link) {
        logger.info("添加友情链接: {}", link);
        link.setUserId(userUtils.getUserId());
        CmsLink savedLink = cmsLinkService.saveLink(link);
        return ResponseBodyUtils.createBySuccessCodeMessage("友情链接添加成功！", savedLink);
    }
    
    /**
     * 编辑友情链接
     */
    @PostMapping("edit")
    public ResponseBodyUtils<Object> editLink(@RequestBody CmsLink link) {
        logger.info("编辑友情链接: {}", link);
        int rows = cmsLinkService.updateLink(link);
        return ResponseBodyUtils.createBySuccess(rows);
    }
    
    /**
     * 删除友情链接
     */
    @PostMapping("del")
    public ResponseBodyUtils<Object> deleteLink(@RequestBody CmsLink link) {
        logger.info("删除友情链接: {}", link);
        int rows = cmsLinkService.deleteLink(link.getLinkId());
        return ResponseBodyUtils.createBySuccess(rows);
    }
    
    /**
     * 查询所有友情链接
     */
    @PostMapping("list")
    public ResponseBodyUtils<List<CmsLink>> listLinks() {
        logger.info("查询所有友情链接");
        List<CmsLink> links = cmsLinkService.queryAllLinks();
        return ResponseBodyUtils.createBySuccess(links);
    }
    
    /**
     * 根据类型查询友情链接
     */
    @PostMapping("type/{linkType}")
    public ResponseBodyUtils<List<CmsLink>> getLinksByType(@PathVariable String linkType) {
        logger.info("根据类型查询友情链接，类型: {}", linkType);
        List<CmsLink> links = cmsLinkService.getLinksByType(linkType);
        return ResponseBodyUtils.createBySuccess(links);
    }
    
    /**
     * 审核友情链接
     */
    @PostMapping("approve")
    public ResponseBodyUtils<Object> approveLink(@RequestBody CmsLink link) {
        logger.info("审核友情链接: {}", link);
        int rows = cmsLinkService.approveLink(link.getLinkId(), link.getLinkStatus());
        return ResponseBodyUtils.createBySuccess(rows);
    }
}