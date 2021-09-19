package com.xymiao.pojo.cms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

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

}