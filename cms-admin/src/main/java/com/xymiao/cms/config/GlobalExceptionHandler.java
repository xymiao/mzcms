package com.xymiao.cms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xymiao.cms.util.ResponseBodyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	public void defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
		logger.info("登录失败");
		response.setStatus(200);
		response.setContentType("application/json;charset=UTF-8");


		logger.error("ExceptionHandler ===>" + e.getMessage());
		e.printStackTrace();
		// 这里可根据不同异常引起的类做不同处理方式
		String exceptionName = ClassUtils.getShortName(e.getClass());

		// 写入响应里
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getWriter(),
				ResponseBodyUtils.createByErrorMessage(e.getMessage()));

		logger.error("ExceptionHandler ===>" + exceptionName);

	}
}