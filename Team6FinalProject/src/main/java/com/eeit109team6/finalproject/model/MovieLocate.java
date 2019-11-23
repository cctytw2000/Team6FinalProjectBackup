package com.eeit109team6.finalproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="movielocate")
public class MovieLocate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer locate_ID;
	
	private Integer movie_ID;
	@Transient
	private Integer type;
	@Transient
	private String location;
	
	
	
	public Integer getLocate_ID() {
		return locate_ID;
	}
	public void setLocate_ID(Integer locate_ID) {
		this.locate_ID = locate_ID;
	}
	public Integer getMovie_ID() {
		return movie_ID;
	}
	public void setMovie_ID(Integer movie_ID) {
		this.movie_ID = movie_ID;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	
}
