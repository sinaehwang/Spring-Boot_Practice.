package com.hsn.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hsn.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {

	public Article writeArticle(String title, String body);
	// INSER INTO article SET regDate = NOW(), updateDate = NOW(), title = ?, body =?
	
	@Select("SELECT * FROM article WHERE id = #{id}")
	public Article getArticle(@Param("id") int id);
	
	@Delete("DELETE FROM article WHERE id = #{id}")
	public void deleteArticle(@Param("id")int id);
	// DELETE FROM article WHERE id = ?
	@Update("UPDATE article SET regDate = NOW(), updateDate = NOW(), title = #{title} , `body` = #{body} WHERE id = #{id}")
	public void modifyArticle(@Param("id")int id,@Param("title")String title, @Param("body")String body); 
	// UPDATE article SET regDate = NOW(), updateDate = NOW(), title = ? , body = ?WHERE id = ?
	@Select("SELECT * FROM article ORDER BY id DESC")
	public List<Article> getArticles();
	// SELECT * FROM article ORDER BY id DESC
}
