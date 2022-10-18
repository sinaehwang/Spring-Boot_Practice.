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
			SELECT COUNT(*) FROM article
			LEFT JOIN `member` 
			ON article.memberId = `member`.Id
			WHERE 1=1 
			AND article.boardId = #{boardId}
			ORDER BY article.id DESC
			""")
	int getTotalPageCount(int boardId);




}
