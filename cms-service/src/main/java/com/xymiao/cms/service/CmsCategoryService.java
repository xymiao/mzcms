package com.xymiao.cms.service;

import java.util.List;

import com.xymiao.cms.pojo.CmsCategory;

public interface CmsCategoryService {
	
	List<CmsCategory> queryCategoryList();

	CmsCategory getCategory(String categoryId);
	
	CmsCategory saveCategory(CmsCategory cmsCategory);

	int updateCategory(CmsCategory cmsCategory);

	int delCategory(String categoryId);

	List<CmsCategory> listMenuByUser(String string, String module, String string2); 
}
