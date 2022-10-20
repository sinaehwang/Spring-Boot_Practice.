package com.hsn.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hsn.exam.demo.vo.Board;

@Mapper
public interface BoardRepository {
	
	@Select("""
			SELECT * FROM board
			WHERE board.id = #{id}
			AND board.delStatus = 0
			""")
	Board getBoardId(int boardId);

	@Select("""
			<script>
			SELECT COUNT(*) FROM article
			LEFT JOIN `member` 
			ON article.memberId = `member`.Id
			WHERE 1=1 
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
			ORDER BY article.id DESC
			</script>
			"""
			)
	int getTotalPageCount(int boardId, String searchKeywordType, String searchKeyword);




}
