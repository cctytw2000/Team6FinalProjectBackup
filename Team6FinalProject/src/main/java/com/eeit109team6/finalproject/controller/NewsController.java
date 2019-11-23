package com.eeit109team6.finalproject.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eeit109team6.finalproject.model.Activity;
import com.eeit109team6.finalproject.model.ActivityType;
import com.eeit109team6.finalproject.model.Game;
import com.eeit109team6.finalproject.model.GameType;
import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.Message;
import com.eeit109team6.finalproject.model.News;
import com.eeit109team6.finalproject.model.NewsType;
import com.eeit109team6.finalproject.service.IActivityService;
import com.eeit109team6.finalproject.service.IGameService;
import com.eeit109team6.finalproject.service.INewsService;

@Controller
public class NewsController {

	public NewsController() {
	}

	ServletContext context;

	@Autowired
	public void setContext(ServletContext context) {
		this.context = context;
	}

	INewsService newsService;
	IGameService gameService;
	IActivityService activityService;

	@Autowired
	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	@Autowired
	public void setGameService(IGameService gameService) {
		this.gameService = gameService;
	}

	@Autowired
	public void setActivityService(IActivityService activityService) {
		this.activityService = activityService;
	}

//====================================================消息類別=================================================

	// 新增消息類別
	@RequestMapping("/newsBack/addNewsType")
	public String addNewsType(@RequestParam("newsTypeName") String newsTypeName) {
		NewsType nt = new NewsType();
		nt.setNewsTypeName(newsTypeName);
		newsService.addNewsType(nt);
		// return "redirect:/newsBack";
		return "redirect:/newsBack";
	}

	// 更新消息類別名稱-->newsBack.jsp
	@RequestMapping(value = "/updateNewsType", method = RequestMethod.POST)
	public String updateNewsTypeById(@RequestParam("newsTypeId") Integer newsTypeId,
			@RequestParam("newsTypeName") String newsTypeName) {
		System.out.println("newsTypeName=" + newsTypeName);
		System.out.println("updateNewsType");
		NewsType nt = newsService.getNewsTypeById(newsTypeId);
		nt.setNewsTypeName(newsTypeName);
		newsService.updateNewsTypeById(nt);

		return "redirect:/newsBack";
	}

	// 刪除消息類別-->newsBack.jsp
	@RequestMapping(value = "/deleteNewsType", method = RequestMethod.POST)
	public String deleteNewsTypeById(@RequestParam("newsTypeId") Integer newsTypeId) {
		newsService.deleteNewsTypeById(newsTypeId);
		return "redirect:/newsBack";
	}

	// 取得所有消息類別的json格式
	@RequestMapping(value = "/newsBack/searchNewsTypeByAjax", produces = "application/json")
	public @ResponseBody List<NewsType> searchNewsTypeByAjax() {
		return newsService.getAllNewsTypes();
	}

	// 取得所有消息類別的json格式
	@RequestMapping(value = "searchNewsTypeByAjax1", produces = "application/json")
	public @ResponseBody List<NewsType> searchNewsTypeByAjax1() {
		return newsService.getAllNewsTypes();
	}

//====================================================消息====================================================

	// 導向新增消息頁面--> addNews.jsp
	@RequestMapping(value = "/newsBack/addNews", method = RequestMethod.GET)
	public String getAddNewNewsForm() {
		return "addNews";
	}

	// 新增消息資料--> 導向消息後台newsBack.jsp
	@RequestMapping(value = "/newsBack/addNews1", method = RequestMethod.POST)
	public String processAddNewNewsForm(@RequestParam("newsImage") MultipartFile newsImage, HttpServletRequest request,
			HttpSession session) throws ParseException {
		News news = new News();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date publicationDate = format.parse(format.format(new Date()));// 取得發文時間

		news.setMember((Member) session.getAttribute("mem"));
		news.setIpAddress(request.getRemoteAddr());// 取得發文位置
		news.setPublicationDate(publicationDate);
		news.setNewsType(newsService.getNewsTypeById(Integer.parseInt(request.getParameter("newsType"))));
		if (!(request.getParameter("game") == null)) {
			news.setGame(gameService.getGameById(Integer.parseInt(request.getParameter("game"))));
		}
		if (!(request.getParameter("activity") == null)) {
			news.setActivity(activityService.getActivityById(Integer.parseInt(request.getParameter("activity"))));
		}
		news.setTitle(request.getParameter("title"));
		news.setArticle(request.getParameter("article"));
		if (request.getParameter("isVisable") == null) {

		} else if (Integer.parseInt(request.getParameter("isVisable")) == 1) {
			news.setIsVisable(true);
		} else {
			news.setIsVisable(false);
		}

//		測試上傳圖片
		System.out.println("newsImage=" + newsImage);
		String originalFilename = newsImage.getOriginalFilename();
		if (newsImage != null && !newsImage.isEmpty()) {
			try {
				byte[] b = newsImage.getBytes();
				Blob blob = new SerialBlob(b);
				news.setPicture(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常:" + e.getMessage());
			}
		}

		newsService.addNews(news);

		return "redirect:/newsBack";
	}

	// 發佈消息隱藏--> 消息後台 newsBack.jsp
	@RequestMapping("/deleteNewsShow")
	public String deleteNewsShow(@RequestParam("newsId") Integer newsId, Model model) {
		newsService.deleteNewsShow(newsId);
		List<News> news = newsService.getAllNews();
		model.addAttribute("news", news);
		return "redirect:/newsBack";
	}

	// 隱藏消息發佈--> 消息後台 newsBack.jsp
	@RequestMapping("/reopenNews")
	public String reopenNews(@RequestParam("newsId") Integer newsId, Model model) {
		newsService.reopenNews(newsId);
		List<News> news = newsService.getAllNews();
		model.addAttribute("news", news);
		return "redirect:/newsBack";
	}

	// 讀取圖片
	@RequestMapping(value = "/getNewsPicture/{newsid}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer newsid) {
		String filePath = "resources/images/NoImage.jpg";
		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		int len = 0;
		News news = newsService.getNewsById(newsid);
		if (news != null) {
			Blob blob = news.getPicture();
			if (blob != null) { // 資料庫有圖片
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				} catch (SQLException e) {
					throw new RuntimeException("ProductController的getPicture()發生SQLException:" + e.getMessage());
				}
			} else { // 資料庫沒圖片
				media = toByteArray(filePath);
			}
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	// 讀取圖片
	private byte[] toByteArray(String filePath) {
		byte[] b = null;
		String realPath = context.getRealPath(filePath);
		try {
			File file = new File(realPath);
			long size = file.length();
			b = new byte[(int) size];
			InputStream fis = context.getResourceAsStream(filePath);
			fis.read(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	// 用ajax給viewsx欄位加一
	@RequestMapping(value = "/countForNews", method = RequestMethod.POST)
	public void countForNews(@RequestParam("newsId") Integer newsId, @RequestParam("count") Integer views) {
//			System.out.println("newsId=" + newsId);
//			System.out.println("views=" + views);
		News news = newsService.getNewsById(newsId);
//			System.out.println("views2="+news.getViews());
		Integer i = news.getViews();
		if (i == null) {
			i = 0;
			news.setViews(i + views);
//				System.out.println("views3="+news.getViews());
		} else {
			news.setViews(i + views);
//				System.out.println("views4="+news.getViews());
		}
		newsService.updateNewsById(news);

	}

	// 新增消息評論-> 消息細節
	@RequestMapping(value = "/addMemo", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> addMemo(@RequestParam("memo") String memo,
			@RequestParam("newsId") Integer newsId, @RequestParam("member_id") Integer member_id, Model model,
			HttpSession session, HttpServletRequest request) {
//			System.out.println("memo="+memo);
//			System.out.println("newsId="+newsId);
//			System.out.println("member_id="+member_id);

		Member member = (Member) session.getAttribute("mem");
		Message m = new Message();
		m.setMemo(memo);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String sd = sdFormat.format(d);
		m.setPublicationDate(sd);
		;
		m.setIpAddress(request.getRemoteAddr());// 取得發文位置
		News n = newsService.getNewsById(newsId);
		m.setNews(n);
		m.setMember(member);
		m.setIsVisable(true);

		newsService.addMemo(m);

		Map<String, String> messageMap = new HashMap<>();
		messageMap.put("messageId", String.valueOf(m.getMessageId()));
		messageMap.put("memberId", String.valueOf(m.getMember().getMember_id()));
		messageMap.put("userName", m.getMember().getUsername());
		messageMap.put("memo", m.getMemo());
		messageMap.put("publicationDate", m.getPublicationDate());

//			System.out.println(messageMap);

		return messageMap;
	}

	// 進入更新單筆消息詳細資料表單--> updateNews.jsp
	@RequestMapping("/updateNews")
	public String updateNewsById(HttpServletRequest request, Model model) {

		News news = newsService.getNewsById(Integer.parseInt(request.getParameter("newsId")));
		model.addAttribute("news", news);

		return "updateNews";
	}

	// 更新消息資訊
	@RequestMapping("/updateNewsDetail")
	public String updateNewsDetailById(@RequestParam("newsId") Integer newsId,
			@RequestParam("newsType") Integer newsTypeId, String title, String article, MultipartFile newsImage,
			HttpServletRequest request) {

		News original = newsService.getNewsById(newsId);
		original.setNewsType(newsService.getNewsTypeById(newsTypeId));
		original.setTitle(title);
		original.setArticle(article);

		// 上傳圖片begin
		if (newsImage.getSize() == 0) {
			original.setPicture(original.getPicture());
		} else {
			if (newsImage != null && !newsImage.isEmpty()) {
				try {
					byte[] b = newsImage.getBytes();
					Blob blob = new SerialBlob(b);
					original.setPicture(blob);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常:" + e.getMessage());
				}
			}
		}

		// 上傳圖片end
		newsService.updateNewsById(original);

		return "redirect:/newsBack";
	}

	// 刪除遊戲細節-->updateNews.jsp
	@RequestMapping(value = "/deleteGameInUpdateNews", method = RequestMethod.POST)
	public String deleteGameInUpdateNews(@RequestParam("gameId") Integer gameId, @RequestParam("newsId") Integer newsId,
			HttpSession session) {
//			System.out.println("newsId==" + newsId);
		session.setAttribute("updateNewsId", newsId);
		News news = newsService.getNewsById(newsId);
		if (gameId == null) {
			news.setGame(null);
		}
		newsService.updateNewsById(news);
		return "redirect:/updateNews?newsId=" + newsId;
	}

	// 更改遊戲細節-->updateNews.jsp
	@RequestMapping(value = "/updateGameInUpdateNews", method = RequestMethod.POST)
	public String updateGameInUpdateNews(@RequestParam("game") Integer gameId, @RequestParam("newsId") Integer newsId,
			HttpSession session) {
//				System.out.println("newsId==" + newsId);
		session.setAttribute("updateNewsId", newsId);
		News news = newsService.getNewsById(newsId);
		Game game = gameService.getGameById(gameId);
		news.setGame(game);

		newsService.updateNewsById(news);
		return "redirect:/updateNews?newsId=" + newsId;
	}

	// 新增遊戲細節-->updateNews.jsp
	@RequestMapping(value = "/showGameInUpdateNews", method = RequestMethod.POST)
	public String showGameInUpdateNews(@RequestParam("game") Integer gameId, @RequestParam("newsId") Integer newsId,
			HttpSession session) {
//			System.out.println("newsId==" + newsId);
		session.setAttribute("updateNewsId", newsId);
		News news = newsService.getNewsById(newsId);
		Game game = gameService.getGameById(gameId);
		news.setGame(game);

		newsService.updateNewsById(news);
		return "redirect:/updateNews?newsId=" + newsId;
	}

	// 刪除活動細節-->updateNews.jsp
	@RequestMapping(value = "/deleteActivityInUpdateNews", method = RequestMethod.POST)
	public String deleteActivityInUpdateNews(@RequestParam("activityId") Integer activityId,
			@RequestParam("newsId") Integer newsId, HttpSession session) {
//			System.out.println("newsId==" + newsId);
		session.setAttribute("updateNewsId", newsId);
		News news = newsService.getNewsById(newsId);
		if (activityId == null) {
			news.setActivity(null);
		}
		newsService.updateNewsById(news);
		return "redirect:/updateNews?newsId=" + newsId;
	}

	// 更改活動細節-->updateNews.jsp
	@RequestMapping(value = "/updateActivityInUpdateNews", method = RequestMethod.POST)
	public String updateActivityInUpdateNews(@RequestParam("activity") Integer activityId,
			@RequestParam("newsId") Integer newsId) {
//					System.out.println("newsId==" + newsId);
		News news = newsService.getNewsById(newsId);
		Activity activity = activityService.getActivityById(activityId);
		news.setActivity(activity);

		newsService.updateNewsById(news);
		return "redirect:/updateNews?newsId=" + newsId;
	}

	// 新增活動細節-->updateNews.jsp
	@RequestMapping(value = "/showActivityInUpdateNews", method = RequestMethod.POST)
	public String showActivityInUpdateNews(@RequestParam("activity") Integer activityId,
			@RequestParam("newsId") Integer newsId) {
//		System.out.println("newsId==" + newsId);
		News news = newsService.getNewsById(newsId);
		Activity activity = activityService.getActivityById(activityId);
		news.setActivity(activity);

		newsService.updateNewsById(news);
		return "redirect:/updateNews?newsId=" + newsId;
	}
	
	@RequestMapping(value = "/searchByKeyWord", method = RequestMethod.POST)
	public void searchByKeyWord(@RequestParam("keyWord") String keyWord, Model model) {
		model.addAttribute("news", newsService.getNewsByKeyWord(keyWord));
	}

//========================================未完成========================================

	// 查詢所有後臺消息類別--> 消息後台 newsBack.jsp
	@RequestMapping("/newsBack")
	public String newsListBack(Model model) throws ParseException {
		List<NewsType> newsTypeList = newsService.getAllNewsTypes();
		model.addAttribute("newsTypeList", newsTypeList);

		List<GameType> gameTypeList = gameService.getAllGameTypes();
		model.addAttribute("gameTypeList", gameTypeList);

		List<ActivityType> activityTypeList = activityService.getAllActivityTypes();
		model.addAttribute("activityTypeList", activityTypeList);

		List<Game> gameList = gameService.getAllGames();
		model.addAttribute("gameList", gameList);

		List<Activity> activityOneList = activityService.getAllActivities();
		for (Activity a : activityOneList) {
			if (a.getStartingTime_date().equals("00:00:00")) {
				a.setStartingTime_date("");
			}
		}
		// for迴圈要remove List裡的項目要用倒敘刪除，不然會跳過某些值
		for (int i = activityOneList.size() - 1; i >= 0; i--) {
			if (!((activityOneList.get(i)).getStartingDate().equals(""))
					&& !((activityOneList.get(i)).getEndingDate().equals(""))) {
				activityOneList.remove(i);
			}
		}
		model.addAttribute("activityOneList", activityOneList);

		List<Activity> activityMoreList = activityService.getAllActivities();
		for (int i = activityMoreList.size() - 1; i >= 0; i--) {
			if ((!(activityMoreList.get(i)).getStartingDate_time().equals(""))
					&& (!(activityMoreList.get(i)).getStartingTime_date().equals("00:00:00"))) {
				activityMoreList.remove(i);
			}
		}
		model.addAttribute("activityMoreList", activityMoreList);

		List<News> newsShowList = newsService.getAllNews();
		for (int i = newsShowList.size() - 1; i >= 0; i--) {
//			newsShowList.get(i).getPublicationDate();//2019-11-14 20:00:49.0
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String datetime =format.format(newsShowList.get(i).getPublicationDate());//2019-11-14 20:00:49
//			format.parse(datetime);//Thu Nov 14 20:00:49 GMT+08:00 2019	

			if (newsShowList.get(i).getIsVisable() == false) {
				newsShowList.remove(i);
			}
		}
		model.addAttribute("newsShowList", newsShowList);

		List<News> newsHideList = newsService.getAllNews();
		for (int i = newsHideList.size() - 1; i >= 0; i--) {
			if (newsHideList.get(i).getIsVisable() == true) {
				newsHideList.remove(i);
			}
		}
		model.addAttribute("newsHideList", newsHideList);

		return "newsBack";
	}

	// 前台查詢所有消息類別--> 消息後台 newsPage.jsp
	@RequestMapping("/newsPage")
	public String newsPage(Model model) {
		List<News> newsList = newsService.getAllNewsByTime();
		model.addAttribute("newsList", newsList);

		List<NewsType> newsTypeList = newsService.getAllNewsTypes();
		model.addAttribute("newsTypeList", newsTypeList);

		return "newsPage";
	}

	// 取得所有消息的json格式(某部分東西會取不到!!!!!)
	@RequestMapping(value = "/searchNewsByAjax.json")
	public String searchNewsByAjax(Model model) {
		ArrayList<News> news = (ArrayList<News>) newsService.getAllNewsByTime();
		model.addAttribute("newsList", news);
		ArrayList<News> list = (ArrayList<News>) newsService.getAllNewsByViews();
		model.addAttribute("newses", list);
		return "newsPage";
	}

	// 查詢單筆消息詳細資料--> newsDetail.jsp
	@RequestMapping("/newsDetail")
	public String getNewsDetailById(@RequestParam("newsId") Integer newsId, Model model, HttpSession session) {
		News news = newsService.getNewsById(newsId);
		model.addAttribute("news", news);

		List<Message> messagesList = newsService.getMessagesByNewsId(newsId);
//		for(Message m:messages) {
//			System.out.println("m="+m.getMember().getMember_id());
//		}
		model.addAttribute("messagesList", messagesList);

		return "newsDetail";
	}
	
	@RequestMapping(value = "/hotNewsTop5.json")
	public String hotNewsTop5( Model model) {
		Map<String, Integer> data = new LinkedHashMap();
		
		List<News> newsesList = newsService.getAllNewsByViews();
		for(int i=0 ; i<=4 ;i++) {
			data.put(newsesList.get(i).getTitle(),newsesList.get(i).getViews());
		}
		
		ArrayList hotNewsTop5 = new ArrayList();
		hotNewsTop5.add(data);
		model.addAttribute("hotNewsTop5",hotNewsTop5);
		
		return "newsBack";
	}

}
