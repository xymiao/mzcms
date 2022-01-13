package com.xymiao.cms.service;

import java.util.List;

import com.xymiao.cms.pojo.CmsCategory;

public interface CmsCategoryService {
	
	List<CmsCategory> queryCategoryList();

	CmsCategory getCategory(String categoryId);

	CmsCategory getCategoryByUrl(String url);
	
	CmsCategory saveCategory(CmsCategory cmsCategory);

	int updateCategory(CmsCategory cmsCategory);

	int delCategory(String categoryId);

	List<CmsCategory> listMenuByUser(String string, String module, String string2);
	/**
	 * 根据内容ID 查询分类信息
	 * @param contentId 内容主键
	 * @return 分类信息的集合
	 */
	List<CmsCategory> listByContentId(String contentId);
}
