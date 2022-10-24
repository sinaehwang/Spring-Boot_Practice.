package com.hsn.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrController {
	@RequestMapping("/usr/home/main")
	public String showMain() {
		//return "/WEB-INF/jsp/usr/home/main.jsp"; //yml에서 prefix랑suffix 시작과 끝주소가 써져있기때문에 생략가능
		return "/usr/home/main"; 
	}
	
	@RequestMapping("/") //자동응답처럼 /usr/home/main이 실행된다
	public String showRoot() {
		return "redirect:/usr/home/main";
	}

}

