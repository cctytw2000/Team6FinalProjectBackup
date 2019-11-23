package com.eeit109team6.finalproject.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="moviebrowselog")
public class MovieBrowseLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Log_ID;
	
	private Integer Movie_ID;
	
	private Integer Owner_ID;
	
	private Date Time;

	
	public Integer getLog_ID() {
		return Log_ID;
	}

	public void setLog_ID(Integer log_ID) {
		Log_ID = log_ID;
	}

	public Integer getMovie_ID() {
		return Movie_ID;
	}

	public void setMovie_ID(Integer movie_ID) {
		Movie_ID = movie_ID;
	}

	public Integer getOwner_ID() {
		return Owner_ID;
	}

	public void setOwner_ID(Integer owner_ID) {
		Owner_ID = owner_ID;
	}

	public Date getTime() {
		return Time;
	}

	public void setTime(Date time) {
		Time = time;
	}
	

}
