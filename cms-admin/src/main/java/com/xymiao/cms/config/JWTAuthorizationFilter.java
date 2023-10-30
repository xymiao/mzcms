package com.xymiao.cms.config;

import com.xymiao.cms.service.auth.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final static Logger log = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = getTokenFromRequestHeader(request);
        Authentication verifyResult = verefyToken(token, JWTUtil.DEFAULT_SECRET);
        if (verifyResult == null) {
            // 即便验证失败，也继续调用过滤链，匿名过滤器生成匿名令牌
            chain.doFilter(request, response);
            return;
        } else {
            log.info("token令牌验证成功");
            SecurityContextHolder.getContext().setAuthentication(verifyResult);
            chain.doFilter(request, response);
        }
    }

    // 从请求头获取token
    private String getTokenFromRequestHeader(HttpServletRequest request) {
        String header = request.getHeader(JWTUtil.TOKEN_HEADER);
        if (header == null || !header.startsWith(JWTUtil.TOKEN_PREFIX)) {
            log.info("请求头不含JWT token， 调用下个过滤器");
            return null;
        }

        String token = header.split(" ")[1].trim();
        return token;
    }

    // 验证token，并生成认证后的token
    private UsernamePasswordAuthenticationToken verefyToken(String token, String secret) {
        if (token == null) {
            return null;
        }

        // 认证失败，返回null
        if (!JWTUtil.verifyToken(token, secret)) {
            return null;
        }

        // 提取用户名
        String username = JWTUtil.getUsername(token);
        // 定义权限列表
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 从token提取角色
        List<String> roles = JWTUtil.getRole(token);
        for (String role : roles) {
            log.info("用户身份是:" + role);
            authorities.add(new SimpleGrantedAuthority(role));
        }
        // 构建认证过的token
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}