package com.xymiao.cms.pojo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("CMS_TAG")
public class CmsTag {
    @TableId
    @TableField("tag_id")
    private String tagId;
    
    @TableField("tag_name")
    private String tagName;
    
    @TableField("tag_slug")
    private String tagSlug;
    
    @TableField("tag_desc")
    private String tagDesc;
    
    @TableField("tag_color")
    private String tagColor;
    
    @TableField("use_count")
    private Integer useCount;
    
    private LocalDateTime created;
    
    private LocalDateTime updated;
    
    @TableField("user_id")
    private String userId;
    
    @TableField("del_flag")
    private String delFlag; // 0-正常, 1-已删除
}