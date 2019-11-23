package com.eeit109team6.finalproject.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.io.filefilter.FalseFileFilter;



import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "movieInfo")
public class MovieInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movie_ID;

	private String name;

	private String movie_content;
	

	//private String date;
	

	private String Time;

//	@Transient
//	private int owner_ID;

	@Transient
	private Integer like_Sum;

	private Integer click_Sum;

	private String location_Test;

	@Transient
	private Blob video;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_ID")
	@JsonIgnore
	private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "movieInfo", fetch = FetchType.LAZY)
	private Set<MovieLike> movieLike = new LinkedHashSet<MovieLike>();

	public Set<MovieLike> getMovieLike() {
		return movieLike;
	}

	public void setMovieLike(Set<MovieLike> movieLike) {
		this.movieLike = movieLike;
	}

	public Integer getMovie_ID() {
		return movie_ID;
	}

	public void setMovie_ID(Integer movie_ID) {
		this.movie_ID = movie_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMovie_content() {
		return movie_content;
	}

	public void setMovie_content(String movie_content) {
		this.movie_content = movie_content;
	}
//	@Column(name="date",insertable = false,updatable = false)
//	public String getDate() {
//		return date;
//	}
//	public void setDate(String date) {
//		this.date = date;
//	}
//	public Integer getOwner_ID() {
//		return owner_ID;
//	}
//	public void setOwner_ID(int owner_ID) {
//		this.owner_ID = owner_ID;
//	}
	public Integer getLike_Sum() {
		return like_Sum;
	}

	public void setLike_Sum(Integer like_Sum) {
		this.like_Sum = like_Sum;
	}

	public Integer getClick_Sum() {
		return click_Sum;
	}

	public void setClick_Sum(Integer click_Sum) {
		this.click_Sum = click_Sum;
	}

	public String getLocation_Test() {
		return location_Test;
	}

	public void setLocation_Test(String location_Test) {
		this.location_Test = location_Test;
	}

	public Blob getVideo() {
		return video;
	}

	public void setVideo(Blob video) {
		this.video = video;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

}
