package com.eeit109team6.finalproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eeit109team6.finalproject.dao.IGameDao;
import com.eeit109team6.finalproject.model.Game;
import com.eeit109team6.finalproject.model.GameType;

@Repository
public class GameDaoImpl implements IGameDao {

	public GameDaoImpl() {
	}

	SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//====================================================遊戲類別==================================================
	
	@Override
	public void addGameType(GameType gametype) {
		Session session = sessionFactory.getCurrentSession();
		session.save(gametype);
	}

	@Override
	public List<GameType> getAllGameTypes() {
		String hql = "FROM GameType";
		Session session = sessionFactory.getCurrentSession();
		List<GameType> list = new ArrayList<>();
		list = session.createQuery(hql).getResultList();
		return list;
	}
	
	@Override
	public GameType getGameTypeById(Integer gameTypeId) {
		Session session = sessionFactory.getCurrentSession();
		GameType gameType = session.get(GameType.class, gameTypeId);
		return gameType;
	}
	
	@Override
	public void updateGameTypeById(GameType gameType) {
		Session session = sessionFactory.getCurrentSession();
//		session.clear();
		session.update(gameType);
	}

	@Override
	public void deleteGameTypeById(Integer gameTypeId) {
		Session session = sessionFactory.getCurrentSession();
		GameType gt = session.get(GameType.class, gameTypeId);
		session.delete(gt);			
	}

//====================================================遊戲====================================================

	@Override
	public List<Game> getAllGames() {
		String hql = "FROM Game";
		List<Game> list = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}
	

	@Override
	public Game getGameById(Integer gameId) {
		Session session = sessionFactory.getCurrentSession();
		Game game = session.get(Game.class, gameId);
		return game;
	}
	
	@Override
	public void addGame(Game game) {
		Session session = sessionFactory.getCurrentSession();
		session.save(game);
	}
	
	@Override
	public void deleteGameById(Integer gameId) {
		Session session = sessionFactory.getCurrentSession();
		Game game = session.get(Game.class, gameId);
		session.delete(game);

	}
	
	@Override
	public void updateGameById(Game game) {
		Session session = sessionFactory.getCurrentSession();
		session.update(game);
	}
	
//====================================================未完成===================================================

	





	


}
