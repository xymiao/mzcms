package com.xymiao.cms.util;

import com.xymiao.cms.pojo.auth.SysUser;
import com.xymiao.cms.service.user.SysUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
    private  SysUserService sysUserService;

    public UserUtils(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public  String getName(){
        String userName  = SecurityContextHolder.getContext().getAuthentication().getName();;
        return userName;
    }
    public   String getUserId(){
        SysUser sysUser = sysUserService.getUserByLoginId(getName());
        return sysUser.getUserId();
    }
}
