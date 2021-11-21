package com.xymiao.cms.pojo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("CMS_CONTENT")
public class CmsContent {
	@TableId
	private String contentId;
	private String title;
	private Long viewNum;
	private Long commentNum;
	private String contentState;
	private String commentFlag;
	private LocalDateTime created;
	private LocalDateTime publishDate;
	private String publishUser;
	private LocalDateTime updated;
	private String contentDesc;
	private String titleMini;
	private String contentType;
	private String contentPwd;
}