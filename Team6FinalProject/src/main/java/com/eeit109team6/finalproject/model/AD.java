package com.eeit109team6.finalproject.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AD")
public class AD {

	private Integer ADId;
	private String ADName;
	private Blob picture;
	private GameType gameType;
	
	public AD() {
	}

	@Id
	@Column(name="ADID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getADId() {
		return ADId;
	}

	public void setADId(Integer aDId) {
		ADId = aDId;
	}

	@Column(name="ADNAME")
	public String getADName() {
		return ADName;
	}

	public void setADName(String aDName) {
		ADName = aDName;
	}

	@Column(name="PICTURE")
	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GAMETYPEID")
	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
	
}
