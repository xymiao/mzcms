package com.xymiao.cms.service;

import com.xymiao.cms.pojo.CmsTag;

import java.util.List;

public interface CmsTagService {
    
    /**
     * 保存标签
     */
    CmsTag saveTag(CmsTag tag);
    
    /**
     * 更新标签
     */
    int updateTag(CmsTag tag);
    
    /**
     * 删除标签
     */
    int deleteTag(String tagId);
    
    /**
     * 根据ID获取标签
     */
    CmsTag getTag(String tagId);
    
    /**
     * 查询所有标签
     */
    List<CmsTag> queryAllTags();
    
    /**
     * 根据内容ID查询标签
     */
    List<CmsTag> getTagsByContentId(String contentId);
    
    /**
     * 根据标签名查询标签
     */
    CmsTag getTagByName(String tagName);
    
    /**
     * 根据标签slug查询标签
     */
    CmsTag getTagBySlug(String tagSlug);
    
    /**
     * 获取热门标签
     */
    List<CmsTag> getHotTags(int limit);
    
    /**
     * 为内容添加标签
     */
    void addTagsToContent(String contentId, List<String> tagIds, String userId);
    
    /**
     * 移除内容的所有标签
     */
    void removeTagsFromContent(String contentId);
}