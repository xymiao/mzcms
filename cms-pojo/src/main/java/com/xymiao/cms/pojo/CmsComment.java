package com.xymiao.cms.pojo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("CMS_COMMENT")
public class CmsComment {
    @TableId
    @TableField("comment_id")
    private String commentId;
    
    @TableField("content_id")
    private String contentId;
    
    @TableField("parent_id")
    private String parentId;
    
    @TableField("user_id")
    private String userId;
    
    @TableField("user_name")
    private String userName;
    
    @TableField("user_email")
    private String userEmail;
    
    @TableField("user_website")
    private String userWebsite;
    
    @TableField("comment_content")
    private String commentContent;
    
    @TableField("comment_status")
    private String commentStatus; // 0-待审核, 1-已通过, 2-已拒绝
    
    @TableField("ip_address")
    private String ipAddress;
    
    @TableField("user_agent")
    private String userAgent;
    
    private LocalDateTime created;
    
    private LocalDateTime updated;
    
    @TableField("del_flag")
    private String delFlag; // 0-正常, 1-已删除
}