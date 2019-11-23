package com.eeit109team6.finalproject.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="heatmap")
public class HeatMap {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer heat_ID;
	
	private Integer movie_ID;
	
	private Integer owner_ID;
	
	private Date time;

	private Integer location_Num;

	public Integer getHeat_ID() {
		return heat_ID;
	}

	public void setHeat_ID(Integer heat_ID) {
		this.heat_ID = heat_ID;
	}

	public Integer getMovie_ID() {
		return movie_ID;
	}

	public void setMovie_ID(Integer movie_ID) {
		this.movie_ID = movie_ID;
	}

	public Integer getOwner_ID() {
		return owner_ID;
	}

	public void setOwner_ID(Integer owner_ID) {
		this.owner_ID = owner_ID;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getLocation_Num() {
		return location_Num;
	}

	public void setLocation_Num(Integer location_Num) {
		this.location_Num = location_Num;
	}

	
	
}
