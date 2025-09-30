package com.xymiao.cms.pojo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("CMS_SEO")
public class CmsSeo {
    @TableId
    @TableField("seo_id")
    private String seoId;
    
    @TableField("content_id")
    private String contentId;
    
    @TableField("content_type")
    private String contentType; // content, category, page
    
    @TableField("meta_title")
    private String metaTitle;
    
    @TableField("meta_description")
    private String metaDescription;
    
    @TableField("meta_keywords")
    private String metaKeywords;
    
    @TableField("canonical_url")
    private String canonicalUrl;
    
    @TableField("og_title")
    private String ogTitle;
    
    @TableField("og_description")
    private String ogDescription;
    
    @TableField("og_image")
    private String ogImage;
    
    @TableField("og_type")
    private String ogType;
    
    @TableField("twitter_card")
    private String twitterCard;
    
    @TableField("schema_markup")
    private String schemaMarkup;
    
    private LocalDateTime created;
    
    private LocalDateTime updated;
    
    @TableField("user_id")
    private String userId;
}