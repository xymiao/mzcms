package com.xymiao.cms.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.xymiao.cms")
public class CmsWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsWebApplication.class, args);
	}

}