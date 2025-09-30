package com.xymiao.cms.service;

import com.xymiao.cms.mapper.CmsSeoMapper;
import com.xymiao.cms.pojo.CmsSeo;
import com.xymiao.common.exception.BusinessException;
import com.xymiao.common.utils.IdsUtils;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("cmsSeoServiceImpl")
public class CmsSeoServiceImpl implements CmsSeoService {
    
    private final CmsSeoMapper cmsSeoMapper;
    
    public CmsSeoServiceImpl(CmsSeoMapper cmsSeoMapper) {
        this.cmsSeoMapper = cmsSeoMapper;
    }
    
    @Override
    public CmsSeo saveSeo(CmsSeo seo) {
        LocalDateTime currDate = LocalDateTime.now();
        
        seo.setSeoId(IdsUtils.getId());
        seo.setCreated(currDate);
        
        int rows = cmsSeoMapper.insert(seo);
        if (rows <= 0) {
            throw new BusinessException("SEO信息保存失败！");
        }
        return seo;
    }
    
    @Override
    public int updateSeo(CmsSeo seo) {
        seo.setUpdated(LocalDateTime.now());
        return cmsSeoMapper.updateById(seo);
    }
    
    @Override
    public int deleteSeo(String seoId) {
        return cmsSeoMapper.deleteById(seoId);
    }
    
    @Override
    public CmsSeo getSeo(String seoId) {
        return cmsSeoMapper.selectById(seoId);
    }
    
    @Override
    public CmsSeo getSeoByContentIdAndType(String contentId, String contentType) {
        return cmsSeoMapper.selectByContentIdAndType(contentId, contentType);
    }
    
    @Override
    public CmsSeo setSeoForContent(String contentId, String contentType, CmsSeo seo) {
        // 检查是否已存在SEO信息
        CmsSeo existingSeo = getSeoByContentIdAndType(contentId, contentType);
        
        if (existingSeo != null) {
            // 更新现有SEO信息
            seo.setSeoId(existingSeo.getSeoId());
            updateSeo(seo);
            return seo;
        } else {
            // 创建新的SEO信息
            seo.setContentId(contentId);
            seo.setContentType(contentType);
            return saveSeo(seo);
        }
    }
}