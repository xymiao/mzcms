package com.xymiao.cms.pojo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("CMS_LINK")
public class CmsLink {
    @TableId
    @TableField("link_id")
    private String linkId;
    
    @TableField("link_name")
    private String linkName;
    
    @TableField("link_url")
    private String linkUrl;
    
    @TableField("link_desc")
    private String linkDesc;
    
    @TableField("link_logo")
    private String linkLogo;
    
    @TableField("link_type")
    private String linkType; // friend, partner, sponsor
    
    @TableField("link_status")
    private String linkStatus; // 0-待审核, 1-已通过, 2-已拒绝
    
    @TableField("sort_order")
    private Integer sortOrder;
    
    @TableField("contact_email")
    private String contactEmail;
    
    @TableField("contact_name")
    private String contactName;
    
    private LocalDateTime created;
    
    private LocalDateTime updated;
    
    @TableField("user_id")
    private String userId;
    
    @TableField("del_flag")
    private String delFlag; // 0-正常, 1-已删除
}