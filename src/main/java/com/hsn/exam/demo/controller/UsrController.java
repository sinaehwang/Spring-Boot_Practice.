package com.hsn.exam.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsn.exam.demo.vo.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UsrController {
	@RequestMapping("/usr/home/getString")
	@ResponseBody
	public String getString() {
		return "hi";
	}
	
	@RequestMapping("/usr/home/getInt")
	@ResponseBody
	public int getInt() {
		return 1;
	}
	
	@RequestMapping("/usr/home/getFloat")
	@ResponseBody
	public float getFloat() {
		return 10.5f;
	}
	
	@RequestMapping("/usr/home/getDouble")
	@ResponseBody
	public double getDouble() {
		return 1.5;
	}
	
	@RequestMapping("/usr/home/getChar")
	@ResponseBody
	public char getChar() {
		return 'a';
	}
	
	@RequestMapping("/usr/home/getMap")
	@ResponseBody
	public Map<String,Object> getMap() {
		
		Map<String,Object> map = new HashMap<>();
		map.put("철수나이", 20);
		map.put("영희나이", 10);
		return map;
	}
	
	@RequestMapping("/usr/home/getList")
	@ResponseBody
	public List<String> getList() {//메소드 출력후 브라우져에 나타나는것은 웹브라우져에 보여지도록 자바스크립트로 정제후 문장타입으로 나타나게 되는것. 
		
		List<String>list = new ArrayList<>();
		
		list.add("지역");
		list.add("회사");
		
		return list;
	}
	
	@RequestMapping("/usr/home/getArticle")
	@ResponseBody
	public Article getArticle() {
		
		Article article = new Article(2, "제목2", "내용2");
		Article article2 = new Article();
		
		return article2;
	}
	
	@RequestMapping("/usr/home/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		
		List<Article> articles = new ArrayList<>();
		
		Article article = new Article(1, "제목1", "내용1");
		Article article2 = new Article();
		articles.add(article);
		
		article2.setId(2);
		article2.setTitle("제목2");
		article2.setBody("내용2");
		articles.add(article2);
		
		return articles;
	}
}

