package com.xymiao.cms;

import com.xymiao.cms.service.CmsMenuService;
import com.xymiao.cms.pojo.CmsMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class CmsMenuServiceTests {
    @Autowired
    CmsMenuService cmsMenuService;
    @Rollback
    @Test
    void saveMenu() {
        CmsMenu cmsMenu = new CmsMenu();
        cmsMenu.setMenuInfo("测试菜单");
        cmsMenu.setMenuModule("backend");
        cmsMenu.setMenuType("left_nav");
        cmsMenu.setMenuName("测试菜单");
        cmsMenu.setParentId("parent");
        cmsMenu.setSort(0);
        CmsMenu cmsMenuSave =  cmsMenuService.saveMenu(cmsMenu);
        System.out.println(cmsMenuSave);
    }

  
    void updateMenu(){
        CmsMenu cmsMenu = new CmsMenu();
        cmsMenu.setMenuId("4d55530bc7938476018f70b7043b9918");
        cmsMenu.setMenuInfo("我是修改的菜单");
        int rows = cmsMenuService.updateMenu(cmsMenu);
        Assertions.assertEquals(rows, 1);
    }

    void deleteMenu(){
        int rows = cmsMenuService.delMenu("4d55530bc7938476018f70b7043b9918");
        Assertions.assertEquals(rows, 1);
    }
}
