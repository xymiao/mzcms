package com.xymiao.cms.service.user;

import com.xymiao.cms.pojo.auth.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getUserByLoginId(loginId);
        if (sysUser == null) {
            throw new UsernameNotFoundException("username " + loginId + " is not found");
        }
        return new CustomUserDetails(sysUser);
    }
    static final class CustomUserDetails extends SysUser implements UserDetails {
		private static final long serialVersionUID = 6916027547378258066L;
		private static final List<GrantedAuthority> ROLE_USER = Collections
                .unmodifiableList(AuthorityUtils.createAuthorityList("ROLE_USER"));

        CustomUserDetails(SysUser sysUser) {
            super(sysUser.getUserId(), sysUser.getLoginId(), sysUser.getUserPwd());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return ROLE_USER;
        }

        @Override
        public String getPassword() {
            return getUserPwd();
        }

        @Override
        public String getUsername() {
            return getLoginId();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }

}