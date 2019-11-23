package com.eeit109team6.finalproject.model;

import java.sql.Blob;
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
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BoardType")
public class BoardType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardId; // 看板編號
	private String boardName; // 看板名稱
	@JsonIgnore
	private Blob boardImage;// 看板圖片
	
	@Transient
	private MultipartFile bImage; //除了Blob，也要另設一個MultipartFile型態的屬性，控制器方法才能使用
	
	private Integer boardViews; // 看板被瀏覽次數

	//mappedBy="對方變數名稱"
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "boardType", fetch = FetchType.LAZY)
	private Set<Discussion> discussion = new LinkedHashSet<Discussion>();


	public Set<Discussion> getDiscussion() {
		return discussion;
	}

	public void setDiscussion(Set<Discussion> discussion) {
		this.discussion = discussion;
	}


	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
	public Blob getBoardImage() {
		return boardImage;
	}

	public void setBoardImage(Blob boardImage) {
		this.boardImage = boardImage;
	}
	
	public MultipartFile getbImage() {
		return bImage;
	}

	public void setbImage(MultipartFile bImage) {
		this.bImage = bImage;
	}

	public Integer getBoardViews() {
		return boardViews;
	}

	public void setBoardViews(Integer boardViews) {
		this.boardViews = boardViews;
	}

}
