package com.hsn.exam.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hsn.exam.demo.intercepter.BeforeActionIntercepter;

@Configuration

public class MyWebMvcConfigurer  implements WebMvcConfigurer{
	//객체생성없이 불러오기
	@Autowired
	BeforeActionIntercepter beforeActionIntercepter;
	
	//인터셉터 적용해주기
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(beforeActionIntercepter).addPathPatterns("/**").excludePathPatterns("/resource/**")
		.excludePathPatterns("/error");
		//add /**는 모든 요청 작동전에 경로해서 가도록 한다, exclude /resource/**,error는 제외시키고 경로한다.
		
		
	}
	

}
