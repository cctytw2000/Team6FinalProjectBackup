package com.eeit109team6.finalproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eeit109team6.finalproject.dao.IBoardTypeDao;
import com.eeit109team6.finalproject.model.BoardType;
import com.eeit109team6.finalproject.model.Discussion;

@Repository
public class BoardTypeDaoImpl implements IBoardTypeDao {

	SessionFactory factory;
	
	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public List<BoardType> getAllBoardType() {
		String hql = "FROM BoardType";
		List<BoardType> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public BoardType getBoardTypeById(int boardId) {
		Session session = factory.getCurrentSession();
		BoardType boardType = session.get(BoardType.class, boardId);
		return boardType;
	}
	
	@Override
	public void addBoardType(BoardType boardType) {
		Session session = factory.getCurrentSession();
		session.save(boardType);
	}

	@Override
	public void updateBoardTypeById(BoardType boardType) {
		Session session = factory.getCurrentSession();
//		session.clear();	//強制清除快取
		session.update(boardType);
	}

	@Override
	public void physicalDeleteBoardById(Integer boardId) {
		Session session = factory.getCurrentSession();
		BoardType boardType = session.get(BoardType.class, boardId);
		session.delete(boardType);
	}

}
