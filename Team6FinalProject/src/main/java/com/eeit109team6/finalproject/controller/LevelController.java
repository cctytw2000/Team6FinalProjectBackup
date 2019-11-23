package com.eeit109team6.finalproject.controller;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.MemberLevel;
import com.eeit109team6.finalproject.service.IMemberLevelService;
import com.eeit109team6.finalproject.service.IMemberService;

@Controller
public class LevelController {
	IMemberLevelService IMemberLevelService;
	IMemberService MemberService;
	ServletContext context;

	@Autowired
	public void setMemberService(IMemberService memberService) {
		MemberService = memberService;
	}

	@Autowired
	public void setIMemberLevelService(IMemberLevelService iMemberLevelService) {
		IMemberLevelService = iMemberLevelService;
	}

	@Autowired
	public void setContext(ServletContext context) {
		this.context = context;
	}

	@RequestMapping(value = "/memberLevel.json", method = RequestMethod.GET)
	public String memberLevel(Model model) {

		ArrayList<MemberLevel> level = IMemberLevelService.findAll();
		model.addAttribute("levelList", level);

		return "membersBack";

	}

	@RequestMapping(value = "/memberBack/change", method = RequestMethod.POST)
	public String memberLevelChange(@RequestParam("memberId") Integer id, @RequestParam("levelChange") Integer levelId,
			Model model) {

		System.out.println("id=" + id);
		System.out.println("level=" + levelId);
		MemberLevel level = IMemberLevelService.findById(levelId);

		MemberService.updateLevel(id, level);
//		
		return "redirect:/member?id=" + id;

	}

}
