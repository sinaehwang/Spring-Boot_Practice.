package com.hsn.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hsn.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {

	public void writeArticle(String title,String body,int memberId); //리턴타입이 없기때문에 id를 생성할수없어 타입오류가 발생됨
	
	public Article getArticle(int id);
	
	public void deleteArticle(int id);

	public void modifyArticle(int id,String title, String body); 

	public List<Article> getArticles();

	public int getLastInsertId();
}
