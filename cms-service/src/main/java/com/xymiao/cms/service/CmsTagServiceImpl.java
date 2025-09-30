package com.xymiao.cms.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xymiao.cms.mapper.CmsTagMapper;
import com.xymiao.cms.mapper.CmsContentTagMapper;
import com.xymiao.cms.pojo.CmsTag;
import com.xymiao.cms.pojo.CmsContentTag;
import com.xymiao.common.exception.BusinessException;
import com.xymiao.common.utils.IdsUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("cmsTagServiceImpl")
public class CmsTagServiceImpl implements CmsTagService {
    
    private final CmsTagMapper cmsTagMapper;
    private final CmsContentTagMapper cmsContentTagMapper;
    
    public CmsTagServiceImpl(CmsTagMapper cmsTagMapper, CmsContentTagMapper cmsContentTagMapper) {
        this.cmsTagMapper = cmsTagMapper;
        this.cmsContentTagMapper = cmsContentTagMapper;
    }
    
    @Override
    public CmsTag saveTag(CmsTag tag) {
        LocalDateTime currDate = LocalDateTime.now();
        
        tag.setTagId(IdsUtils.getId());
        tag.setCreated(currDate);
        tag.setUseCount(0);
        tag.setDelFlag("0");
        
        int rows = cmsTagMapper.insert(tag);
        if (rows <= 0) {
            throw new BusinessException("标签保存失败！");
        }
        return tag;
    }
    
    @Override
    public int updateTag(CmsTag tag) {
        tag.setUpdated(LocalDateTime.now());
        return cmsTagMapper.updateById(tag);
    }
    
    @Override
    public int deleteTag(String tagId) {
        CmsTag tag = new CmsTag();
        tag.setTagId(tagId);
        tag.setDelFlag("1");
        return cmsTagMapper.updateById(tag);
    }
    
    @Override
    public CmsTag getTag(String tagId) {
        return cmsTagMapper.selectById(tagId);
    }
    
    @Override
    public List<CmsTag> queryAllTags() {
        return cmsTagMapper.selectList(Wrappers.<CmsTag>lambdaQuery().eq(CmsTag::getDelFlag, "0"));
    }
    
    @Override
    public List<CmsTag> getTagsByContentId(String contentId) {
        return cmsTagMapper.selectTagsByContentId(contentId);
    }
    
    @Override
    public CmsTag getTagByName(String tagName) {
        return cmsTagMapper.selectByTagName(tagName);
    }
    
    @Override
    public CmsTag getTagBySlug(String tagSlug) {
        return cmsTagMapper.selectByTagSlug(tagSlug);
    }
    
    @Override
    public List<CmsTag> getHotTags(int limit) {
        return cmsTagMapper.selectHotTags(limit);
    }
    
    @Override
    @Transactional
    public void addTagsToContent(String contentId, List<String> tagIds, String userId) {
        // 先删除现有关联
        cmsContentTagMapper.deleteByContentId(contentId);
        
        // 添加新关联
        for (String tagId : tagIds) {
            CmsContentTag contentTag = new CmsContentTag();
            contentTag.setCtId(IdsUtils.getId());
            contentTag.setContentId(contentId);
            contentTag.setTagId(tagId);
            contentTag.setCreated(LocalDateTime.now());
            contentTag.setCreater(userId);
            cmsContentTagMapper.insert(contentTag);
            
            // 更新标签使用次数
            cmsTagMapper.updateUseCount(tagId, 1);
        }
    }
    
    @Override
    public void removeTagsFromContent(String contentId) {
        cmsContentTagMapper.deleteByContentId(contentId);
    }
}