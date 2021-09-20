package com.xymiao.cms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xymiao.cms.util.ResponseBodyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.info("没有登录， 请登录");
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), ResponseBodyUtils.createByErrorCodeMessage(401,"没有登录， 请登录！"));

    }
}
