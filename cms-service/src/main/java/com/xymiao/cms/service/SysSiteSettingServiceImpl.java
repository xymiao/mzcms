package com.xymiao.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xymiao.cms.mapper.SysSiteSettingMapper;
import com.xymiao.cms.pojo.SysSiteSetting;
import com.xymiao.common.exception.ParameterException;

@Service("sysSiteSettingServiceImpl")
public class SysSiteSettingServiceImpl implements SysSiteSettingService {
	private final static Logger logger = LoggerFactory.getLogger(SysSiteSettingServiceImpl.class);

	@Autowired
	private SysSiteSettingMapper sysSiteSettingMapper;

	@Override
	public SysSiteSetting getSiteSetting(String siteKey) {
		if (StringUtils.isEmpty(siteKey)) {
			throw new ParameterException("参数无效");
		}
		return sysSiteSettingMapper
				.selectOne(Wrappers.<SysSiteSetting>lambdaQuery().eq(SysSiteSetting::getSiteKey, siteKey));
	}

	@Override
	public int saveSiteSetting(SysSiteSetting siteSetting) {
		if (Objects.isNull(siteSetting)) {
			throw new ParameterException("参数无效");
		}
		return sysSiteSettingMapper.insert(siteSetting);
	}

	@Override
	public int updateSiteSetting(SysSiteSetting siteSetting) {
		if (Objects.isNull(siteSetting)) {
			throw new ParameterException("参数无效");
		}
		return sysSiteSettingMapper.updateById(siteSetting);
	}

	@Override
	public int deleteSiteSetting(String siteId) {
		if (StringUtils.isEmpty(siteId)) {
			throw new ParameterException("参数无效");
		}
		// 首先放入回收站
		// 删除数据
		return sysSiteSettingMapper.deleteById(siteId);
	}

	@Override
	public Map<String, SysSiteSetting> listSiteSetting(String module) {
		if (StringUtils.isEmpty(module)) {
			throw new ParameterException("参数无效");
		}
		logger.info("加载数据：" + module);
		List<SysSiteSetting> list = sysSiteSettingMapper
				.selectList(Wrappers.<SysSiteSetting>lambdaQuery().eq(SysSiteSetting::getModuleType, module));
		Map<String, SysSiteSetting> map = new HashMap<>();
		if(list == null || list.size() <= 0) {
			return map;
		}
		for (SysSiteSetting sysSiteSetting : list) {
			map.put(sysSiteSetting.getSiteKey(), sysSiteSetting);
		}
		return map;
	}

}
