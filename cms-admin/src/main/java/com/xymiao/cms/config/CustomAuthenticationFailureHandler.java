package com.xymiao.cms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xymiao.cms.util.ResponseBodyUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");
        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");

        // 写入响应里
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),
                ResponseBodyUtils.createByErrorMessage(exception.getLocalizedMessage()));

    }
}
