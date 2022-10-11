package com.hsn.exam.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hsn.exam.demo.intercepter.BeforeActionIntercepter;

public class MyWebConfig implements WebMvcConfigurer{

	@Autowired
	BeforeActionIntercepter beforeActionIntercepter = new BeforeActionIntercepter(); 
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

	InterceptorRegistration ir	 = registry.addInterceptor(beforeActionIntercepter);
	
	//구현중
		
	}
}
