package com.xymiao.cms.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@TableName("CMS_MENU")
public class CmsMenu {

    @TableId
    private String menuId;
    private String menuName;
    private int sort;
    private String menuInfo;
    private String parentId;
    private String menuType;
    private String menuModule;
    private String delFlag;
    private LocalDateTime created;
    private LocalDateTime lastDate;
    private String url;
    private String iconUrl;

    @TableField(exist = false)
    private List<CmsMenu> cmsMenus;
}