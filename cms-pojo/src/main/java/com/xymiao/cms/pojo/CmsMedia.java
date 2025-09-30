package com.xymiao.cms.pojo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("CMS_MEDIA")
public class CmsMedia {
    @TableId
    @TableField("media_id")
    private String mediaId;
    
    @TableField("file_name")
    private String fileName;
    
    @TableField("original_name")
    private String originalName;
    
    @TableField("file_path")
    private String filePath;
    
    @TableField("file_url")
    private String fileUrl;
    
    @TableField("file_size")
    private Long fileSize;
    
    @TableField("file_type")
    private String fileType;
    
    @TableField("mime_type")
    private String mimeType;
    
    @TableField("media_type")
    private String mediaType; // image, video, audio, document
    
    @TableField("alt_text")
    private String altText;
    
    @TableField("title")
    private String title;
    
    @TableField("description")
    private String description;
    
    private LocalDateTime created;
    
    private LocalDateTime updated;
    
    @TableField("user_id")
    private String userId;
    
    @TableField("del_flag")
    private String delFlag; // 0-正常, 1-已删除
}