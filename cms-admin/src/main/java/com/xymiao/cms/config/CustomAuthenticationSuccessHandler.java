package com.xymiao.cms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xymiao.cms.service.auth.JWTUtil;
import com.xymiao.cms.util.ResponseBodyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String currentUser = authentication.getName();
        logger.info("用户{}, 登录成功", currentUser);
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");

        // 提取用户名，准备写入token
        String username = authentication.getName();
        // 提取角色，转为List<String>对象，写入token
        List<String> roles = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities){
            roles.add(authority.getAuthority());
        }

        // 创建token
        String token = JWTUtil.createToken(username, roles, JWTUtil.DEFAULT_SECRET, true);
        response.setCharacterEncoding("UTF-8");
        // 为了跨域，把token放到响应头WWW-Authenticate里
        response.setHeader("WWW-Authenticate", JWTUtil.TOKEN_PREFIX + token);
        // 写入响应里
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),  ResponseBodyUtils.createBySuccessCodeMessage("登录成功" , JWTUtil.TOKEN_PREFIX + token));
    }

}
