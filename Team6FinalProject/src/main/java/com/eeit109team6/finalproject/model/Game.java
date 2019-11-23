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
@Table(name = "game")
public class Game {
	@JsonIgnore
	@Transient
	private Integer gameType_;
	@JsonIgnore
	@Transient
	private Integer newsType_;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer gameId;
	private String gameName;
	private String publicationDate;
	private String publisher;
	private String platform;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GAMETYPEID")
	private GameType gameType;

	public Game() {
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Integer getGameType_() {
		return gameType_;
	}

	public void setGameType_(Integer gameType_) {
		this.gameType_ = gameType_;
	}

	public Integer getNewsType_() {
		return newsType_;
	}

	public void setNewsType_(Integer newsType_) {
		this.newsType_ = newsType_;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}
