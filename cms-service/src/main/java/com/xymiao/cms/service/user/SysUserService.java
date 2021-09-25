package com.xymiao.cms.service.user;

import com.xymiao.cms.pojo.auth.SysUser;

public interface SysUserService {
    /**
     * 根据用户登录帐号查询对应的用户信息
     * @param loginId  登录帐号id
     * @return 用户的信息
     */
    SysUser getUserByLoginId(String loginId);
}
