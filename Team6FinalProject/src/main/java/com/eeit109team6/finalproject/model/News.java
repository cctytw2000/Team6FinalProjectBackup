package com.eeit109team6.finalproject.model;

import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "news")
public class News {
	@JsonIgnore
	@Transient
	private Integer game_;
	@JsonIgnore
	@Transient
	private Integer newsType_;
	@JsonIgnore
	@Transient
	private Integer activity_;
	@JsonIgnore
	@Transient
	private MultipartFile newsImage;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer newsId;
	private String title;
	@Temporal(TemporalType.TIMESTAMP)  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date publicationDate;
	private String article;
	private Integer likes;
	private Integer views;
	private Boolean isVisable;
	private String ipAddress;
	private Date lastUpdated;
	@JsonIgnore
	private Blob picture;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "news", cascade = CascadeType.ALL)
	private Set<Message> messages = new HashSet<Message>();
	// 單向多對一
	@JoinColumn(name = "GAMEID")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Game game;
	@JoinColumn(name = "ACTIVITYID")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Activity activity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	@JsonIgnore
	private Member member;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NEWSTYPEID")
	private NewsType newsType;

	public MultipartFile getNewsImage() {
		return newsImage;
	}

	public void setNewsImage(MultipartFile newsImage) {
		this.newsImage = newsImage;
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public News() {
	}

	public Integer getActivity_() {
		return activity_;
	}

	public void setActivity_(Integer activity_) {
		this.activity_ = activity_;
	}

	public Integer getGame_() {
		return game_;
	}

	public void setGame_(Integer game_) {
		this.game_ = game_;
	}

	public Integer getNewsType_() {
		return newsType_;
	}

	public void setNewsType_(Integer newsType_) {
		this.newsType_ = newsType_;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public NewsType getNewsType() {
		return newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Boolean getIsVisable() {
		return isVisable;
	}

	public void setIsVisable(Boolean isVisable) {
		this.isVisable = isVisable;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

//	public Set<Member> getMemberLikes() {
//		return memberLikes;
//	}
//
//	public void setMemberLikes(Set<Member> memberLikes) {
//		this.memberLikes = memberLikes;
//	}

}
