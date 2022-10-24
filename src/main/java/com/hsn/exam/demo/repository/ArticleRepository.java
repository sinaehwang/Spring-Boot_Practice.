package com.hsn.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hsn.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {

	public void writeArticle(int boardId, String title, String body, int memberId); // 리턴타입이 없기때문에 id를 생성할수없어 타입오류가 발생됨

	public Article getForPrintArticle(int id);

	public void deleteArticle(int id);

	public int getLastInsertId();

	public void modifyArticle(int id, String title, String body);

	@Select("""
			<script>
			SELECT A.*, M.nickname AS
			extra_writerName
			FROM article AS A
			LEFT JOIN `member` AS M
			ON A.memberId
			= M.id WHERE 1
			<if test="boardId != 0">
				AND A.boardId = #{boardId}
			</if>
			<if test="searchKeyword != ''">
				<choose>
					<when test="searchKeywordType == 'title'">
						AND A.title LIKE CONCAT('%', #{searchKeyword}, '%')
					</when>
					<when test="searchKeywordType == 'body'">
						AND A.body LIKE CONCAT('%', #{searchKeyword}, '%')
					</when>
					<otherwise>
						AND (
							A.title LIKE CONCAT('%', #{searchKeyword}, '%')
							OR A.body LIKE CONCAT('%', #{searchKeyword}, '%')
							)
					</otherwise>
				</choose>
			</if>
			ORDER BY A.id DESC
			<if test="limitTake != -1">
				LIMIT #{limitStart}, #{limitTake}
			</if>
			</script>
				""")
	public List<Article> getArticles(int boardId, int limitStart, int limitTake, String searchKeywordType,String searchKeyword);// 시작페이지와 마지막페이지를 넘겨준다.


	
	@Select("""
			<script>
			SELECT COUNT(*) FROM article
			LEFT JOIN `member` 
			ON article.memberId = `member`.Id
			WHERE 1
			<if test="boardId != 0">
				AND article.boardId = #{boardId}
			</if>
			<if test="searchKeyword != ''">
				<choose>
					<when test = "searchKeywordType == 'title'" >
						AND article.title LIKE CONCAT('%',#{searchKeyword},'%') 
					</when>
					
					<when test = "searchKeywordType == 'body'" >
						AND article.body LIKE CONCAT('%',#{searchKeyword},'%')
					</when>
					
					<otherwise>
						AND (
							article.title LIKE CONCAT('%',#{searchKeyword},'%')
							OR
							article.body LIKE CONCAT('%',#{searchKeyword},'%')
						)
					</otherwise>
				</choose>
			</if>
			</script>
			"""
			)
	public int getTotalPageCount(int boardId, String searchKeywordType, String searchKeyword);
}
