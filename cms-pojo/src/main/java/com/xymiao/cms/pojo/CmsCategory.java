package com.xymiao.cms.pojo;

import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("CMS_CATEGORY")
public class CmsCategory {
	@TableId
	private String categoryId;
	private String name;
	private String iconUrl;
	private String moduleId;
	private String parentId;
	private LocalDateTime created;
	private LocalDateTime lastDate;
	private String userId;
	private Integer sort;
	private String url;
	private String status;
	private String delFlag;
	
    @TableField(exist = false)
    private List<CmsCategory> cmsCategories;
}
