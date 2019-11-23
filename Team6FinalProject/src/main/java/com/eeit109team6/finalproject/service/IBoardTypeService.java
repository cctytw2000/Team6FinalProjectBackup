package com.eeit109team6.finalproject.service;

import java.util.List;

import com.eeit109team6.finalproject.model.BoardType;

public interface IBoardTypeService {
	
	List<BoardType> getAllBoardType();	//列出所有討論看板
	BoardType getBoardTypeById(int boardId);//依照id取得一個看板的資訊
	void addBoardType(BoardType boardType);	//新增一個討論看板
	void updateBoardTypeById(BoardType boardType);//修改討論看板名稱
	void physicalDeleteBoardById(Integer boardId);//刪除看板
	
}
