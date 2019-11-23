package com.eeit109team6.finalproject.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.eeit109team6.finalproject.model.BoardType;
import com.eeit109team6.finalproject.model.Discussion;
import com.eeit109team6.finalproject.model.SubjectType;
import com.eeit109team6.finalproject.service.IBoardTypeService;
import com.eeit109team6.finalproject.service.IDiscussionService;
import com.eeit109team6.finalproject.service.ISubjectTypeService;

@Controller
public class BoardTypeController {

	public BoardTypeController() {
	}

	IBoardTypeService boardTypeservice;
	IDiscussionService discussionService;
	ISubjectTypeService subjectTypeService;
	ServletContext context;

	@Autowired
	public void setContext(ServletContext context) {
		this.context = context;
	}

	@Autowired
	public void setService(IBoardTypeService service) {
		this.boardTypeservice = service;
	}

	// 討論區後台，顯示討論區看板列表 --> discussionBack.jsp
	@RequestMapping("/discussionBack")
	public String getAllBoardType(Model model) {
		BoardType boardType = new BoardType();
		boardType.setBoardName("");//開發時在此填入aaa看效果
		model.addAttribute("boardType", boardType);//將boardType物件裝入model，model內的識別字串為boardType
		List<BoardType> list = boardTypeservice.getAllBoardType();
		System.out.println("進入討論區後台   getAllBoardType()");	
		
		model.addAttribute("Blist",list);
		return "discussionBack";
	}

	// 提供新增討論看板時的表單 --> addBoard.jsp
//	@RequestMapping(value = "addBoard", method = RequestMethod.GET)
//	public String getAddBoardTypeForm(Model model) {
//		BoardType boardType = new BoardType();
//		model.addAttribute("boardType", boardType);//將boardType物件裝入model，model內的識別字串為boardType
//		return "addBoard";
//	}

	// 新增討論看板 -->重定向至討論區後台 discussionBack.jsp
	@RequestMapping(value = "addBoard", method = RequestMethod.POST)
	public String processAddBoardType(
			@ModelAttribute("boardType") BoardType boardType    //特別指定引入Model中的其中一個鍵值
//			Model model											//引入整個model
//			@RequestParam("BoardImage") MultipartFile boardImage,//非form:form表單傳遞參數方法
	) {
		System.out.println("進入processAddBoardType()方法");
//		BoardType boardType = new BoardType();			//new一個BoardType實體指給boardType變數
		boardType.setBoardViews(0);
		MultipartFile boardImage = boardType.getbImage(); //boardType物件
		String originalFilename = boardImage.getOriginalFilename();
		// 建立Blob物件，交由Hibernate寫入資料庫
		if (boardImage != null && !boardImage.isEmpty()) {
			try {
				byte[] b = boardImage.getBytes();
				Blob blob = new SerialBlob(b);
				boardType.setBoardImage(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常:" + e.getMessage());
			}
		}

		boardTypeservice.addBoardType(boardType);
		return "redirect:/discussionBack";
	}

	// 讀取圖片
	@RequestMapping(value = "/getBoardImage/{boardId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer boardId) {
		String filePath = "resources/images/NoImage.jpg";
		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		int len = 0;
		BoardType boardType = boardTypeservice.getBoardTypeById(boardId);
		if (boardType != null) {
			Blob blob = boardType.getBoardImage();
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
	
	// bug
	// 提供新增發文分類時的表單 --> addBoard.jsp
	@RequestMapping(value = "addSubjectType", method = RequestMethod.GET)
	public String getAddSubjectTypeForm(Model model) {
		SubjectType subjectType = new SubjectType();
		model.addAttribute("subjectType", subjectType);//將boardType物件裝入model，model內的識別字串為boardType
		return "addSubjectType";
	}
	
	// bug
	// 新增發文分類 -->重定向至討論區後台 discussionBack.jsp
	@RequestMapping(value = "addSubjectType", method = RequestMethod.POST)
	public String processAddSubjectType(
			@ModelAttribute("subjectType") SubjectType subjectType    //特別指定引入Model中的其中一個鍵值
//			Model model											//引入整個model
//			@RequestParam("BoardImage") MultipartFile boardImage,//非form:form表單傳遞參數方法
	) {
		System.out.println("進入processAddSubjectType()方法");

		MultipartFile subjectImage = subjectType.getsImage(); 
		String originalFilename = subjectImage.getOriginalFilename();
		// 建立Blob物件，交由Hibernate寫入資料庫
		if (subjectImage != null && !subjectImage.isEmpty()) {
			try {
				byte[] b = subjectImage.getBytes();
				Blob blob = new SerialBlob(b);
				subjectType.setSubjectImage(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常:" + e.getMessage());
			}
		}

		subjectTypeService.addSubjectType(subjectType);
		return "redirect:/discussionBack";
	}
	
	
	
	
	

	// 提供修改討論看板時的表單 --updateBoard.jsp
//	@RequestMapping(value = "updateBoard", method = RequestMethod.GET)
//	public String getUpdateBoardTypeForm(@RequestParam("boardId") Integer boardId, Model model) {
//		System.out.println("進入getUpdateBoardTypeForm()方法");
//		BoardType boardtype = service.getBoardTypeById(boardId);
//		model.addAttribute("boardType", boardtype);
//		return "updateBoard";
//	}
//	
	// 修改討論看板--> 重定向至討論區後台 discussionBack.jsp
//	@RequestMapping(value = "updateBoard", method = RequestMethod.POST)
//	public String processUpdateBoardType(@ModelAttribute("boardType") BoardType boardType, @RequestParam("boardId") Integer boardId) {
//		
//		
//		boardType.updateBoard
//		return "redirect:/discussionBack";
//	}

}
