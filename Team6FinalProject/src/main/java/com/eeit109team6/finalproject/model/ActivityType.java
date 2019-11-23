package com.eeit109team6.finalproject.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="activitytype")
public class ActivityType {

	@Id
	@Column(name="ACTIVITYTYPEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer activityTypeId;
	@Column(name="ACTIVITYTYPENAME")
	private String activityTypeName;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "activityType", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Activity> activities = new HashSet<Activity>();
	
	public ActivityType() {
	}

	public Integer getActivityTypeId() {
		return activityTypeId;
	}

	public void setActivityTypeId(Integer activityTypeId) {
		this.activityTypeId = activityTypeId;
	}

	public String getActivityTypeName() {
		return activityTypeName;
	}

	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}
	
	

}
