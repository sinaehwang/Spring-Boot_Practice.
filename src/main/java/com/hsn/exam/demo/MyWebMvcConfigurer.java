package com.hsn.exam.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hsn.exam.demo.intercepter.BeforeActionIntercepter;
import com.hsn.exam.demo.intercepter.NeedLoginIntercepter;

@Configuration

public class MyWebMvcConfigurer  implements WebMvcConfigurer{
	//객체생성없이 불러오기(하나씩 각자 만들어줘야함)
	@Autowired
	BeforeActionIntercepter beforeActionIntercepter;
	@Autowired
	NeedLoginIntercepter needLoginIntercepter;
	
	//인터셉터 적용해주기
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(beforeActionIntercepter).addPathPatterns("/**").excludePathPatterns("/resource/**")
		.excludePathPatterns("/error");
		//add /**는 모든 요청 작동전에 경로해서 가도록 한다, exclude /resource/**,error는 제외시키고 경로한다.
		
		//로그인이 먼저 필요한 작업들을 인터셉터로 확인할수 있도록 need인터셉터로 만듬
		registry.addInterceptor(needLoginIntercepter).addPathPatterns("/usr/article/doAdd").addPathPatterns("/usr/article/doDelete").addPathPatterns("/usr/article/doModify");
		
		
	}
	

}
