//package com.eeit109team6.finalproject.controller;
//
//import javax.servlet.ServletContext;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.eeit109team6.finalproject.model.BoardType;
//import com.eeit109team6.finalproject.model.SubjectType;
//import com.eeit109team6.finalproject.service.IBoardTypeService;
//import com.eeit109team6.finalproject.service.ISubjectTypeService;
//
//@Controller
//public class SubjectTypeController {
//
//	public SubjectTypeController() {
//	}
//	
//	IBoardTypeService boardTypeService;
//	ISubjectTypeService subjectTypeService;
//	ServletContext context;
//
//	@Autowired
//	public void setContext(ServletContext context) {
//		this.context = context;
//	}
//
//	@Autowired
//	public void setBoardTypeService(IBoardTypeService boardTypeService) {
//		this.boardTypeService = boardTypeService;
//	}
//	
//	@Autowired
//	public void setSubjectTypeService(ISubjectTypeService subjectTypeService) {
//		this.subjectTypeService = subjectTypeService;
//	}
//	
//	// 提供新增發文分類時的表單 --> addSubjectType.jsp
//	@RequestMapping(value = "addSubjectType", method = RequestMethod.GET)
//	public String getAddBoardTypeForm(Model model) {
//
//		SubjectType subjectType = new SubjectType();
//		model.addAttribute("subjectType", subjectType);
//		return "addSubjectType";
//	}
//	
//	// 新增發文分類 -->重定向至討論區後台 discussionBack.jsp
//	@RequestMapping(value = "addBoard", method = RequestMethod.POST)
//	public String processAddBoardType(@ModelAttribute("subjcetType") SubjectType st) {
//		System.out.println("進入processAddBoardType()方法");
//		subjectTypeService.addSubjectType(st);
//		return "redirect:/discussionBack";
//	}
//	
//}
//
//
//
//
