package com.hsn.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrController {
	@RequestMapping("/usr/home/getString")
	@ResponseBody
	public String getString() {
		return "hi";
	}

}

