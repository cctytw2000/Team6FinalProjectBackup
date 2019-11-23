package com.eeit109team6.finalproject.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.MemberHeadShot;
import com.eeit109team6.finalproject.service.IMemberHeadShotService;
import com.eeit109team6.finalproject.service.IMemberService;

@Controller
public class MemberHeadShotController {
	IMemberService MemService;

	IMemberHeadShotService MhsService;

	@Autowired
	public void setMhsService(IMemberHeadShotService mhsService) {
		MhsService = mhsService;
	}

	@Autowired
	public void setMemService(IMemberService memService) {
		MemService = memService;
	}

	// 增加照片
	@RequestMapping(value = "/member/addHeadShot", method = RequestMethod.POST)
	public String addHeadShot(@RequestParam("headshotImg") ArrayList<MultipartFile> headshotImg, HttpSession session) {

		Member mem = (Member) session.getAttribute("mem");

//		Path p = Paths.get("C:/memberImages"); // 路徑設定
//
//		if (Files.exists(p)) {
//			System.out.print("資料夾已存在");
//		}
//		if (!Files.exists(p)) {
//			/* 不存在的話,直接建立資料夾 */
//			try {
//				Files.createDirectory(p);
//				System.out.print("已成功建立資料夾");
//			} catch (IOException e) {
//				System.out.println("發生錯誤");
//			}
//		}

		Path dir = Paths.get("C:/memberImages/" + mem.getAccount() + "_" + mem.getMember_id());

		if (Files.exists(dir)) {
			System.out.print("資料夾已存在");
		}
		if (!Files.exists(dir)) {
			/* 不存在的話,直接建立資料夾 */
			try {
				Files.createDirectory(dir);
				System.out.print("已成功建立資料夾");
			} catch (IOException e) {
				System.out.println("發生錯誤");
			}
		}

		for (int i = 0; i < headshotImg.size(); i++) {

			MemberHeadShot mhs = new MemberHeadShot();
			mhs.setMember(mem);
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String createtime = sf.format(new Date());

			mhs.setHeadshotname(createtime + headshotImg.get(i).getOriginalFilename());

			MhsService.add(mhs);

			try {
				InputStream img = headshotImg.get(i).getInputStream();
				File file = new File("C:\\memberImages\\" + mem.getAccount() + "_" + mem.getMember_id(),
						mem.getUsername() + mem.getMember_id() + createtime + headshotImg.get(i).getOriginalFilename());
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buff = new byte[1024];
				int len;

				while ((len = img.read(buff)) != -1) {
					fos.write(buff, 0, len);
				}

				fos.close();
				img.close();

			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return "redirect:/member/PhotoList";
	}

	// 照片
	@RequestMapping(value = "/member/PhotoList", method = RequestMethod.GET)
	public String HeadShotList(HttpSession session, Model model) {
		Member mem = (Member) session.getAttribute("mem");
		ArrayList<MemberHeadShot> mhs = MhsService.findByMemberId(mem.getMember_id());
//		System.out.println("mhs=" + mhs.get(0).getHeadshotname());
		model.addAttribute("memberheadshots", mhs);
		return "HeadShotList";
	}

	// 更改大頭貼
	@RequestMapping(value = "/member/changeHeadShot", method = RequestMethod.POST)
	public String changeHeadShot(HttpSession session, Model model, @RequestParam("id") Integer id) {
		System.out.println("id=====" + id);
		MemberHeadShot mhs = MhsService.findById(id);
		Member mem = (Member) session.getAttribute("mem");
		MemService.changeHeadshot(mhs.getHeadshotname(), mem.getMember_id());
		Member member = MemService.findById(mem);
		session.setAttribute("mem", member);
		return "redirect:/member/PhotoList";
	}

	// 刪除大頭貼
	@RequestMapping(value = "/member/deleteImg", method = RequestMethod.POST)
	public String deleteImg(HttpSession session, Model model, @RequestParam("imgId") Integer id) {

		MhsService.delete(id);
		return "redirect:/member/PhotoList";
	}

}
