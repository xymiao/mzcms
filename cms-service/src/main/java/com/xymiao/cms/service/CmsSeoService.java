package com.xymiao.cms.service;

import com.xymiao.cms.pojo.CmsSeo;

public interface CmsSeoService {
    
    /**
     * 保存SEO信息
     */
    CmsSeo saveSeo(CmsSeo seo);
    
    /**
     * 更新SEO信息
     */
    int updateSeo(CmsSeo seo);
    
    /**
     * 删除SEO信息
     */
    int deleteSeo(String seoId);
    
    /**
     * 根据ID获取SEO信息
     */
    CmsSeo getSeo(String seoId);
    
    /**
     * 根据内容ID和类型获取SEO信息
     */
    CmsSeo getSeoByContentIdAndType(String contentId, String contentType);
    
    /**
     * 为内容设置SEO信息
     */
    CmsSeo setSeoForContent(String contentId, String contentType, CmsSeo seo);
}