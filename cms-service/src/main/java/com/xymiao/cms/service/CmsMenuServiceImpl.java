package com.xymiao.cms.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xymiao.cms.mapper.CmsMenuMapper;
import com.xymiao.cms.pojo.CmsCategory;
import com.xymiao.cms.pojo.CmsMenu;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service("cmsMenuServiceImpl")
public class CmsMenuServiceImpl implements CmsMenuService {
    private final static Logger logger = LoggerFactory.getLogger(CmsMenuServiceImpl.class);
    private CmsMenuMapper cmsMenuMapper;

    @Autowired
    public CmsMenuServiceImpl(CmsMenuMapper cmsMenuMapper) {
        this.cmsMenuMapper = cmsMenuMapper;
    }

    @Override
    public CmsMenu saveMenu(CmsMenu cmsMenu) {
        if (Objects.isNull(cmsMenu)) {
            return null;
        }
        cmsMenu.setMenuId(IdWorker.get32UUID());
        cmsMenu.setDelFlag("0");
        cmsMenu.setCreated(LocalDateTime.now());
        cmsMenu.setLastDate(LocalDateTime.now());
        int rows = cmsMenuMapper.insert(cmsMenu);
        if (rows <= 0) {
            return null;
        }
        return cmsMenu;
    }

    @Override
    public CmsMenu getMenu(String menuId) {
        return cmsMenuMapper.selectById(menuId);
    }

    @Override
    public Page<CmsMenu> listMenu(String module, String type, Integer current, Integer size) {
        Page<CmsMenu> cmsMenuPage = cmsMenuMapper.selectPage(new Page<>(current, size),
                Wrappers.<CmsMenu>lambdaQuery().eq(CmsMenu::getParentId, "parent").eq(CmsMenu::getMenuModule, module).eq(CmsMenu::getMenuType, type));
        List<CmsMenu> cmsMenus = cmsMenuMapper.queryMenuList("backend");
        cmsMenuPage.setRecords(cmsMenus);
        logger.info("数据信息： {}", cmsMenus);
        return cmsMenuPage;
    }

    @Override
    public int updateMenu(CmsMenu cmsMenu) {
        if (Objects.isNull(cmsMenu)) {
            return 0;
        }
        cmsMenu.setLastDate(LocalDateTime.now());
        int rows = cmsMenuMapper.updateById(cmsMenu);
        return rows;
    }

    @Override
    public int delMenu(String menuId) {
        CmsMenu cmsMenu = new CmsMenu();
        cmsMenu.setMenuId(menuId);
        cmsMenu.setDelFlag("1");
        cmsMenu.setLastDate(LocalDateTime.now());
        int rows = cmsMenuMapper.update(cmsMenu, Wrappers.<CmsMenu>lambdaQuery().eq(CmsMenu::getMenuId, menuId));
        return rows;
    }

    @Override
    public List<CmsMenu> listMenuByFront(String module) {
        if (StringUtils.isEmpty(module)) {
            return null;
        }
        return cmsMenuMapper.selectList(Wrappers.<CmsMenu>lambdaQuery()
                .eq(CmsMenu::getMenuModule, module)
                .eq(CmsMenu::getMenuType, "top_nav"));
    }

    @Override
    public List<CmsMenu> listMenuByUser(String userId, String module, String left_nav) {
        List<CmsMenu> cmsMenus = cmsMenuMapper.queryMenuList("backend");
        return cmsMenus;
    }
}
