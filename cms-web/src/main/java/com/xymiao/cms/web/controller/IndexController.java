package com.xymiao.cms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xymiao.cms.web.BaseController;

@Controller
public class IndexController extends BaseController{
	
	@RequestMapping("")
	public String index() {
		
		logger.info("进入首页");
		return RETURN_THEME + "index";
	}
}
