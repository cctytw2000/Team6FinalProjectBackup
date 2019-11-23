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
@Table(name = "gametype")
public class GameType {
	@Id
	@Column(name = "GAMETYPEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer gameTypeId;
	@Column(name = "GAMETYPENAME")
	private String gameTypeName;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gameType", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Game> games = new HashSet<Game>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gameType", cascade = CascadeType.ALL)
	private Set<AD> ADs= new HashSet<AD>();

	public GameType() {
	}

	public Integer getGameTypeId() {
		return gameTypeId;
	}

	public void setGameTypeId(Integer gameTypeId) {
		this.gameTypeId = gameTypeId;
	}

	public String getGameTypeName() {
		return gameTypeName;
	}

	public void setGameTypeName(String gameTypeName) {
		this.gameTypeName = gameTypeName;
	}

	public Set<Game> getGames() {
		return games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}
	
	public Set<AD> getADs() {
		return ADs;
	}

	public void setADs(Set<AD> aDs) {
		ADs = aDs;
	}

}
