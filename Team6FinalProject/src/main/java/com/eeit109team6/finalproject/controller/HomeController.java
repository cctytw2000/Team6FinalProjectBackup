package com.eeit109team6.finalproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eeit109team6.finalproject.model.BoardType;
import com.eeit109team6.finalproject.model.Discussion;
import com.eeit109team6.finalproject.model.HomeMovie;
import com.eeit109team6.finalproject.model.MemberDetail;
import com.eeit109team6.finalproject.model.MovieInfo;
import com.eeit109team6.finalproject.model.News;
import com.eeit109team6.finalproject.model.Product;
import com.eeit109team6.finalproject.service.IBoardTypeService;
import com.eeit109team6.finalproject.service.IDiscussionService;
import com.eeit109team6.finalproject.service.IHomeMovieService;
import com.eeit109team6.finalproject.service.IMovieService;
import com.eeit109team6.finalproject.service.INewsService;
import com.eeit109team6.finalproject.service.ProductService;

@Controller
public class HomeController {
//	IMemberService service;
	ProductService service;
	IHomeMovieService homeMovieService;
	INewsService newsService;
	IDiscussionService discussionService;
	IBoardTypeService boardTypeService;
	IMovieService movieservice;

	@Autowired
	public void setHomeMovieService(IHomeMovieService homeMovieService) {
		this.homeMovieService = homeMovieService;
	}

	@Autowired
	public void setMovieservice(IMovieService movieservice) {
		this.movieservice = movieservice;
	}

	@Autowired
	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}
	
	@Autowired
	public void setDiscussionService(IDiscussionService discussionService) {
		this.discussionService = discussionService;
	}

	@Autowired
	public void setService(ProductService service) {
		this.service = service;
	}

	ServletContext context;

	@Autowired
	public void setContext(ServletContext context) {
		this.context = context;
	}

//	@Autowired
//	public void setMovieService(IHomeMovieService movieService) {
//		this.homeMovieService = movieService;
//	}
	
	
	// Run On Server -->  @RequestMapping(value ="/"     index(...) --> home.jsp (首頁)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, HttpSession session) {
		//		找出首頁影片 <!-- homeMovie movie_Id == 1  -->
		HomeMovie home = homeMovieService.findById(1);

		List<Product> list = service.getProductTop8();
		session.setAttribute("productsTop8", list);

		model.addAttribute("homeMovie", home);

		List<News> newslist = newsService.getAllNewsByViews();
		session.setAttribute("newses", newslist);

		List<Discussion> articleList = discussionService.getArticleTop6();
		session.setAttribute("articleTop6", articleList);
		
		List<BoardType> boardTypeList = discussionService.getBoardTopN();
		session.setAttribute("boardTop5", boardTypeList);

		ArrayList<MovieInfo> newMovies = movieservice.getNewMovieInfo(3);
		System.out.println("newMovies=" + newMovies.get(0).getMember().getAccount());
		model.addAttribute("newMovies", newMovies);

		return "home";
	}

	@RequestMapping(value = "/member/insertMemberInformationform", method = RequestMethod.GET)
	public String insertMemberInformationForm(@RequestParam("id") Integer memberid, @RequestParam("token") String token,
			Model model) {

		MemberDetail md = new MemberDetail();
		md.setAddress("美國");
		md.setToken(token);
		md.setId(memberid);

		model.addAttribute("MemberDetail", md);

		return "insertMemberDetail";
	}

	@RequestMapping(value = "/member/insertThirdPartyMemberInformationform", method = RequestMethod.GET)
	public String insertThirdPartyMemberInformationform(@RequestParam("id") Integer memberid,
			@RequestParam("type") String type, @RequestParam("username") String username, Model model) {

		MemberDetail md = new MemberDetail();
		md.setUsername(username);
		md.setId(memberid);
		md.setType(type);
		model.addAttribute("MemberDetail", md);

		return "insertMemberDetail";
	}

//	@RequestMapping(value = "/homeMovie/{id}.json")
//	public String homeMovie(@PathVariable("id") Integer id, Model model) {
//		HomeMovie home = movieService.findById(id);
//		model.addAttribute(home);
//		return "MovieBack";
//
//	}

}
