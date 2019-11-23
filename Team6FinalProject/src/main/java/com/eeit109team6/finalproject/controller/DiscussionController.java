package com.eeit109team6.finalproject.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eeit109team6.finalproject.model.BoardType;
import com.eeit109team6.finalproject.model.Discussion;
import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.Reply;
import com.eeit109team6.finalproject.model.SubjectType;
import com.eeit109team6.finalproject.service.IBoardTypeService;
import com.eeit109team6.finalproject.service.IDiscussionService;
import com.eeit109team6.finalproject.service.IMemberService;
import com.eeit109team6.finalproject.service.IReplyService;
import com.eeit109team6.finalproject.service.ISubjectTypeService;

@Controller
public class DiscussionController {

	public DiscussionController() {
	}

	IDiscussionService discussionService;
	IBoardTypeService boardTypeService;
	ISubjectTypeService subjectTypeService;
	IReplyService replyService;

	IMemberService memberService;
	ServletContext context;

	@Autowired
	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	@Autowired
	public void setContext(ServletContext context) {
		this.context = context;
	}

	@Autowired
	public void setDiscussionService(IDiscussionService service) {
		this.discussionService = service;
	}

	@Autowired
	public void setBoardTypeService(IBoardTypeService boardTypeService) {
		this.boardTypeService = boardTypeService;
	}

	@Autowired
	public void setSubjectTypeService(ISubjectTypeService subjectTypeService) {
		this.subjectTypeService = subjectTypeService;
	}
	
	@Autowired
	public void setReplyService(IReplyService replyService) {
		this.replyService = replyService;
	}


	// 討論區主頁:列出所有看板 --> showDiscussion.jsp
	@RequestMapping("/discussion")
	public String getAllBoardType(Model model, HttpServletRequest request, HttpSession session) {

//		HttpSession session = request.getSession();
		System.out.println("進入控制器方法getAllBoardType()，即將導向視圖showDiscussion");
		
//		Member mem = (Member) session.getAttribute("mem");//從Session取得使用者會員    //這兩行若放在前台，非登入狀態點討論區主頁會500
//		System.out.println("Member的username:"+ mem.getUsername());
//		System.out.println("session內的username:" + session.getAttribute("username"));
		// boardTypeService實作方法向DAO取得所有看板的值，將其設給BoardType型態的物件組成的串列
		
//		System.out.println("使用者登入的ip位置:"+mem.getRemoteAddr());// 取得發文位置);
//		Discussion.setIpAddress(request.getRemoteAddr());// 取得發文位置
//		
//		List<Discussion> allArticlelist = discussionService.getAllArticles();
//		List<Integer> viewsByBoardType = By;
		
		// 取得最新發表的三篇文章
		List<Discussion> articlelist = discussionService.getLatestArticle();
		session.setAttribute("articleLatest3", articlelist);
		
		List<BoardType> list = boardTypeService.getAllBoardType();
		System.out.println("抓取看板列表");
		model.addAttribute(list);
		return "showDiscussion";
	}

	// 顯示全站討論區所有文章列表: 本方法暫無用武之地
//	@RequestMapping("/board")		
//	public String getAllArticles(Model model) {
//		List<Discussion> list = discussionService.getAllArticles();
//		model.addAttribute(list);//discussionList  不給定名字，則用物件首字小寫+型態首字大寫List。
//	return "board";
//	}

	// 進入指定看板，顯示指定看板的文章列表 --> board.jsp
	@RequestMapping("/board")
	public String getArticleByBoardTypeId(@RequestParam("id") Integer boardId, Model model) {

		// 1.取得指定看版屬性，並更新瀏覽人次，更新後再重取一次
		BoardType boardType = boardTypeService.getBoardTypeById(boardId);				
		discussionService.updateBoardViews(boardId); 
		BoardType boardType2 = boardTypeService.getBoardTypeById(boardId);// 透過service.getBoardTypeById方法取得一個指定看版的看板名稱
		
		// 2.取得指定看版的所有文章
		List<Discussion> Discussionlist = discussionService.getArticleByBoardTypeId(boardId);// 透過service.getArticleByBoardTypeId方法取得所有指定看板上的文章
		
		// 3.將屬性放入SpringMVC提供的model
		model.addAttribute("DiscussionList", Discussionlist);// 將指定看板上的所有文章物件，都注入model中，識別字串為DiscussionList
		model.addAttribute("boardType", boardType2); // 將指定看板的名稱物件，注入model中，識別字串為boardType

		// 說明:  discussionList 假若不給定名字，僅僅傳入list，則接收方JSP則以物件首字小寫+型態首字大寫List接收。
		// 將service實作類別取得的物件，設給Spring提供的Model介面的model物件
		// Spring提供的注入集合功能，支援List、Map、Properties、Set四種集合。ref:王本p48
		return "board";
	}
	
	// 進入指定看板，顯示指定看板的文章列表 --> board-Rich.jsp
	@RequestMapping("/board-Rich")
	public String getArticleByBoardTypeIdRich(@RequestParam("id") Integer boardId, Model model) {

		// 1.取得指定看版屬性，並更新瀏覽人次，更新後再重取一次
		BoardType boardType = boardTypeService.getBoardTypeById(boardId);				
		discussionService.updateBoardViews(boardId); 
		BoardType boardType2 = boardTypeService.getBoardTypeById(boardId);// 透過service.getBoardTypeById方法取得一個指定看版的看板名稱
		
		// for bootstrap版本發文表單，取得發文分類
		List<SubjectType> subjectType = subjectTypeService.getAllSubjectType();// 透過service.getAllSubjectType方法取得所有發文分類

		
		// 2.取得指定看版的所有文章
		List<Discussion> Discussionlist = discussionService.getArticleByBoardTypeId(boardId);// 透過service.getArticleByBoardTypeId方法取得所有指定看板上的文章
		
		// 3.將屬性放入SpringMVC提供的model
		model.addAttribute("DiscussionList", Discussionlist);// 將指定看板上的所有文章物件，都注入model中，識別字串為DiscussionList
		model.addAttribute("boardType", boardType2); // 將指定看板的名稱物件，注入model中，識別字串為boardType
		model.addAttribute("subjectType", subjectType); // 將分類名稱物件集合，注入model中，識別字串為subjectType

		// 說明:  discussionList 假若不給定名字，僅僅傳入list，則接收方JSP則以物件首字小寫+型態首字大寫List接收。
		// 將service實作類別取得的物件，設給Spring提供的Model介面的model物件
		// Spring提供的注入集合功能，支援List、Map、Properties、Set四種集合。ref:王本p48
		return "board-Rich";
	}
	

	// 瀏覽文章 --> article.jsp
	@RequestMapping("/article")
	public String getArticleById(Model model, @RequestParam("id") Integer articleId) {
		System.out.println("articleId:" + articleId);

		@SuppressWarnings("unused")
		Discussion discussion = discussionService.getArticleById(articleId);
		discussionService.updateViews(articleId); 
		Discussion discussion_sessDiscussion = discussionService.getArticleById(articleId);
		
		List<Reply> list = replyService.getReplyByArticle(articleId);		
		
		model.addAttribute("discussion", discussion_sessDiscussion);
		model.addAttribute("reply", list);

		return "article";
	}

//	提供新增文章時的表單 --> addArticle.jsp 
	@RequestMapping(value = "/addArticle", method = RequestMethod.GET)
	public String getAddArticleForm(@RequestParam("id") Integer boardId, @RequestParam("name") String name, Model model,
			HttpServletRequest request, HttpSession session) {
		System.out.println("****************************************進入Discussion Controller getAddArticleForm()");

		System.out.println("取得request.getSession()");

		List<SubjectType> subjectType = subjectTypeService.getAllSubjectType();// 透過service.getAllSubjectType方法取得所有發文分類
		System.out.println("subjectTypeService.getAllSubjectType()");

		model.addAttribute("subjectType", subjectType); // 將分類名稱物件集合，注入model中，識別字串為subjectType

		model.addAttribute("boardId", boardId);
		model.addAttribute("boardName", name);

		// subjectTypeService實作方法向DAO取得所有發文分類的值，將其設給SubjectType型態的物件組成的串列
		List<BoardType> list = boardTypeService.getAllBoardType();
		System.out.println("在getAddArticleForm()內，抓取看板列表");
		model.addAttribute(list);
		System.out.println("model.addAttribute(list)" + list);

		return "addArticle";
	}

	// 新增文章 -->重定向至所屬的討論看板 board.jsp
	@RequestMapping(value = "/addArticle", method = RequestMethod.POST)
	public String processAddArticle(@RequestParam("boardId") Integer boardId, @RequestParam("subject") String subject,
			@RequestParam("subjectTypeId") Integer subjectType, @RequestParam("body") String body,
			@RequestParam("author") String author, Model model, HttpServletRequest request, HttpSession session) {
		System.out.println("進入processAddArticle()方法");
		BoardType type = boardTypeService.getBoardTypeById(boardId);
		
		String articleBody = request.getParameter("body").replaceAll("\n", "<br>");//換行處理
		
		Member mem = (Member) session.getAttribute("mem");//從Session取得使用者會員
//		System.out.println("Member的username:"+ mem.getUsername());
		//以addArticle.jsp下拉選單subjectTypeId，被選取的${subjectType.subjectTypeId}值，呼叫DAO取得對應的SubjectType物件
		SubjectType Stype = subjectTypeService.getSubjectTypeById(subjectType);

		System.out.println("subjectType=" + subjectType);

		// ==============設定發表文章時間=======================
		Calendar rightNow = Calendar.getInstance();
		String createtime = rightNow.get(Calendar.YEAR) + "-" + (rightNow.get(Calendar.MONTH) + 1) + "-"
				+ rightNow.get(Calendar.DATE) + " " + rightNow.get(Calendar.HOUR) + ":" + rightNow.get(Calendar.MINUTE)
				+ ":" + rightNow.get(Calendar.SECOND);
		// ==============/設定發表文章時間=======================

		Discussion discussion = new Discussion();
		discussion.setArticleBody(articleBody); // 填入文章
		discussion.setMember(mem); // 填入發文者，引數為Member型態的物件mem
		discussion.setSubject(subject); // 填入標題
		discussion.setSubjectType(Stype);// 填入發文分類
		discussion.setBoardType(type); // 填入看版
		discussion.setViews(0); // 填入人氣(計數器)，初始值為0
		discussion.setIsDeleted(0); // 填入是否軟刪除，初始值為0，未被刪除
		discussion.setPostTimeStamp(createtime);// 填入時間戳

		discussionService.addArticle(discussion);
		return "redirect:/board-Rich?id=" + boardId;   // 重定向至看板，留意key值的用法
	}

	
	// 新增回覆 -->重定向至所屬的討論文章 article.jsp
	@RequestMapping(value = "/addReply", method = RequestMethod.POST)
	public String processAddReply(@RequestParam("articleId") Integer articleId,
			@RequestParam("body") String body,
			@RequestParam("author") String author, Model model, HttpServletRequest request, HttpSession session) throws ParseException {
		System.out.println("進入新增回覆 processAddReply()方法，articleId:\"+articleId");
		Reply reply = new Reply();
		String replyBody = request.getParameter("body").replaceAll("\n", "<br>");//換行處理
		Member mem = (Member) session.getAttribute("mem");//從Session取得使用者會員
//		System.out.println("Member的username:"+ mem.getUsername());

//		// ==============設定發表文章時間=======================
//		Calendar rightNow = Calendar.getInstance();
//		String postTimeStamp = rightNow.get(Calendar.YEAR) + "-" + (rightNow.get(Calendar.MONTH) + 1) + "-"
//				+ rightNow.get(Calendar.DATE) + " " + rightNow.get(Calendar.HOUR) + ":" + rightNow.get(Calendar.MINUTE)
//				+ ":" + rightNow.get(Calendar.SECOND);
		
		
		SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		  String postTimeStamp = myFmt2.format(new Date ());
		
//		// ==============/設定發表文章時間=======================
	
		
		// ===============將值填入reply 屬性物件之內=====================
		Discussion discussion = discussionService.getArticleById(articleId);//以articleId取得一筆文章物件
		reply.setDiscussion(discussion);//將文章物件填入reply屬性。填入文章編號，引數為Discussion型態的物件discussion
		reply.setReplyBody(replyBody); // 填入回覆文
		reply.setMember(mem); // 填入發文者，引數為Member型態的物件mem
		reply.setPostTimeStamp(postTimeStamp);// 填入時間戳
		
//		System.out.println("reply.getMember():"+reply.getMember()); 
//		System.out.println("articleId=" + discussion);
		
		//===============呼叫Reply Service，將裝好資料的屬性物件，交給DAO以Hibernate塞進資料庫
		replyService.addReply(reply);
		return "redirect:/article?id=" + articleId; // 重定向至所屬文章，留意key值的用法
	}
		
	// 後台:進入指定看板，顯示指定看板的文章列表 --> board-RichBack.jsp
	@RequestMapping("/board-RichBack")
	public String getArticleByBoardTypeIdRichBack(@RequestParam("id") Integer boardId, Model model) {

		// 1.取得指定看版屬性，並更新瀏覽人次，更新後再重取一次
		BoardType boardType = boardTypeService.getBoardTypeById(boardId);				
		discussionService.updateBoardViews(boardId); 
		BoardType boardType2 = boardTypeService.getBoardTypeById(boardId);// 透過service.getBoardTypeById方法取得一個指定看版的看板名稱
		
		// 2.取得指定看版的所有文章
		List<Discussion> discussionlist = discussionService.getArticleByBoardTypeIdBack(boardId);// 透過service.getArticleByBoardTypeId方法取得所有指定看板上的文章
		
		// 3.將屬性放入SpringMVC提供的model
		model.addAttribute("DiscussionList", discussionlist);// 將指定看板上的所有文章物件，都注入model中，識別字串為DiscussionList
		model.addAttribute("boardType", boardType2); // 將指定看板的名稱物件，注入model中，識別字串為boardType

		return "board-RichBack";
	}
	
	// 刪除看板 -->重定向至討論區後台首頁 discussionBack.jsp
	@RequestMapping(value = "/physicalDeleteBoardById", method = RequestMethod.GET)
	public String physicalDeleteBoardById(@RequestParam("id") Integer boardId) {		
		boardTypeService.physicalDeleteBoardById(boardId);
		return "redirect:/discussionBack";
	}
	
	
	// 實體刪除文章 -->重定向至所屬的看板 board-RichBack.jsp
	@RequestMapping(value = "/physicalDeleteArticle", method = RequestMethod.GET)
	public String physicalDeleteArticleById(@RequestParam("id") Integer articleId) {
		Discussion d = discussionService.getArticleById(articleId);	//由articleId取得指定的文章資料列物件
		Integer boardId = d.getBoardType().getBoardId();//為了回看板，取得看板id
		
		discussionService.physicalDeleteArticleById(articleId);//執行真正的刪除
		
		return "redirect:/board-RichBack?id=" + boardId;
	}
		
	// 軟刪除文章 -->重定向至所屬的看板 board-RichBack.jsp
	@RequestMapping(value = "/deleteArticle", method = RequestMethod.GET)
	public String deleteArticleById(@RequestParam("id") Integer articleId) {
		Discussion d = discussionService.getArticleById(articleId); //由articleId取得指定的文章資料列物件
		Integer boardId = d.getBoardType().getBoardId();//為了回看板，取得看板id
		discussionService.deleteArticleById(articleId);//執行軟刪除
		List<Discussion> discussion = discussionService.getArticleByBoardTypeIdBack(boardId);//刪除後，再取一次原看板的所有文章
		return "redirect:/board-RichBack?id=" + boardId;
	}
	
	// 恢復文章 -->重定向至所屬的看板 board-RichBack.jsp
	@RequestMapping(value = "/recoverArticleById", method = RequestMethod.GET)
	public String recoverArticleById(@RequestParam("id") Integer articleId) {
		Discussion d = discussionService.getArticleById(articleId); //由articleId取得指定的文章資料列物件
		Integer boardId = d.getBoardType().getBoardId();//為了回看板，取得看板id
		discussionService.recoverArticleById(articleId);;//執行恢復
		List<Discussion> discussion = discussionService.getArticleByBoardTypeIdBack(boardId);//恢復後，再取一次原看板的所有文章
		return "redirect:/board-RichBack?id=" + boardId;
	}
	
}
