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




}
