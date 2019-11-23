package com.eeit109team6.finalproject.service;

import java.util.List;

import com.eeit109team6.finalproject.model.Activity;
import com.eeit109team6.finalproject.model.ActivityType;
import com.eeit109team6.finalproject.model.GameType;

public interface IActivityService {
//====================================================活動類別=================================================
	void addActivityType(ActivityType activityType);
	List<ActivityType> getAllActivityTypes();
	ActivityType getActivityTypeById(Integer activityTypeId);
	void updateActivityTypeById(ActivityType activityType); //更新活動類別
	void deleteActivityTypeById(Integer activityTypeId); //刪除活動類別
//====================================================活動====================================================
	void addActivity(Activity activity); 
	List<Activity> getAllActivities();
	public Activity getActivityById(Integer activityId);
	void deleteActivityById(Integer activityId); 
	void updateActivityById(Activity activity); 
//====================================================未完成===================================================		
	 
	
	
	
		
}
