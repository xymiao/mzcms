package com.xymiao.cms.service.user;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xymiao.cms.mapper.SysUserMapper;
import com.xymiao.cms.pojo.auth.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysUserServiceImpl")
public class SysUserServiceImpl implements SysUserService {
    private SysUserMapper sysUserMapper;

    @Autowired
    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public SysUser getUserByLoginId(String loginId) {
        if (StringUtils.isEmpty(loginId)) return null;
        return sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getLoginId, loginId));
    }
}
