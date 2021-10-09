package com.xymiao.cms.service;

import java.util.Map;

import com.xymiao.cms.pojo.SysSiteSetting;

/**
 * 网站配置相关的业务处理
 */
public interface SysSiteSettingService {
	/**
	 * 根据 配置的主键 查询对应的值
	 * @param siteKey 配置的主键
	 * @return 如果存在就返回对应的配置项
	 */
	SysSiteSetting getSiteSetting(String siteKey);
	/**
	 * 保存配置项
	 * @param siteSetting 配置项实体类
	 * @return 受影响的行数
	 */
	int saveSiteSetting(SysSiteSetting siteSetting);
	/**
	 * 保存配置项
	 * @param siteSetting 配置项实体类
	 * @return 受影响的行数
	 */
	int updateSiteSetting(SysSiteSetting siteSetting);
	/**
	 * 删除数据
	 * @param siteId 配置主键
	 * @return 受影响的行数
	 */
	int deleteSiteSetting(String siteId);
	/**
	 * 加载某一模块的所有配置
	 * @param module 模块类型
	 * @return 配置集合
	 */
	Map<String, SysSiteSetting> listSiteSetting(String module);
}
