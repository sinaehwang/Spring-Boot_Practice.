package com.hsn.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hsn.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {

	@Insert("INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = #{title}, `body` =#{body}")
	public void writeArticle(String title,String body); //리턴타입이 없기때문에 id를 생성할수없어 타입오류가 발생됨
	// INSER INTO article SET regDate = NOW(), updateDate = NOW(), title = ?, body =?
	
	@Select("SELECT * FROM article WHERE id = #{id}")
	public Article getArticle(int id);
	
	@Delete("DELETE FROM article WHERE id = #{id}")
	public void deleteArticle(int id);
	// DELETE FROM article WHERE id = ?
	@Update("UPDATE article SET regDate = NOW(), updateDate = NOW(), title = #{title} , `body` = #{body} WHERE id = #{id}")
	public void modifyArticle(int id,String title, String body); 
	// UPDATE article SET regDate = NOW(), updateDate = NOW(), title = ? , body = ?WHERE id = ?
	@Select("SELECT * FROM article ORDER BY id DESC")
	public List<Article> getArticles();
	// SELECT * FROM article ORDER BY id DESC

	@Select("SELECT LAST_INSERT_ID()") //마지막 게시글의 id를 구해오는 쿼리문
	public int getLastInsertId();
}
