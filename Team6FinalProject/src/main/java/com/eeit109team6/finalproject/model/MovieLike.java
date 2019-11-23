package com.eeit109team6.finalproject.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="movielike")
public class MovieLike {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer like_ID;
	
//	@Transient
//	private int movie_ID;
	
//	@Transient
//	private int owner_ID;
	
	private Date time;

	
	
	@ManyToOne
	@JoinColumn(name = "MOVIE_ID")
	private MovieInfo movieInfo;
	
	public MovieInfo getMovieInfo() {
		return movieInfo;
	}

	public void setMovieInfo(MovieInfo movieInfo) {
		this.movieInfo = movieInfo;
	}
	
	
	@ManyToOne
	@JoinColumn(name="OWNER_ID" )
	private Member member;
	
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	
	
	
	
	
	
	

	public Integer getLike_ID() {
		return like_ID;
	}

	public void setLike_ID(Integer like_ID) {
		this.like_ID = like_ID;
	}

//	public Integer getMovie_ID() {
//		return movie_ID;
//	}
//
//	public void setMovie_ID(Integer movie_ID) {
//		this.movie_ID = movie_ID;
//	}
//
//	public Integer getOwner_ID() {
//		return owner_ID;
//	}
//
//	public void setOwner_ID(Integer owner_ID) {
//		this.owner_ID = owner_ID;
//	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	

	
	
	
}
