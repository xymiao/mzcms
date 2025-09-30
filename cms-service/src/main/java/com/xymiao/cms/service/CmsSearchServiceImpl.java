package com.xymiao.cms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.mapper.CmsContentMapper;
import com.xymiao.cms.pojo.CmsContent;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("cmsSearchServiceImpl")
public class CmsSearchServiceImpl implements CmsSearchService {
    
    private final CmsContentMapper cmsContentMapper;
    
    public CmsSearchServiceImpl(CmsContentMapper cmsContentMapper) {
        this.cmsContentMapper = cmsContentMapper;
    }
    
    @Override
    public Page<CmsContent> searchContent(String keyword, Integer current, Integer size) {
        QueryWrapper<CmsContent> wrapper = new QueryWrapper<>();
        wrapper.and(w -> w.like("title", keyword).or().like("content", keyword).or().like("content_desc", keyword))
               .eq("content_state", "1")
               .eq("del_flag", "0")
               .orderByDesc("publish_date");
        
        Page<CmsContent> page = new Page<>(current, size);
        return cmsContentMapper.selectPage(page, wrapper);
    }
    
    @Override
    public Page<CmsContent> searchContentByTag(String tagId, Integer current, Integer size) {
        // 需要通过关联表查询，这里简化处理
        QueryWrapper<CmsContent> wrapper = new QueryWrapper<>();
        wrapper.inSql("content_id", 
                     "SELECT content_id FROM cms_content_tag WHERE tag_id = '" + tagId + "'")
               .eq("content_state", "1")
               .eq("del_flag", "0")
               .orderByDesc("publish_date");
        
        Page<CmsContent> page = new Page<>(current, size);
        return cmsContentMapper.selectPage(page, wrapper);
    }
    
    @Override
    public Page<CmsContent> searchContentByCategory(String categoryId, Integer current, Integer size) {
        QueryWrapper<CmsContent> wrapper = new QueryWrapper<>();
        wrapper.inSql("content_id", 
                     "SELECT content_id FROM cms_category_content WHERE category_id = '" + categoryId + "'")
               .eq("content_state", "1")
               .eq("del_flag", "0")
               .orderByDesc("publish_date");
        
        Page<CmsContent> page = new Page<>(current, size);
        return cmsContentMapper.selectPage(page, wrapper);
    }
    
    @Override
    public Page<CmsContent> advancedSearch(String keyword, String categoryId, String tagId, 
                                          String contentType, Integer current, Integer size) {
        QueryWrapper<CmsContent> wrapper = new QueryWrapper<>();
        
        // 关键词搜索
        if (StringUtils.hasText(keyword)) {
            wrapper.and(w -> w.like("title", keyword).or().like("content", keyword).or().like("content_desc", keyword));
        }
        
        // 分类筛选
        if (StringUtils.hasText(categoryId)) {
            wrapper.inSql("content_id", 
                         "SELECT content_id FROM cms_category_content WHERE category_id = '" + categoryId + "'");
        }
        
        // 标签筛选
        if (StringUtils.hasText(tagId)) {
            wrapper.inSql("content_id", 
                         "SELECT content_id FROM cms_content_tag WHERE tag_id = '" + tagId + "'");
        }
        
        // 内容类型筛选
        if (StringUtils.hasText(contentType)) {
            wrapper.eq("content_type", contentType);
        }
        
        wrapper.eq("content_state", "1")
               .eq("del_flag", "0")
               .orderByDesc("publish_date");
        
        Page<CmsContent> page = new Page<>(current, size);
        return cmsContentMapper.selectPage(page, wrapper);
    }
}