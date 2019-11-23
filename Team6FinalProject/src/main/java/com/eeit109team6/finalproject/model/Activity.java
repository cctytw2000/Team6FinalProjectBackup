package com.eeit109team6.finalproject.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "activity")
public class Activity {
	@JsonIgnore
	@Transient
	private Integer activityType_;
	@JsonIgnore
	@Transient
	private Integer newsType_;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer activityId;
	private String activityName;
	private String startingDate_time;
	private String startingTime_date;
	private String startingDate;
	private String endingDate;
	private String location;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITYTYPEID")
	private ActivityType activityType;

	public Activity() {
	}

	public String getStartingTime_date() {
		return startingTime_date;
	}

	public void setStartingTime_date(String startingTime_date) {
		this.startingTime_date = startingTime_date;
	}

	public Integer getActivityType_() {
		return activityType_;
	}

	public void setActivityType_(Integer activityType_) {
		this.activityType_ = activityType_;
	}

	public Integer getNewsType_() {
		return newsType_;
	}

	public void setNewsType_(Integer newsType_) {
		this.newsType_ = newsType_;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public ActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getStartingDate_time() {
		return startingDate_time;
	}

	public void setStartingDate_time(String startingDate_time) {
		this.startingDate_time = startingDate_time;
	}

	public String getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	public String getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(String endingDate) {
		this.endingDate = endingDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
