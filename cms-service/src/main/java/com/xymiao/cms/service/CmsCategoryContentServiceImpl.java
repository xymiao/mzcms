package com.xymiao.cms.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xymiao.cms.mapper.CmsCategoryContentMapper;
import com.xymiao.cms.pojo.CmsCategoryContent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cmsCategoryContentServiceImpl")
public class CmsCategoryContentServiceImpl implements CmsCategoryContentService {

    private final CmsCategoryContentMapper CmsCategoryContentMapper;

    public CmsCategoryContentServiceImpl(CmsCategoryContentMapper CmsCategoryContentMapper) {
        this.CmsCategoryContentMapper = CmsCategoryContentMapper;
    }


}