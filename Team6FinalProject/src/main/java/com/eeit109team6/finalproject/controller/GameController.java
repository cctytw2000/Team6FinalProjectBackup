package com.eeit109team6.finalproject.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eeit109team6.finalproject.model.Game;
import com.eeit109team6.finalproject.model.GameType;
import com.eeit109team6.finalproject.model.NewsType;
import com.eeit109team6.finalproject.service.IGameService;
import com.eeit109team6.finalproject.service.INewsService;

@Controller
public class GameController {

	public GameController() {
	}

	ServletContext context;

	@Autowired
	public void setContext(ServletContext context) {
		this.context = context;
	}

	IGameService gameService;
	INewsService newsService;

	@Autowired
	public void setGameService(IGameService gameService) {
		this.gameService = gameService;
	}

	@Autowired
	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

// ====================================================遊戲類別=================================================

	// 新增遊戲類別
	@RequestMapping("/newsBack/addGameType")
	public String addGameType(@RequestParam("gameTypeName") String gameTypeName) {
		GameType gt = new GameType();
		gt.setGameTypeName(gameTypeName);
		gameService.addGameType(gt);
		return "redirect:/newsBack";
	}

	// 取得所有遊戲類別的json格式
	@RequestMapping(value = "/newsBack/searchGameTypeByAjax", produces = "application/json")
	public @ResponseBody List<GameType> searchGameTypeByAjax() {
		return gameService.getAllGameTypes();
	}

	// 查詢所有遊戲類別並存入Model(for form:form)
	@ModelAttribute("gameTypes")
	public List<GameType> getGameTypes() {
		return gameService.getAllGameTypes();
	}

	// 更新遊戲類別名稱-->newsBack.jsp
	@RequestMapping(value = "/updateGameType", method = RequestMethod.POST)
	public String updateGameTypeById(@RequestParam("gameTypeId") Integer gameTypeId,
			@RequestParam("gmaeTypeName") String gameTypeName) {
		GameType gt = gameService.getGameTypeById(gameTypeId);
		gt.setGameTypeName(gameTypeName);
		gameService.updateGameTypeById(gt);

		return "redirect:/newsBack";
	}

	// 刪除遊戲類別-->newsBack.jsp
	@RequestMapping(value = "/deleteGameType", method = RequestMethod.POST)
	public String deleteGameTypeById(@RequestParam("gameTypeId") Integer gameTypeId) {
		gameService.deleteGameTypeById(gameTypeId);
		return "redirect:/newsBack";
	}

//====================================================遊戲====================================================

	// 導向新增遊戲頁面--> addGame.jsp
	@RequestMapping(value = "/newsBack/addGame", method = RequestMethod.GET)
	public String getAddNewGameForm(Model model) {
		Game game = new Game();
		model.addAttribute("game", game);

		List<GameType> list = gameService.getAllGameTypes();
		Map<Integer, String> gameTypeMap = new HashMap<>();
		for (GameType g : list) {
			gameTypeMap.put(g.getGameTypeId(), g.getGameTypeName());
		}
		model.addAttribute("gameTypeMap", gameTypeMap);

		return "addGame";
	}

	// 新增遊戲--> 消息後台 newsBack.jsp
	@RequestMapping(value = "/newsBack/addGame1", method = RequestMethod.POST)
	public String processAddNewGameForm(@ModelAttribute("game") Game game) throws ParseException {
		Integer gt_ = game.getGameType_(); // 取回遊戲類別分類id
		GameType gt = gameService.getGameTypeById(gt_); // 利用id取得遊戲類別
		// 改變日期格式
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		if (!(game.getPublicationDate() == "")) {
			String PublicationDate = format1.format((Date) format.parse(game.getPublicationDate()));
			game.setPublicationDate(PublicationDate);
		}
		game.setGameType(gt); // 設定遊戲的遊戲類別
		gameService.addGame(game);
		return "redirect:/newsBack";
	}

	// 取得所有遊戲的json格式
	@RequestMapping(value = "/newsBack/searchGameByAjax",produces = "application/json")
	public @ResponseBody List<Game> searchGameByAjax() {
		return gameService.getAllGames();
	}

	// 用ajax傳回gameDetail給addNews.jsp
	@RequestMapping(value = "/newsBack/searchGameByAjax1", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> searchGameByAjax1(@RequestParam("gameId") Integer gameId) {
		System.out.println(gameId);
		Game g = gameService.getGameById(gameId);
		Map<String, String> gameMap = new HashMap<>();
		gameMap.put("gameTypeName", g.getGameType().getGameTypeName());
		gameMap.put("name", g.getGameName());
		// 若未設定發售日則傳回未設定
		if (g.getPublicationDate().equals("")) {
			gameMap.put("publicationDate", "未設定");
		} else {
			gameMap.put("publicationDate", g.getPublicationDate());
		}
		gameMap.put("publisher", g.getPublisher());
		gameMap.put("platform", g.getPlatform());

		return gameMap;
	}
	
	// 取得所有遊戲的json格式
		@RequestMapping(value = "/searchGameByAjax2_1",produces = "application/json")
		public @ResponseBody List<Game> searchGameByAjax2_1() {
			return gameService.getAllGames();
		}
	
	// 用ajax傳回gameDetail給updateNews.jsp
		@RequestMapping(value = "/searchGameByAjax2", method = RequestMethod.POST)
		public @ResponseBody Map<String, String> searchGameByAjax2(@RequestParam("gameId") Integer gameId) {
			System.out.println(gameId);
			Game g = gameService.getGameById(gameId);
			Map<String, String> gameMap = new HashMap<>();
			gameMap.put("gameTypeName", g.getGameType().getGameTypeName());
			gameMap.put("name", g.getGameName());
			// 若未設定發售日則傳回未設定
			if (g.getPublicationDate().equals("")) {
				gameMap.put("publicationDate", "未設定");
			} else {
				gameMap.put("publicationDate", g.getPublicationDate());
			}
			gameMap.put("publisher", g.getPublisher());
			gameMap.put("platform", g.getPlatform());

			return gameMap;
		}

	// 更新遊戲細節-->newsBack.jsp
	@RequestMapping(value = "/updateGame", method = RequestMethod.POST)
	public String updateGameById(@RequestParam("gameId") Integer gameId, @RequestParam("gameName") String gameName,
			@RequestParam("gameType") Integer gameTypeId, @RequestParam("publicationDate") String publicationDate,
			@RequestParam("publisher") String publisher, @RequestParam("platform") String platform) {
		Game g = gameService.getGameById(gameId);
		System.out.println("gameTypeId:" + gameTypeId);
		GameType gt = gameService.getGameTypeById(gameTypeId);
		g.setGameName(gameName);
		g.setGameType(gt);
		g.setPublicationDate(publicationDate);
		g.setPublisher(publisher);
		g.setPlatform(platform);
		gameService.updateGameById(g);

		return "redirect:/newsBack";
	}

	// 刪除遊戲-->newsBack.jsp
	@RequestMapping(value = "/deleteGame", method = RequestMethod.POST)
	public String deleteGameById(@RequestParam("gameId") Integer gameId) {
		gameService.deleteGameById(gameId);
		return "redirect:/newsBack";
	}

}
