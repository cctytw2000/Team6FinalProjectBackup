package com.eeit109team6.finalproject.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.eeit109team6.finalproject.model.Activity;
import com.eeit109team6.finalproject.model.ActivityType;
import com.eeit109team6.finalproject.model.Game;
import com.eeit109team6.finalproject.model.GameType;
import com.eeit109team6.finalproject.model.NewsType;
import com.eeit109team6.finalproject.service.IActivityService;
import com.eeit109team6.finalproject.service.INewsService;

@Controller
public class ActivityController {

	public ActivityController() {
	}

	IActivityService activityService;

	@Autowired
	public void setActivityService(IActivityService activityService) {
		this.activityService = activityService;
	}

	INewsService newsService;

	@Autowired
	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	ServletContext context;

	@Autowired
	public void setContext(ServletContext context) {
		this.context = context;
	}

//====================================================活動類別=================================================

	// 新增活動類別
	@RequestMapping("/newsBack/addActivityType")
	public String addActivityType(@RequestParam("activityTypeName") String activityTypeName) {
		ActivityType at = new ActivityType();
		at.setActivityTypeName(activityTypeName);
		activityService.addActivityType(at);
		;
		return "redirect:/newsBack";
	}

	// 取得所有遊戲類別的json格式
	@RequestMapping(value = "/newsBack/searchActivityTypeByAjax", produces = "application/json")
	public @ResponseBody List<ActivityType> searchActivityTypeByAjax() {
		return activityService.getAllActivityTypes();
	}

	// 查詢所有活動類別並存入Model(for form:form)
	@ModelAttribute("activityTypes")
	public List<ActivityType> getActivityTypes() {
		return activityService.getAllActivityTypes();
	}

	// 更新活動類別名稱-->newsBack.jsp
	@RequestMapping(value = "/updateActivityType", method = RequestMethod.POST)
	public String updateActivityTypeById(@RequestParam("activityTypeId") Integer activityTypeId,
			@RequestParam("activityTypeName") String activityTypeName) {
		ActivityType at = activityService.getActivityTypeById(activityTypeId);
		at.setActivityTypeName(activityTypeName);
		activityService.updateActivityTypeById(at);

		return "redirect:/newsBack";
	}

	// 刪除活動類別-->newsBack.jsp
	@RequestMapping(value = "/deleteActivityType", method = RequestMethod.POST)
	public String deleteActivityTypeById(@RequestParam("activityTypeId") Integer activityTypeId) {
		activityService.deleteActivityTypeById(activityTypeId);
		return "redirect:/newsBack";
	}

//====================================================活動====================================================

	// 導向新增活動頁面--> addActivity.jsp
	@RequestMapping(value = "/newsBack/addActivity", method = RequestMethod.GET)
	public String getAddNewActivityForm(Model model) {
		Activity activity = new Activity();
		model.addAttribute("activity", activity);

		List<ActivityType> list = activityService.getAllActivityTypes();
		Map<Integer, String> activityTypeMap = new HashMap<>();
		for (ActivityType a : list) {
			activityTypeMap.put(a.getActivityTypeId(), a.getActivityTypeName());
		}
		model.addAttribute("activityTypeMap", activityTypeMap);

		return "addActivity";
	}

	// 新增活動--> 消息後台 newsBack.jsp
	@RequestMapping(value = "/newsBack/addActivity1", method = RequestMethod.POST)
	public String processAddNewActivityForm(@ModelAttribute("activity") Activity activity) throws ParseException {
		Integer at_ = activity.getActivityType_(); // 取回遊戲類別分類id
		ActivityType at = activityService.getActivityTypeById(at_); // 利用id取得遊戲類別
		// 改變日期格式
		if (!(activity.getStartingDate_time()).equals("")) {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String StartingDate_time = format1.format((Date) format.parse(activity.getStartingDate_time()));
			activity.setStartingDate_time(StartingDate_time);
		}
		if (!(activity.getStartingDate()).equals("")) {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String StartingDate = format1.format((Date) format.parse(activity.getStartingDate()));
			activity.setStartingDate(StartingDate);
		}
		if (!(activity.getEndingDate()).equals("")) {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String EndingDate = format1.format((Date) format.parse(activity.getEndingDate()));
			activity.setEndingDate(EndingDate);
		}
		activity.setActivityType(at); // 設定遊戲的遊戲類別
		activityService.addActivity(activity);
		return "redirect:/newsBack";
	}

	// 取得所有活動的json格式
	@RequestMapping(value = "/newsBack/searchActivityByAjax", produces = "application/json")
	public @ResponseBody List<Activity> searchActivityByAjax() {
		return activityService.getAllActivities();
	}

	// 用ajax傳回activityDetail給addNews.jsp
	@RequestMapping(value = "/newsBack/searchActivityByAjax1", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> searchActivityByAjax1(@RequestParam("activityId") Integer activityId) {
		System.out.println(activityId);
		Activity a = activityService.getActivityById(activityId);
		Map<String, String> activityMap = new HashMap<>();
		activityMap.put("name", a.getActivityName());
		activityMap.put("activityTypeName", a.getActivityType().getActivityTypeName());
		activityMap.put("location", a.getLocation());
		// 若未設定日期/時間則不回傳
		System.out.println(a.getStartingTime_date());
		System.out.println(a.getStartingTime_date() instanceof String);
		if (!(a.getStartingDate_time().equals(""))) {
			activityMap.put("startingDate_time", a.getStartingDate_time());
		}
		if (!(a.getStartingTime_date().equals("00:00:00"))) {
			activityMap.put("startingTime_date", a.getStartingTime_date());
		}
		if (!(a.getStartingDate().equals(""))) {
			activityMap.put("startingDate", a.getStartingDate());
		}
		if (!(a.getEndingDate().equals(""))) {
			activityMap.put("endingDate", a.getEndingDate());
		}

		return activityMap;
	}

	// 取得所有活動的json格式
	@RequestMapping(value = "/searchActivityByAjax2_1", produces = "application/json")
	public @ResponseBody List<Activity> searchActivityByAjax2_1() {
		return activityService.getAllActivities();
	}

	// 用ajax傳回activityDetail給updateNews.jsp
	@RequestMapping(value = "/searchActivityByAjax2", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> searchActivityByAjax2(@RequestParam("activityId") Integer activityId) {
		System.out.println(activityId);
		Activity a = activityService.getActivityById(activityId);
		Map<String, String> activityMap = new HashMap<>();
		activityMap.put("name", a.getActivityName());
		activityMap.put("activityTypeName", a.getActivityType().getActivityTypeName());
		activityMap.put("location", a.getLocation());
		// 若未設定日期/時間則不回傳
		System.out.println(a.getStartingTime_date());
		System.out.println(a.getStartingTime_date() instanceof String);
		if (!(a.getStartingDate_time().equals(""))) {
			activityMap.put("startingDate_time", a.getStartingDate_time());
		}
		if (!(a.getStartingTime_date().equals("00:00:00"))) {
			activityMap.put("startingTime_date", a.getStartingTime_date());
		}
		if (!(a.getStartingDate().equals(""))) {
			activityMap.put("startingDate", a.getStartingDate());
		}
		if (!(a.getEndingDate().equals(""))) {
			activityMap.put("endingDate", a.getEndingDate());
		}

		return activityMap;
	}

	// 刪除活動-->newsBack.jsp
	@RequestMapping(value = "/deleteActivity", method = RequestMethod.POST)
	public String deleteActivityById(@RequestParam("activityId") Integer activityId) {
		activityService.deleteActivityById(activityId);
		return "redirect:/newsBack";
	}

	// 更新一日活動細節-->newsBack.jsp
	@RequestMapping(value = "/updateActivityOne", method = RequestMethod.POST)
	public String updateActivityOneById(@RequestParam("activityId") Integer activityId,
			@RequestParam("activityName") String activityName, @RequestParam("activityType") Integer activityTypeId,
			@RequestParam("startingDate_time") String startingDate_time,
			@RequestParam("startingTime_date") String startingTime_date, @RequestParam("location") String location) {
		Activity a = activityService.getActivityById(activityId);
		System.out.println("activityId:" + activityId);
		ActivityType at = activityService.getActivityTypeById(activityTypeId);
		a.setActivityName(activityName);
		a.setActivityType(at);
		a.setStartingDate_time(startingDate_time);
		a.setStartingTime_date(startingTime_date);
		a.setLocation(location);
		activityService.updateActivityById(a);

		return "redirect:/newsBack";
	}

	// 更新多日活動細節-->newsBack.jsp
	@RequestMapping(value = "/updateActivityMore", method = RequestMethod.POST)
	public String updateActivityMoreById(@RequestParam("activityId") Integer activityId,
			@RequestParam("activityName") String activityName, @RequestParam("activityType") Integer activityTypeId,
			@RequestParam("startingDate") String startingDate, @RequestParam("endingDate") String endingDate,
			@RequestParam("location") String location) {
		Activity a = activityService.getActivityById(activityId);
		System.out.println("activityId:" + activityId);
		ActivityType at = activityService.getActivityTypeById(activityTypeId);
		a.setActivityName(activityName);
		a.setActivityType(at);
		a.setStartingDate(startingDate);
		a.setEndingDate(endingDate);
		a.setLocation(location);
		activityService.updateActivityById(a);

		return "redirect:/newsBack";
	}

}
