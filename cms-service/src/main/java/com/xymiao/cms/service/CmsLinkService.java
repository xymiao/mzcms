package com.xymiao.cms.service;

import com.xymiao.cms.pojo.CmsLink;

import java.util.List;

public interface CmsLinkService {
    
    /**
     * 保存友情链接
     */
    CmsLink saveLink(CmsLink link);
    
    /**
     * 更新友情链接
     */
    int updateLink(CmsLink link);
    
    /**
     * 删除友情链接
     */
    int deleteLink(String linkId);
    
    /**
     * 根据ID获取友情链接
     */
    CmsLink getLink(String linkId);
    
    /**
     * 查询所有友情链接
     */
    List<CmsLink> queryAllLinks();
    
    /**
     * 根据链接类型查询友情链接
     */
    List<CmsLink> getLinksByType(String linkType);
    
    /**
     * 根据状态查询友情链接
     */
    List<CmsLink> getLinksByStatus(String status);
    
    /**
     * 审核友情链接
     */
    int approveLink(String linkId, String status);
}