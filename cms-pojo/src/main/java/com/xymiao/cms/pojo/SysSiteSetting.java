package com.xymiao.cms.pojo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("SYS_SITE_SETTING")
public class SysSiteSetting {
	@TableId
	private String siteId;
	private String title;
	private String siteKey;
	private String siteValue;
	private String valueType;
	private String siteState;
	private LocalDateTime created;
	private LocalDateTime updated;
	private String userId;
	private String moduleType;
	
}