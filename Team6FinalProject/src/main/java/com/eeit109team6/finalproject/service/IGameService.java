package com.eeit109team6.finalproject.service;

import java.util.List;

import com.eeit109team6.finalproject.model.Game;
import com.eeit109team6.finalproject.model.GameType;

public interface IGameService {
//====================================================遊戲類別=================================================
	void addGameType(GameType gameType);
	List<GameType> getAllGameTypes();
	GameType getGameTypeById(Integer gameTypeId);
	void updateGameTypeById(GameType gameType); //更新遊戲類別
	void deleteGameTypeById(Integer gameTypeId); //刪除遊戲類別
//====================================================遊戲====================================================
	void addGame(Game game); 
	List<Game> getAllGames(); 
	Game getGameById(Integer gameId);
//====================================================未完成===================================================		
	void deleteGameById(int gameId); 
	void updateGameById(Game game); 
}
