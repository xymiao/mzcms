package com.xymiao.cms.service;


import com.xymiao.cms.mapper.CmsCategoryContentMapper;

import org.springframework.stereotype.Service;


@Service("cmsCategoryContentServiceImpl")
public class CmsCategoryContentServiceImpl implements CmsCategoryContentService {

    private final CmsCategoryContentMapper cmsCategoryContentMapper;

    public CmsCategoryContentServiceImpl(CmsCategoryContentMapper CmsCategoryContentMapper) {
        this.cmsCategoryContentMapper = CmsCategoryContentMapper;
    }
    
    public void get() {
    	cmsCategoryContentMapper.selectById("");
    }
}