package com.xymiao.cms.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xymiao.cms.mapper.CmsLinkMapper;
import com.xymiao.cms.pojo.CmsLink;
import com.xymiao.common.exception.BusinessException;
import com.xymiao.common.utils.IdsUtils;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("cmsLinkServiceImpl")
public class CmsLinkServiceImpl implements CmsLinkService {
    
    private final CmsLinkMapper cmsLinkMapper;
    
    public CmsLinkServiceImpl(CmsLinkMapper cmsLinkMapper) {
        this.cmsLinkMapper = cmsLinkMapper;
    }
    
    @Override
    public CmsLink saveLink(CmsLink link) {
        LocalDateTime currDate = LocalDateTime.now();
        
        link.setLinkId(IdsUtils.getId());
        link.setCreated(currDate);
        link.setLinkStatus("0"); // 默认待审核
        link.setDelFlag("0");
        
        int rows = cmsLinkMapper.insert(link);
        if (rows <= 0) {
            throw new BusinessException("友情链接保存失败！");
        }
        return link;
    }
    
    @Override
    public int updateLink(CmsLink link) {
        link.setUpdated(LocalDateTime.now());
        return cmsLinkMapper.updateById(link);
    }
    
    @Override
    public int deleteLink(String linkId) {
        CmsLink link = new CmsLink();
        link.setLinkId(linkId);
        link.setDelFlag("1");
        return cmsLinkMapper.updateById(link);
    }
    
    @Override
    public CmsLink getLink(String linkId) {
        return cmsLinkMapper.selectById(linkId);
    }
    
    @Override
    public List<CmsLink> queryAllLinks() {
        return cmsLinkMapper.selectList(Wrappers.<CmsLink>lambdaQuery().eq(CmsLink::getDelFlag, "0"));
    }
    
    @Override
    public List<CmsLink> getLinksByType(String linkType) {
        return cmsLinkMapper.selectByLinkType(linkType);
    }
    
    @Override
    public List<CmsLink> getLinksByStatus(String status) {
        return cmsLinkMapper.selectByStatus(status);
    }
    
    @Override
    public int approveLink(String linkId, String status) {
        return cmsLinkMapper.updateLinkStatus(linkId, status);
    }
}