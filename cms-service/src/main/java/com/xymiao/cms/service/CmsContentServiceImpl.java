package com.xymiao.cms.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.mapper.CmsContentMapper;
import com.xymiao.cms.pojo.CmsContent;
import com.xymiao.common.exception.BusinessException;
import com.xymiao.common.utils.IdsUtils;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("cmsContentServiceImpl")
public class CmsContentServiceImpl implements CmsContentService {
	private final CmsContentMapper cmsContentMapper;

	public CmsContentServiceImpl(CmsContentMapper cmsContentMapper) {
		this.cmsContentMapper = cmsContentMapper;
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
}
