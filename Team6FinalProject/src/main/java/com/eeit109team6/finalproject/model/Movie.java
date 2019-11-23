package com.eeit109team6.finalproject.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@Component(value = "Movie")
//@Scope(value = "prototype")
@Entity
@Table(name = "movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer movieId;
	
	private String movieName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", fetch = FetchType.LAZY)
	private Set<HomeMovie> homeMovie = new LinkedHashSet<HomeMovie>();

	
	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	
//	@JsonIgnore
	public Set<HomeMovie> getHomeMovie() {
		return homeMovie;
	}

	public void setHomeMovie(Set<HomeMovie> homeMovie) {
		this.homeMovie = homeMovie;
	}

}
