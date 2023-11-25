package com.xymiao.cms.pojo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("CMS_CONTENT")
public class CmsContent {
	@TableId
	@TableField("content_id")
	private String contentId;
	private String title;
	private String content;
	@TableField("view_num")
	private Long viewNum;
	@TableField("comment_num")
	private Long commentNum;
	@TableField("content_state")
	private String contentState;
	@TableField("comment_flag")
	private String commentFlag;
	private LocalDateTime created;
	@TableField("publish_date")
	private LocalDateTime publishDate;
	@TableField("publish_user")
	private String publishUser;
	private LocalDateTime updated;
	@TableField("content_desc")
	private String contentDesc;
	@TableField("title_mini")
	private String titleMini;
	@TableField("content_type")
	private String contentType;
	@TableField("content_pwd")
	private String contentPwd;
}