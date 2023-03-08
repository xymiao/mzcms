package com.xymiao.cms.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xymiao.common.exception.BusinessException;
import com.xymiao.common.utils.IdsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xymiao.cms.mapper.CmsCategoryMapper;
import com.xymiao.cms.pojo.CmsCategory;

@Service("cmsCategoryServiceImpl")
public class CmsCategoryServiceImpl implements CmsCategoryService {
	
	private final CmsCategoryMapper cmsCategoryMapper;

	public CmsCategoryServiceImpl(CmsCategoryMapper cmsCategoryMapper) {
		this.cmsCategoryMapper = cmsCategoryMapper;
	}

	@Override
	public List<CmsCategory> queryCategoryList() {
		return cmsCategoryMapper.queryCategoryList("");
	}
	@Override
	public CmsCategory getCategory(String categoryId) {
		return cmsCategoryMapper.selectById(categoryId);
	}

	@Override
	public CmsCategory getCategoryByUrl(String url) {
		return cmsCategoryMapper.selectOne(Wrappers.<CmsCategory>lambdaQuery().eq(CmsCategory::getUrl, url));
	}

	@Override
	public CmsCategory saveCategory(CmsCategory cmsCategory) {
		if(Objects.isNull(cmsCategory)){
			return null;
		}
		LocalDateTime currDate = 	LocalDateTime.now();
		cmsCategory.setCategoryId(IdsUtils.getId());
		cmsCategory.setCreated(currDate);
		cmsCategory.setLastDate(currDate);
		int rows = cmsCategoryMapper.insert(cmsCategory);
		if(rows <=0){
			throw new BusinessException("分类保存失败！");
		}
		return cmsCategory;
	}
	@Override
	public int updateCategory(CmsCategory cmsCategory) {
		return cmsCategoryMapper.updateById(cmsCategory);
	}
	@Override
	public int delCategory(String categoryId) {
		//判断是否有子元素存在
		Long count = listCategoryCountByParentId(categoryId);
		if(count > 0){
			throw new BusinessException("删除分类失败，当前分类存在子分类！");
		}
		//判断该分类是否有内容

		//删除
		return cmsCategoryMapper.deleteById(categoryId);
	}

	private Long listCategoryCountByParentId(String parentId){
		if(StringUtils.isEmpty(parentId)) return null;
		return cmsCategoryMapper.selectCount(Wrappers.<CmsCategory>lambdaQuery().eq(CmsCategory::getParentId, parentId));
	}

	@Override
	public List<CmsCategory> listMenuByUser(String string, String module, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CmsCategory> listByContentId(String contentId) {
		String inSql = "select category_id from cms_category_content  where content_id = '" + contentId + "'";
		return cmsCategoryMapper.selectList(Wrappers.<CmsCategory>lambdaQuery()
				.inSql(CmsCategory::getCategoryId, inSql).orderByDesc(CmsCategory::getCreated));
	}

}