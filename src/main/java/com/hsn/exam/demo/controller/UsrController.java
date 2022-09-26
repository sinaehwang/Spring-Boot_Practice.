package com.hsn.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrController {
	private int count;
	
	public UsrController() {
		
		count=-1;
	}
	
	@RequestMapping("/usr/home/getCount")//get은 값을 가져오다
	@ResponseBody
	public int showMain4() {
		
		return count;//count를 1씩 증가해서 응답하는 메소드가된다. count값이 초기화 되지않을려면 count가 지역변수가 되어야함
	}
	
	@RequestMapping("/usr/home/doSetCount")//set은 값을 셋팅하다
	@ResponseBody
	public String doSetCount(int count) {//파라미터를 매개변수로 받고
		this.count=count; //지역변수count를 매개변수값으로 셋팅후 응답하는 메소드가됨
		return "count값이 "+this.count+"로 초기화됨";
	}

}
