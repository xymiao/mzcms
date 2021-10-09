package com.xymiao.cms.controller.api;

import com.xymiao.cms.service.SysSiteSettingService;
import com.xymiao.cms.util.ResponseBodyUtils;
import com.xymiao.cms.pojo.SysSiteSetting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 菜单相关的业务功能接口
 */
@RestController
@RequestMapping("api/v1/site")
public class ApiSiteSettingController {
    private final static Logger logger = LoggerFactory.getLogger(ApiSiteSettingController.class);
    private final SysSiteSettingService sysSiteSettingService;

    @Autowired
    public ApiSiteSettingController(SysSiteSettingService sysSiteSettingService) {
        this.sysSiteSettingService = sysSiteSettingService;
    }
    
    @PostMapping(value="put")
    public ResponseBodyUtils<Object> putMenu(@RequestBody  SysSiteSetting sysSiteSetting){
        logger.info("保存一个配置： {}", sysSiteSetting);
        int rows = sysSiteSettingService.saveSiteSetting(sysSiteSetting);
        return ResponseBodyUtils.createBySuccess(rows);
    }

    /**
     * 编辑站点信息
     * @param cmsMenu
     * @return
     */
    @PostMapping(value="edit")
    public ResponseBodyUtils<Object> editMenu(@RequestBody  SysSiteSetting sysSiteSetting){
        logger.info("修改站点设置：{}", sysSiteSetting);
        int rows  = sysSiteSettingService.updateSiteSetting(sysSiteSetting);
        return ResponseBodyUtils.createBySuccessCodeMessage("数据已修改！", rows);
    }

    @PostMapping(value="del")
    public ResponseBodyUtils<Object> deleteMenu(String siteId){
        logger.info("删除站点信息：{}", siteId);
        int rows  = sysSiteSettingService.deleteSiteSetting(siteId);
        return ResponseBodyUtils.createBySuccess(rows);
    }

   
    @PostMapping("list/{module}")
    public ResponseBodyUtils<Object> listMenu(@PathVariable  String module, @RequestParam(defaultValue = "1") Integer currPage){
        logger.info("获取菜单列表功能， 根据模块： {}", module);
        Map<String, SysSiteSetting> map = sysSiteSettingService.listSiteSetting(module);
        return ResponseBodyUtils.createBySuccess(map);
    }
}