package com.hsn.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsn.exam.demo.repository.BoardRepository;
import com.hsn.exam.demo.vo.Board;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;

	}

	public Board getBoardId(int boardId) {
		
		return boardRepository.getBoardId(boardId);
	}


	
	
	

}
