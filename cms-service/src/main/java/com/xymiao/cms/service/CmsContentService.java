package com.xymiao.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.pojo.CmsContent;

import java.util.List;

public interface CmsContentService {
    /**
     * 根据分类加载对应的内容列表
     * @param categoryId 分类主键
     * @return 集合
     */
    List<CmsContent> queryContentByCategory(String categoryId);

    Page<CmsContent> queryContentByModule(Integer current, Integer size, String moduleId);

    Page<CmsContent> queryContentByModuleAndId(Integer current, Integer size, String moduleId, String categoryId);
    /**
     * 根据主键 content_id 查询对应的内容信息
     * @param id 主键 id
     * @return 如果存在就返回对应的内容信息
     */
    CmsContent getContent(String id);
    
	CmsContent saveContent(CmsContent cmsContent);
}
