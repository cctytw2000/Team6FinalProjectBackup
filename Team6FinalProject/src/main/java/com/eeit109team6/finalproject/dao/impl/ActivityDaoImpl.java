package com.eeit109team6.finalproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eeit109team6.finalproject.dao.IActivityDao;
import com.eeit109team6.finalproject.model.Activity;
import com.eeit109team6.finalproject.model.ActivityType;

@Repository
public class ActivityDaoImpl implements IActivityDao {

	public ActivityDaoImpl() {
	}

	SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
//====================================================活動類別=================================================

	@Override
	public void addActivityType(ActivityType activityType) {
		Session session = sessionFactory.getCurrentSession();
		session.save(activityType);
	}

	@Override
	public List<ActivityType> getAllActivityTypes() {
		String hql = "FROM ActivityType";
		Session session = sessionFactory.getCurrentSession();
		List<ActivityType> list = new ArrayList<>();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public ActivityType getActivityTypeById(Integer activityTypeId) {
		Session session = sessionFactory.getCurrentSession();
		ActivityType activityType = session.get(ActivityType.class, activityTypeId);
		return activityType;
	}

	@Override
	public void updateActivityTypeById(ActivityType activityType) {
		Session session = sessionFactory.getCurrentSession();
//		session.clear();
		session.update(activityType);
	}

	@Override
	public void deleteActivityTypeById(Integer activityTypeId) {
		Session session = sessionFactory.getCurrentSession();
		ActivityType at = session.get(ActivityType.class, activityTypeId);
		session.delete(at);
	}

//====================================================活動====================================================
	
	@Override
	public void addActivity(Activity activity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(activity);
	}

	@Override
	public List<Activity> getAllActivities() {
		String hql = "FROM Activity";
		List<Activity> list = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public Activity getActivityById(Integer activityId) {
		Session session = sessionFactory.getCurrentSession();
		Activity activity = session.get(Activity.class, activityId);
		return activity;
	}
	
	@Override
	public void deleteActivityById(Integer activityId) {
		Session session = sessionFactory.getCurrentSession();
		Activity activity = session.get(Activity.class, activityId);
		session.delete(activity);
	}
	
	@Override
	public void updateActivityById(Activity activity) {
		Session session = sessionFactory.getCurrentSession();
		session.update(activity);
	}

//====================================================未完成===================================================

	

	

}
