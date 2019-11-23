package com.eeit109team6.finalproject.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eeit109team6.finalproject.javaUtils.AES_CBC_PKCS5PADDING;
import com.eeit109team6.finalproject.javaUtils.CipherUtils;
import com.eeit109team6.finalproject.javaUtils.Mail;
import com.eeit109team6.finalproject.model.LiLoInfo;
import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.MemberHeadShot;
import com.eeit109team6.finalproject.model.MemberLevel;
import com.eeit109team6.finalproject.model.MovieInfo;
import com.eeit109team6.finalproject.service.ILiLoInforService;
import com.eeit109team6.finalproject.service.IMemberHeadShotService;
import com.eeit109team6.finalproject.service.IMemberLevelService;
import com.eeit109team6.finalproject.service.IMemberService;
import com.eeit109team6.finalproject.service.IMovieService;

@Controller
public class MemberController {
	IMemberService MemService;
	ServletContext context;
	ILiLoInforService LiLoInforService;
	IMemberLevelService IMemberLevelService;
	IMemberHeadShotService MhsService;
	IMovieService movieservice;

	@Autowired
	public void setMovieservice(IMovieService movieservice) {
		this.movieservice = movieservice;
	}

	@Autowired
	public void setMhsService(IMemberHeadShotService mhsService) {
		MhsService = mhsService;
	}

	@Autowired
	public void setIMemberLevelService(IMemberLevelService iMemberLevelService) {
		IMemberLevelService = iMemberLevelService;
	}

	@Autowired
	public void setContext(ServletContext context) {
		this.context = context;
	}

	@Autowired
	public void setMemService(IMemberService memService) {
		MemService = memService;
	}

	@Autowired
	public void setLiLoInforService(ILiLoInforService liLoInforService) {
		LiLoInforService = liLoInforService;
	}

	@RequestMapping(value = "/member/addMovie", method = RequestMethod.POST)
	public String memberAddMovie(@RequestParam("movie_name") String movie_name,
			@RequestParam("movie_content") String movie_content, @RequestParam("video_file") MultipartFile video_file,
			HttpSession session) {

		Member mem = (Member) session.getAttribute("mem");

		MovieInfo movieInfo = new MovieInfo();

		// ==============設定創建時間=======================
		Calendar rightNow = Calendar.getInstance();
		String registeredtime = rightNow.get(Calendar.YEAR) + "-" + (rightNow.get(Calendar.MONTH) + 1) + "-"
				+ rightNow.get(Calendar.DATE) + " " + rightNow.get(Calendar.HOUR) + ":" + rightNow.get(Calendar.MINUTE)
				+ ":" + rightNow.get(Calendar.SECOND);
		// ==============/設定創建時間=======================

		movieInfo.setTime(registeredtime);
		movieInfo.setName(movie_name);
		movieInfo.setMovie_content(movie_content);
		movieInfo.setMember(mem);
		movieInfo.setLocation_Test(video_file.getOriginalFilename());
		movieInfo.setClick_Sum(0);
		movieInfo.setLike_Sum(0);

		String videoname = video_file.getOriginalFilename();

		System.out.println("@RequestMapping(value = \"/moviepersonal/addMovie\")" + movieInfo);

		Integer movieId = movieservice.addMovie(movieInfo);
		// 新版影片上傳
		Path p = Paths.get("C:/memberMovies"); // 路徑設定

		if (Files.exists(p)) {
			System.out.print("資料夾已存在");
		}
		if (!Files.exists(p)) {
			/* 不存在的話,直接建立資料夾 */
			try {
				Files.createDirectory(p);
				System.out.print("已成功建立資料夾");
			} catch (IOException e) {
				System.out.println("發生錯誤");
			}
		}

		p = Paths.get("C:/memberMovies/" + mem.getAccount() + mem.getMember_id()); // 路徑設定

		if (Files.exists(p)) {
			System.out.print("資料夾已存在");
		}
		if (!Files.exists(p)) {
			/* 不存在的話,直接建立資料夾 */
			try {
				Files.createDirectory(p);
				System.out.print("已成功建立資料夾");
			} catch (IOException e) {
				System.out.println("發生錯誤");
			}
		}

		String path = "C:/memberMovies/" + mem.getAccount() + mem.getMember_id();

		if (!video_file.isEmpty()) {
			try {
				byte[] bytes = video_file.getBytes();

				File dir = new File(path, movieId + video_file.getOriginalFilename());

				// File serverFile = new File(dir.getAbsolutePath() + File.separator +
				// videoname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(dir));
				stream.write(bytes);
				stream.close();

				return "redirect:/member/movies";
			} catch (Exception e) {
				return "redirect:/member/movies";
			}
		} else {
			return "redirect:/member/movies";
		}

	}

	@RequestMapping("/member/movies")
	public String memberMovies(Model model, HttpSession session) {
		Member mem = (Member) session.getAttribute("mem");

		ArrayList<MovieInfo> list = (ArrayList<MovieInfo>) movieservice.getMovieInfoByID(mem.getMember_id());
		// List<MovieInfo> list =
		// movieservice.getMovieInfoByOwnerID(mem.getMember_id());
		model.addAttribute("movies", list);
		return "memberMovieIndex";

	}

	@RequestMapping("/member/updateMoviesViews")
	public String updateMoviesViews(@RequestParam("movieId") Integer id, Model model, HttpSession session) {
		Member mem = (Member) session.getAttribute("mem");
		System.out.println("updateMoviesViews====" + id);
		movieservice.updateMovieViews(id);

		return "memberMovieIndex";

	}

	// ===========================會員一般註冊====================================
	@RequestMapping(value = "/member/register", method = RequestMethod.POST)
	public String registerMember(@RequestParam("account") String account, @RequestParam("password") String password,
			@RequestParam("username") String username, @RequestParam("memberimg") MultipartFile memberimg, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		// String[] suppressedFields = result.getSuppressedFields();

		Member mem = new Member();
		// if (suppressedFields.length > 0) {
		// throw new RuntimeException(
		// "嘗試輸入錯誤的欄位:" +
		// org.springframework.util.StringUtils.arrayToCommaDelimitedString(suppressedFields));
		// }

		// ==============設定創建帳號時間=======================
		Calendar rightNow = Calendar.getInstance();
		String registeredtime = rightNow.get(Calendar.YEAR) + "-" + (rightNow.get(Calendar.MONTH) + 1) + "-"
				+ rightNow.get(Calendar.DATE) + " " + rightNow.get(Calendar.HOUR) + ":" + rightNow.get(Calendar.MINUTE)
				+ ":" + rightNow.get(Calendar.SECOND);
		// ==============/設定創建帳號時間=======================

		// ==============密碼加密=======================
		String key = "MickeyKittyLouis";
		String password_AES = CipherUtils.encryptString(key, password).replaceAll("[\\pP\\p{Punct}]", "").replace(" ",
				"");
		// ==============/密碼加密=======================

		MemberLevel level = IMemberLevelService.findById(2);
		// System.out.println("level==" + level.getLevelName());

		// ==============設定token====================
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(256, new SecureRandom());
			SecretKey secretKey = keyGen.generateKey();
			byte[] iv = new byte[16];
			SecureRandom prng = new SecureRandom();
			prng.nextBytes(iv);
			Long math = Long.valueOf((long) (Math.random() * 999999999));
			String token_notformat = AES_CBC_PKCS5PADDING.Encrypt(secretKey, iv, math.toString());
			String token = token_notformat.replaceAll("[\\pP\\p{Punct}]", "").replace(" ", "");
			mem.setToken(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ==============/設定token====================
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd HHmmssSSS");
		String createtime = sf.format(new Date());

		mem.setAccount(account);
		mem.setUsername(username);
		mem.setPassword(password_AES);
		mem.setHeadshot(createtime + memberimg.getOriginalFilename());
		mem.setType("General");
		mem.setMemberlevel(level);
		mem.setRegisteredtime(registeredtime);
		mem.setIsactive(0);
		Integer memberId = MemService.add(mem);
		String email = null;
		String pwd = null;

		Member memberForHS = MemService.findById(memberId);

		MemberHeadShot mhs = new MemberHeadShot();
		mhs.setMember(memberForHS);
		mhs.setHeadshotname(createtime + memberimg.getOriginalFilename());

		MhsService.add(mhs);

		Path p = Paths.get("C:/memberImages"); // 路徑設定

		if (Files.exists(p)) {
			System.out.print("資料夾已存在");
		}
		if (!Files.exists(p)) {
			/* 不存在的話,直接建立資料夾 */
			try {
				Files.createDirectory(p);
				System.out.print("已成功建立資料夾");
			} catch (IOException e) {
				System.out.println("發生錯誤");
			}
		}

		File dir = new File("C:/memberImages/" + mem.getAccount() + "_" + memberId);

		dir.mkdir();

		try {
			BufferedReader bfr = new BufferedReader(new FileReader("C:\\sqldata\\sqldata.txt"));
			String data;
			while ((data = bfr.readLine()) != null) {
				email = data.split(",")[0];
				pwd = data.split(",")[1];
			}
			bfr.close();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		try {
			InputStream img = memberimg.getInputStream();
			File file = new File("C:\\memberImages\\" + mem.getAccount() + "_" + memberId,
					username + memberId + createtime + memberimg.getOriginalFilename());
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

		Mail mail = new Mail();
		mail.setEmail(email);
		mail.setPwd(pwd);
		Boolean isSuccess = mail.SendMessage(memberId, mem.getAccount(), mem.getToken());
		if (isSuccess) {
			System.out.println("寄送email結束.");
			redirectAttributes.addFlashAttribute("msg", "請至您輸入的信箱收取驗證信<br>並輸入完整資料開通帳號");
			return "redirect:/jump";

		} else {
			redirectAttributes.addFlashAttribute("msg", "有東西有問題喔");
			return "redirect:/jump";
		}
		// return "redirect:/jump";
	}

	// ===========================會員第三方登入====================================
	@RequestMapping(value = "/member/thirdPartyLogin", method = RequestMethod.POST)
	public @ResponseBody Boolean thirdPartyLogin(@RequestParam("account") String account,
			@RequestParam("type") String type, @RequestParam("username") String username, HttpSession session,
			HttpServletRequest request) {

		Member mem = new Member();

		String key = "MickeyKittyLouis";
		String password_AES = CipherUtils.encryptString(key, account).replaceAll("[\\pP\\p{Punct}]", "").replace(" ",
				"");

		mem.setAccount(account);
		mem.setPassword(password_AES);
		mem.setType(type);
		Member member = MemService.login(mem);

		LiLoInfo lilo = new LiLoInfo();
		// ==============設定創建帳號時間=======================
		Calendar rightNow = Calendar.getInstance();
		String logintime = rightNow.get(Calendar.YEAR) + "-" + (rightNow.get(Calendar.MONTH) + 1) + "-"
				+ rightNow.get(Calendar.DATE) + " " + rightNow.get(Calendar.HOUR) + ":" + rightNow.get(Calendar.MINUTE)
				+ ":" + rightNow.get(Calendar.SECOND);
		// ==============/設定創建帳號時間=======================

		lilo.setMember(member);
		lilo.setLoginTime(logintime);
		lilo.setType("Login");
		lilo.setClientIP(request.getRemoteAddr());
		lilo.setAccountType(type);
		// System.out.println("member.getMemberlevel().getLevelName()"+member.getMemberlevel().getLevelName());
		if (member != null) {
			System.out.println("第三方登入");
			lilo.setIsSuccess(1);
			LiLoInforService.add(lilo);
			// session.setAttribute("username", member.getUsername());
			// session.setAttribute("token", member.getToken());
			// session.setAttribute("account", member.getAccount());
			// session.setAttribute("member_id", member.getMember_id());
			session.setAttribute("mem", member);
			// session.setAttribute("type", member.getType());
			// session.setAttribute("level", member.getMemberlevel().getLevelName());
			return true;
		} else {

			return false;

		}

	}

	// ===========================會員第三方註冊====================================
	@RequestMapping(value = "/member/thirdPartyRegister")
	public @ResponseBody Integer registerFacebookOrGoogleMember(@RequestParam("account") String account,
			@RequestParam("type") String type, @RequestParam("username") String username) {

		Member mem = new Member();
		// ==============設定創建帳號時間=======================
		Calendar rightNow = Calendar.getInstance();
		String registeredtime = rightNow.get(Calendar.YEAR) + "-" + (rightNow.get(Calendar.MONTH) + 1) + "-"
				+ rightNow.get(Calendar.DATE) + " " + rightNow.get(Calendar.HOUR) + ":" + rightNow.get(Calendar.MINUTE)
				+ ":" + rightNow.get(Calendar.SECOND);
		// ==============/設定創建帳號時間=======================

		// ==============密碼加密=======================

		String key = "MickeyKittyLouis";
		String password_AES = CipherUtils.encryptString(key, account).replaceAll("[\\pP\\p{Punct}]", "").replace(" ",
				"");
		// ==============/密碼加密=======================

		// ==============設定token====================
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(256, new SecureRandom());
			SecretKey secretKey = keyGen.generateKey();
			byte[] iv = new byte[16];
			SecureRandom prng = new SecureRandom();
			prng.nextBytes(iv);
			Long math = Long.valueOf((long) (Math.random() * 999999999));
			String token_notformat = AES_CBC_PKCS5PADDING.Encrypt(secretKey, iv, math.toString());
			String token = token_notformat.replaceAll("[\\pP\\p{Punct}]", "").replace(" ", "");
			mem.setToken(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ==============/設定token====================

		MemberLevel level = IMemberLevelService.findById(2);
		mem.setAccount(account);
		mem.setPassword(password_AES);
		mem.setPassword(password_AES);
		mem.setUsername(username);
		mem.setType(type);
		mem.setMemberlevel(level);
		mem.setRegisteredtime(registeredtime);
		mem.setIsactive(0);

		int memberId = MemService.add(mem);
		File dir = new File("C:/memberImages/" + mem.getAccount() + "_" + memberId);

		dir.mkdir();
		return memberId;

	}

	// ===========================會員第三方帳號判斷重複====================================
	@RequestMapping(value = "/member/checkRepeat")
	public @ResponseBody Boolean checkRepeatFacebookOrGoogleMember(@RequestParam("account") String account,
			@RequestParam("type") String type) {
		System.out.println("/member/checkRepeat");
		System.out.println("account=" + account);
		System.out.println("type=" + type);
		Member mem = new Member();
		mem.setAccount(account);
		mem.setType(type);
		boolean repeatAnswer = MemService.checkAccount(mem);
		System.out.println("repeatAnswer" + repeatAnswer);
		return repeatAnswer;
	}

	// ===========================會員一般帳號判斷重複====================================
	@RequestMapping(value = "/member/checkGeneralRepeat")
	public @ResponseBody Boolean checkGeneralRepeat(@RequestParam("account") String account,
			@RequestParam("type") String type) {
		System.out.println("/member/checkRepeat");
		System.out.println("account=" + account);
		System.out.println("type=" + type);
		Member mem = new Member();
		mem.setAccount(account);
		mem.setType(type);
		boolean repeatAnswer = MemService.checkAccount(mem);
		System.out.println("repeatAnswer" + repeatAnswer);
		return repeatAnswer;
	}

	// ===========================會員後台開起帳號====================================
	@RequestMapping(value = "/member/changeActive", method = RequestMethod.POST)
	public @ResponseBody Boolean changeActive(@RequestParam("id") Integer id, @RequestParam("type") String type,
			@RequestParam("action") String action) {
		System.out.println("id=" + id);
		System.out.println("type=" + type);
		System.out.println("action=" + action);
		if ("close".equals(action)) {
			MemService.closeActive(id);
			return true;
		} else {
			MemService.openActive(id);
			return true;
		}

	}

	// ===========================跳轉業面===================================
	@RequestMapping(value = "/jump")
	public String jumpWeb(Model model) {

		return "jump";
	}

	@InitBinder
	public void whiteListion(WebDataBinder binder) {
		binder.setAllowedFields("account", "password", "username");
	}

	// ===========================會員一般登入===================================
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String memberLogin(@RequestParam("login") String login, @RequestParam("loginpassword") String password,
			@RequestParam("loginaccount") String account, Model model, RedirectAttributes redirectAttributes,
			HttpSession session, HttpServletRequest request) {
		System.out.println("他點的是" + login);

		Member mem = new Member();
		mem.setAccount(account);
		mem.setPassword(password);
		if ("登入".equals(login)) {

			String key = "MickeyKittyLouis";
			String password_AES = CipherUtils.encryptString(key, mem.getPassword()).replaceAll("[\\pP\\p{Punct}]", "")
					.replace(" ", "");

			mem.setPassword(password_AES);
			mem.setType("General");

			Member member = MemService.login(mem);

			LiLoInfo lilo = new LiLoInfo();
			// System.out.println("LiLo============================" +
			// member.getLiLoInfo().size());
			// ==============設定登入帳號時間=======================
			Calendar rightNow = Calendar.getInstance();
			String logintime = rightNow.get(Calendar.YEAR) + "-" + (rightNow.get(Calendar.MONTH) + 1) + "-"
					+ rightNow.get(Calendar.DATE) + " " + rightNow.get(Calendar.HOUR) + ":"
					+ rightNow.get(Calendar.MINUTE) + ":" + rightNow.get(Calendar.SECOND);
			// ==============/設定登入帳號時間=======================

			lilo.setMember(member);
			lilo.setLoginTime(logintime);
			lilo.setType("Login");
			lilo.setClientIP(request.getRemoteAddr());
			lilo.setAccountType("General");
			lilo.setIsSuccess(1);

			if (member != null) {
				LiLoInforService.add(lilo);
				// session.setAttribute("username", member.getUsername());
				// session.setAttribute("token", member.getToken());
				// session.setAttribute("account", member.getAccount());
				// session.setAttribute("member_id", member.getMember_id());
				session.setAttribute("mem", member);
				// session.setAttribute("type", member.getType());

				// session.setAttribute("level", member.getMemberlevel().getLevelName());
				redirectAttributes.addFlashAttribute("msg", "歡迎光臨Gamily");
				return "redirect:/jump";
			} else {
				member = MemService.checkAccount(mem.getAccount(), "General");

				lilo.setIsSuccess(0);
				lilo.setMember(member);
				LiLoInforService.add(lilo);
				redirectAttributes.addFlashAttribute("msg", "帳號密碼錯誤<br>請確認後再登入");
				return "redirect:/jump";

			}
		} else {
			model.addAttribute("msg", "忘記密碼");
			return "forgetPassWord";

		}

	}

	// ===========================會員修改密碼導向寄郵件===================================
	@RequestMapping(value = "/member/sendChangePassWordMail", method = RequestMethod.GET)
	public String sendChangePassWordMail(Model model) {
		model.addAttribute("msg", "修改密碼");

		return "forgetPassWord";

	}

	// ===========================會員修改密碼寄郵件===================================
	@RequestMapping(value = "/member/sendChangePassWordPage", method = RequestMethod.POST)
	public String sendChangePassWord(@RequestParam("account") String account, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {
		// System.out.println("account=" + account);
		String changeType, type, msh_;
		if (session.getAttribute("mem") == null) {
			changeType = "忘記密碼";
			type = "forget";
			msh_ = "Please click URL to change Password";
		} else {
			changeType = "修改密碼";
			type = "change";
			msh_ = "Please click URL to change Password";
		}
		System.out.println("changeType=" + changeType);
		System.out.println("type=" + type);
		Member mem = new Member();
		mem.setAccount(account);
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(256, new SecureRandom());
			SecretKey secretKey = keyGen.generateKey();
			byte[] iv = new byte[16];
			SecureRandom prng = new SecureRandom();
			prng.nextBytes(iv);
			Long math = Long.valueOf((long) (Math.random() * 999999999));
			String token_notformat = AES_CBC_PKCS5PADDING.Encrypt(secretKey, iv, math.toString());
			String token = token_notformat.replaceAll("[\\pP\\p{Punct}]", "").replace(" ", "");

			mem.setToken(token);
			mem.setType("General");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean forget = MemService.forgetPwd(mem);

		if (forget) {
			String email = null;
			String pwd = null;

			BufferedReader bfr;
			try {
				bfr = new BufferedReader(new FileReader("C:\\sqldata\\sqldata.txt"));
				String data;

				while ((data = bfr.readLine()) != null) {
					System.out.println(data);
					email = data.split(",")[0];
					pwd = data.split(",")[1];
				}

				bfr.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String host = "smtp.gmail.com";
			int port = 587;
			final String Email = email;// your Gmail
			final String EmailPwd = pwd;// your password

			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", port);
			Session session_ = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Email, EmailPwd);
				}
			});

			try {

				Message message = new MimeMessage(session_);
				message.setFrom(new InternetAddress(Email));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mem.getAccount()));

				String url = "http://localhost:8080/Team6FinalProject/member/InsertNewPassowrd?account="
						+ mem.getAccount() + "&token=" + mem.getToken() + "&type=" + type;

				message.setSubject(changeType);
				// message.setText();
				// changeType

				// <a href="https://www.w3schools.com">Visit W3Schools.com!</a>
				message.setContent("<h2>please click this url to change your password</h2>\n" + "<a href='" + url + "'>"
						+ msh_ + "</a>", "text/html");
				Transport transport = session_.getTransport("smtp");
				transport.connect(host, port, Email, EmailPwd);

				Transport.send(message);

				System.out.println("寄送email結束.");
				redirectAttributes.addFlashAttribute("msg", "請至您的電子郵件<br>點擊連結修改密碼");

				return "redirect:/jump";

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} else {
			redirectAttributes.addFlashAttribute("msg", "沒有此帳號，或者您註冊的帳號為FB、Google帳號");
			session.removeAttribute("mem");
			return "redirect:/jump";
		}
		// return "redirect:/jump";
	}

	// ===========================會員登出===================================
	@RequestMapping(value = "/member/logout")
	public String memberLogout(@ModelAttribute("Member") Member mem, Model model, BindingResult result,
			RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
		Member member_ = new Member();
		Member memSession = (Member) session.getAttribute("mem");
		member_.setMember_id(memSession.getMember_id());
		Member member = MemService.findById(member_);

		LiLoInfo lilo = new LiLoInfo();
		// System.out.println("LiLo============================" +
		// member.getLiLoInfo().size());
		// ==============設定登出帳號時間=======================
		Calendar rightNow = Calendar.getInstance();
		String logouttime = rightNow.get(Calendar.YEAR) + "-" + (rightNow.get(Calendar.MONTH) + 1) + "-"
				+ rightNow.get(Calendar.DATE) + " " + rightNow.get(Calendar.HOUR) + ":" + rightNow.get(Calendar.MINUTE)
				+ ":" + rightNow.get(Calendar.SECOND);
		// ==============/設定登出帳號時間=======================

		lilo.setMember(member);
		lilo.setLoginTime(logouttime);
		lilo.setType("Logout");
		lilo.setClientIP(request.getRemoteAddr());
		lilo.setAccountType(memSession.getType());
		lilo.setIsSuccess(1);

		LiLoInforService.add(lilo);

		// session.removeAttribute("username");
		// session.removeAttribute("token");
		// session.removeAttribute("account");
		// session.removeAttribute("member_id");
		session.removeAttribute("mem");
		// session.removeAttribute("type");
		redirectAttributes.addFlashAttribute("msg", "謝謝光臨Gamily");
		return "redirect:/jump";

	}

	// ===========================導向修改業面===================================
	@RequestMapping(value = "/member/InsertNewPassowrd", method = RequestMethod.GET)
	public String insertNewPassWrod(@RequestParam("account") String account, @RequestParam("type") String type,
			@RequestParam("token") String token, Model model, RedirectAttributes redirectAttributes) {

		System.out.println("account:" + account);
		System.out.println("token:" + token);
		System.out.println("type:" + type);

		model.addAttribute("token", token);
		model.addAttribute("account", account);
		model.addAttribute("type", type);
		return "InsertNewPassowrd";

	}

	// ===========================修改密碼===================================
	@RequestMapping(value = "/member/ChangeNewPassowrd", method = RequestMethod.POST)
	public String ChangeNewPassowrd(@RequestParam("account") String account, @RequestParam("token") String token,
			@RequestParam("newPassWord") String newPassWord, @RequestParam("oldpassword") String oldPassWord,
			Model model, RedirectAttributes redirectAttributes, HttpSession session) {
		if ("Null".equals(oldPassWord)) {
			Member mem = new Member();
			// System.out.println("account:" + account);
			// System.out.println("token:" + token);
			// System.out.println("newPassWord:" + newPassWord);

			// ==============密碼加密=======================

			String key = "MickeyKittyLouis";
			String password_AES = CipherUtils.encryptString(key, newPassWord).replaceAll("[\\pP\\p{Punct}]", "")
					.replace(" ", "");
			// ==============/密碼加密=======================

			mem.setAccount(account);
			mem.setToken(token);
			mem.setType("General");
			mem.setPassword(password_AES);
			Boolean isSuccess = MemService.changePwd(mem);
			if (isSuccess) {
				// session.removeAttribute("username");
				// session.removeAttribute("token");
				// session.removeAttribute("account");
				// session.removeAttribute("member_id");
				session.removeAttribute("mem");
				// session.removeAttribute("type");
				redirectAttributes.addFlashAttribute("msg", "修改成功\n請依照新密碼登入");
				return "redirect:/jump";
			} else {
				// session.removeAttribute("username");
				// session.removeAttribute("token");
				// session.removeAttribute("account");
				// session.removeAttribute("member_id");
				session.removeAttribute("mem");
				// session.removeAttribute("type");

				redirectAttributes.addFlashAttribute("msg", "資訊錯誤請重新輸入");
				return "redirect:/jump";
			}
		} else {
			Member mem = new Member();
			System.out.println("account:" + account);
			System.out.println("token:" + token);
			System.out.println("oldPassWord:" + oldPassWord);
			System.out.println("newPassWord:" + newPassWord);

			String key = "MickeyKittyLouis";
			String password_AES = CipherUtils.encryptString(key, oldPassWord).replaceAll("[\\pP\\p{Punct}]", "")
					.replace(" ", "");
			// ==============/舊密碼加密=======================

			// ==============新密碼密碼加密=======================

			String new_password_AES = CipherUtils.encryptString(key, newPassWord).replaceAll("[\\pP\\p{Punct}]", "")
					.replace(" ", "");
			// ==============/密碼加密=======================

			mem.setAccount(account);
			mem.setToken(token);
			mem.setType("General");
			mem.setPassword(password_AES);
			Boolean isSuccess = MemService.changePwd(mem, new_password_AES);
			if (isSuccess) {
				// session.removeAttribute("username");
				// session.removeAttribute("token");
				// session.removeAttribute("account");
				// session.removeAttribute("member_id");
				session.removeAttribute("mem");
				// session.removeAttribute("type");
				redirectAttributes.addFlashAttribute("msg", "修改成功\n請依照新密碼登入");
				return "redirect:/jump";
			} else {
				// session.removeAttribute("username");
				// session.removeAttribute("token");
				// session.removeAttribute("account");
				// session.removeAttribute("member_id");
				session.removeAttribute("mem");
				// session.removeAttribute("type");

				redirectAttributes.addFlashAttribute("msg", "資訊錯誤請重新輸入");
				return "redirect:/jump";
			}

		}

	}

	// ===========================導向會員後台===================================
	@RequestMapping(value = "/membersBack")
	public String memberBack(Model model, HttpSession session, HttpServletRequest request) {
		System.out.println("/membersBack");
		ArrayList<Member> member = MemService.findAll();
		model.addAttribute("Memners", member);

		return "membersBack";

	}

	// ===========================會員全部登入資訊(10天間)JSON檔===================================
	@RequestMapping(value = "/memberLoginCount.json")
	public String memberLoginCount(Model model, HttpSession session, HttpServletRequest request) {

		Calendar calendar2 = Calendar.getInstance();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		String now = sdf2.format(new Date());
		calendar2.add(Calendar.DATE, -10);
		String three_days_after = sdf2.format(calendar2.getTime());

		System.out.println(now);
		System.out.println(three_days_after);

		System.out.println("memberLoginCount");
		ArrayList loginData = new ArrayList();
		Map data = LiLoInforService.countLogin(now, three_days_after);
		System.out.println("data" + data);
		loginData.add(data);
		model.addAttribute("memberLoginCount", loginData);
		return "membersBack";

	}

	// ===========================會員資訊JSON檔===================================
	@RequestMapping(value = "/member")
	public String memberBack(@RequestParam("id") Integer id, Model model, HttpSession session,
			HttpServletRequest request) {
		System.out.println("id" + id);
		Member m = new Member();
		m.setMember_id(id);
		Member member = MemService.findById(m);
		model.addAttribute("member", member);
		return "memberBack";

	}

	// ===========================會員個人登入資訊JSON檔===================================
	@RequestMapping(value = "/member/{id}.json")
	public String loginImfo(@PathVariable("id") Integer memberId, Model model, HttpSession session,
			HttpServletRequest request) {

		ArrayList<LiLoInfo> infoList = LiLoInforService.findById(memberId);
		model.addAttribute("infoList", infoList);
		return "memberBack";

	}

	// ===========================會員修改大頭貼===================================
	@RequestMapping(value = "/member/Changeheadshot", method = RequestMethod.POST)
	public String loginImfo(@RequestParam("memberimg") MultipartFile memberimg,
			@RequestParam("memberId") Integer memberId, RedirectAttributes redirectAttributes, HttpSession session) {
		Member m = new Member();
		m.setMember_id(memberId);
		Member member = MemService.findById(m);

		Path p_ = Paths.get("C:/memberImages/"); // 路徑設定

		if (Files.exists(p_)) {
			System.out.println("資料夾已存在");
		}
		if (!Files.exists(p_)) {
			/* 不存在的話,直接建立資料夾 */
			try {
				Files.createDirectory(p_);
				System.out.println("已成功建立資料夾");
			} catch (IOException e) {
				System.out.println("發生錯誤");
			}
		}

		Path p = Paths.get("C:/memberImages/" + member.getAccount() + "_" + member.getMember_id()); // 路徑設定

		if (Files.exists(p)) {
			System.out.println("資料夾已存在");
		}
		if (!Files.exists(p)) {
			/* 不存在的話,直接建立資料夾 */
			try {
				Files.createDirectory(p);
				System.out.println("已成功建立資料夾");
			} catch (IOException e) {
				System.out.println("發生錯誤");
			}
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd HHmmssSSS");
		String createtime = sf.format(new Date());

		System.out.println("createtime=" + createtime);

		try {
			InputStream img = memberimg.getInputStream();
			File file = new File("C:\\memberImages\\" + member.getAccount() + "_" + member.getMember_id(),
					member.getUsername() + memberId + createtime + memberimg.getOriginalFilename());
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
		System.out.println("File name  = " + memberimg.getOriginalFilename());
		MemService.changeHeadshot(createtime + memberimg.getOriginalFilename(), memberId);
		Member memberForSession = MemService.findById(m);
		// session.removeAttribute("username");
		// session.removeAttribute("token");
		// session.removeAttribute("account");
		// session.removeAttribute("member_id");
		session.setAttribute("mem", memberForSession);
		// session.removeAttribute("type");
		// redirectAttributes.addFlashAttribute("msg", "大頭貼已更新");
		return "redirect:/";

	}

}