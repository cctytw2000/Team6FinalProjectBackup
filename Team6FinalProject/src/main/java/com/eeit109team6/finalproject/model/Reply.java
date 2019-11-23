package com.eeit109team6.finalproject.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Reply")
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer replyId;	//回覆編號
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="articleId")// 本方資料表欄位
	@JsonIgnore
	private Discussion discussion;	//所屬文章。外來鍵，使用對方類別宣告型態
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="memberId")// 本方資料表欄位
	@JsonIgnore
	private Member member;		//作者。外來鍵，使用對方類別宣告型態
	
	private String 	replyBody;	//回覆本體
	private String 	postTimeStamp;//發文時間戳
//	private String	modifyTimeStamp;//最後修文時間戳
	private Boolean is_removed;	//是否被刪除
	
	
	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	public Discussion getDiscussion() {
		return discussion;
	} 
	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getReplyBody() {
		return replyBody;
	}
	public void setReplyBody(String replyBody) {
		this.replyBody = replyBody;
	}
	public String getPostTimeStamp() {
		return postTimeStamp;
	}
	public void setPostTimeStamp(String postTimeStamp) {
		this.postTimeStamp = postTimeStamp;
	}
	public Boolean getIs_removed() {
		return is_removed;
	}
	public void setIs_removed(Boolean is_removed) {
		this.is_removed = is_removed;
	}
	

}
