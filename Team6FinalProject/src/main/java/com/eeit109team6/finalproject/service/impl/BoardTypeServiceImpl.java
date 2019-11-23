package com.eeit109team6.finalproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.finalproject.dao.IBoardTypeDao;
import com.eeit109team6.finalproject.model.BoardType;
import com.eeit109team6.finalproject.service.IBoardTypeService;

@Service
public class BoardTypeServiceImpl implements IBoardTypeService {
	IBoardTypeDao dao;
	
	@Autowired
	public void setDao(IBoardTypeDao dao) {
		this.dao = dao;
	}

	public BoardTypeServiceImpl() {
	}

	@Transactional
	@Override
	public List<BoardType> getAllBoardType() {
		return dao.getAllBoardType();
	}
	
	@Transactional
	@Override
	public BoardType getBoardTypeById(int boardId) {
		return dao.getBoardTypeById(boardId);
	}

	@Transactional
	@Override
	public void addBoardType(BoardType boardType) {
		dao.addBoardType(boardType);
	}

	@Transactional
	@Override
	public void updateBoardTypeById(BoardType boardType) {
		dao.updateBoardTypeById(boardType);
	}

	@Transactional
	@Override
	public void physicalDeleteBoardById(Integer boardId) {
		dao.physicalDeleteBoardById(boardId);
	}


}
