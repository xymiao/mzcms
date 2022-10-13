package com.xymiao.cms.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@TableName("CMS_CATEGORY_CONTENT")
public class CmsCategoryContent {
    @TableId
    private String ccId;
    private String categoryId;
    private String contentId;
    private LocalDateTime created;
    private String creater;
}
