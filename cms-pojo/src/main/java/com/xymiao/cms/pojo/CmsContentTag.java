package com.xymiao.cms.pojo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("CMS_CONTENT_TAG")
public class CmsContentTag {
    @TableId
    @TableField("ct_id")
    private String ctId;
    
    @TableField("content_id")
    private String contentId;
    
    @TableField("tag_id")
    private String tagId;
    
    private LocalDateTime created;
    
    @TableField("creater")
    private String creater;
}