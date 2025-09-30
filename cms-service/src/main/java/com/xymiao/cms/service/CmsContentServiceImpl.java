package com.xymiao.cms.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.mapper.CmsContentMapper;
import com.xymiao.cms.pojo.CmsContent;
import com.xymiao.common.exception.BusinessException;
import com.xymiao.common.utils.IdsUtils;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("cmsContentServiceImpl")
public class CmsContentServiceImpl implements CmsContentService {
	private final CmsContentMapper cmsContentMapper;
	private final CmsTagService cmsTagService;

	@Autowired
	public CmsContentServiceImpl(CmsContentMapper cmsContentMapper, CmsTagService cmsTagService) {
		this.cmsContentMapper = cmsContentMapper;
		this.cmsTagService = cmsTagService;
	}

	@Override
	public List<CmsContent> queryContentByCategory(String categoryId) {
		String sql  = " select content_id from cms_category_content where category_id = '" + categoryId + "'";
		return cmsContentMapper.selectList(Wrappers.<CmsContent>lambdaQuery()
				.inSql(CmsContent::getContentId, sql).orderByDesc(CmsContent::getPublishDate));
	}

	@Override
	public Page<CmsContent> queryContentByModule(Integer current, Integer size, String moduleId) {
		return cmsContentMapper.selectPage(new Page<>(current, size),
				Wrappers.<CmsContent>lambdaQuery().orderByDesc(CmsContent::getPublishDate));
	}

	@Override
	public Page<CmsContent> queryContentByModuleAndId(Integer current, Integer size, String moduleId,
			String categoryId) {
		String inSql = "select content_id from cms_category_content  where category_id = '" + categoryId + "'";
		return cmsContentMapper.selectPage(new Page<>(current, size), Wrappers.<CmsContent>lambdaQuery()
				.inSql(CmsContent::getContentId, inSql).orderByDesc(CmsContent::getPublishDate));
	}

	@Override
	public CmsContent getContent(String id) {
		return cmsContentMapper.selectById(id);
	}

	@Override
	public CmsContent saveContent(CmsContent cmsContent) {
		LocalDateTime currDate = LocalDateTime.now();
		
		cmsContent.setContentId(IdsUtils.getId());
		cmsContent.setPublishDate(currDate);
		String t = Jsoup.parse(cmsContent.getContent()).text();
		String c = t.substring(0, t.length() < 100 ? t.length() : 100);
		cmsContent.setContentDesc( c );
		cmsContent.setViewNum(0l);
		cmsContent.setCommentNum(0l);
		cmsContent.setCreated(currDate);
		cmsContent.setCommentFlag("0");
		cmsContent.setContentState("1");
		int rows = cmsContentMapper.insert(cmsContent);
		if(rows <=0){
			throw new BusinessException("内容保存失败！");
		}
		return cmsContent;
	}

	@Override
	public int updateCotent(CmsContent cmsContent) {
		return cmsContentMapper.updateById(cmsContent);
	}

    @Override
    public void addViewCount(String id) {
        cmsContentMapper.updateViewCount(id);
    }
    
    @Override
    public void updateCommentCount(String contentId, Long commentNum) {
        cmsContentMapper.updateCommentCount(contentId, commentNum);
    }
    
    @Override
    @Transactional
    public CmsContent saveContentWithTags(CmsContent cmsContent, List<String> tagIds) {
        // 保存内容
        CmsContent savedContent = saveContent(cmsContent);
        
        // 如果有标签，保存标签关联
        if (tagIds != null && !tagIds.isEmpty()) {
            cmsTagService.addTagsToContent(savedContent.getContentId(), tagIds, savedContent.getPublishUser());
        }
        
        return savedContent;
    }
    
    @Override
    @Transactional
    public int updateContentWithTags(CmsContent cmsContent, List<String> tagIds) {
        // 更新内容
        int result = updateCotent(cmsContent);
        
        // 更新标签关联
        if (tagIds != null) {
            if (tagIds.isEmpty()) {
                // 如果标签列表为空，移除所有标签
                cmsTagService.removeTagsFromContent(cmsContent.getContentId());
            } else {
                // 重新设置标签
                cmsTagService.addTagsToContent(cmsContent.getContentId(), tagIds, cmsContent.getPublishUser());
            }
        }
        
        return result;
    }
}
