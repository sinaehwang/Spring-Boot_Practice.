package com.hsn.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrController {
	int count=0;
	
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "안녕";
	}
	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public String showMain2() {
		return "반갑";
	}
	@RequestMapping("/usr/home/main3")
	@ResponseBody
	public String showMain3() {
		return "잘가";
	}
	@RequestMapping("/usr/home/main4")
	@ResponseBody
	public int showMain4() {
		
		return count++;//count를 1씩 증가해서 응답하는 메소드가된다. count값이 초기화 되지않을려면 count가 지역변수가 되어야함
	}
	
	@RequestMapping("/usr/home/main5")
	@ResponseBody
	public int showMain5() {
		count=0; //count 값을 0으로 만들어 응답하는 메소드가됨
		return count;
	}

}
