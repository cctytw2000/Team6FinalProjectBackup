package com.eeit109team6.finalproject.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Discussion")
public class Discussion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleId; // 文章編號
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "boardId")// 本方資料表關聯欄位
	@JsonIgnore
	private BoardType boardType; // 所屬看板編號。外來鍵，使用對方類別宣告型態
	private String subject; // 文章標題
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "memberId")// 本方資料表關聯欄位
	@JsonIgnore
	private Member member ;	// 發文會員。外來鍵，使用對方類別宣告型態
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "subjectTypeId")// 本方資料表關聯欄位
	@JsonIgnore
	private SubjectType subjectType;// 發文時的標題分類
	private String articleBody; // 文章內文
	private String postTimeStamp;//發文時間戳
	private Integer views; // 文章被瀏覽次數
	private Integer isDeleted;//是否軟刪除


	//mappedBy="對方變數名稱"
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "discussion", fetch = FetchType.LAZY)
	private Set<Reply> reply = new LinkedHashSet<Reply>();
	
	
	public Discussion() { // 子類建構子呼叫父類建構子
		super();
	}
	
	public Set<Reply> getReply() {
		return reply;
	}

	public void setReply(Set<Reply> reply) {
		this.reply = reply;
	}
	
	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public BoardType getBoardType() {
		return boardType;
	}

	public void setBoardType(BoardType boardType) {
		this.boardType = boardType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public SubjectType getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(SubjectType subjectType) {
		this.subjectType = subjectType;
	}

	public String getArticleBody() {
		return articleBody;
	}

	public void setArticleBody(String articleBody) {
		this.articleBody = articleBody;
	}

	public String getPostTimeStamp() {
		return postTimeStamp;
	}

	public void setPostTimeStamp(String postTimeStamp) {
		this.postTimeStamp = postTimeStamp;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

}
