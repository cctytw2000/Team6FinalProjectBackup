package com.eeit109team6.finalproject.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Component(value = "member")
@Scope(value = "prototype")
@Entity
@Table(name = "member")
public class Member {
	private int member_id;
	private String account;
	private String password;
	private String registeredtime;
	private String token;
	private String username;
	private String headshot;
	private String type;
	private int isactive;

	private MemberDetail memberdetail;

	private MemberLevel memberlevel;

	private Set<LiLoInfo> liLoInfo = new LinkedHashSet<LiLoInfo>();

	private Set<Orders> orders = new LinkedHashSet<Orders>();

	private Set<MemberHeadShot> memberheadshot = new LinkedHashSet<MemberHeadShot>();
	private Set<Reply> reply = new LinkedHashSet<Reply>();
	private Set<MovieInfo> movieInfo = new LinkedHashSet<MovieInfo>();
	
	@OneToMany( mappedBy = "member" ,cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	public Set<MovieInfo> getMovieInfo() {
		return movieInfo;
	}
	
	public void setMovieInfo(Set<MovieInfo> movieInfo) {
		this.movieInfo = movieInfo;
	}

	


//	private Set<MovieInfo> movieInfo = new LinkedHashSet<MovieInfo>();
//	
//	@OneToMany( mappedBy = "member" ,cascade = CascadeType.ALL , fetch = FetchType.LAZY)
//	public Set<MovieInfo> getMovieInfo() {
//		return movieInfo;
//	}
//	
//	public void setMovieInfo(Set<MovieInfo> movieInfo) {
//		this.movieInfo = movieInfo;
//	}


//	private Set<MovieLike> movieLike = new LinkedHashSet<MovieLike>();

//	@OneToMany(mappedBy = "member",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	public Set<MovieLike> getMovieLike() {
//		return movieLike;
//	}
//	
//	public void setMovieLike(Set<MovieLike> movieLike) {
//		this.movieLike = movieLike;
//	}

	private Set<Discussion> discussion = new LinkedHashSet<Discussion>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "member", fetch = FetchType.LAZY)
	@JsonIgnore
	public Set<Discussion> getDiscussion() {
		return discussion;
	}

	public void setDiscussion(Set<Discussion> discussion) {
		this.discussion = discussion;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBERLEVEL")
	@JsonIgnore
	public MemberLevel getMemberlevel() {
		return memberlevel;
	}

	public void setMemberlevel(MemberLevel memberlevel) {
		this.memberlevel = memberlevel;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "member", fetch = FetchType.LAZY)
	@JsonIgnore
	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "member", fetch = FetchType.LAZY)
	@JsonIgnore
	public Set<LiLoInfo> getLiLoInfo() {
		return liLoInfo;
	}

	public void setLiLoInfo(Set<LiLoInfo> liLoInfo) {
		this.liLoInfo = liLoInfo;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
	@JsonIgnore
	public MemberDetail getMemberdetail() {
		return memberdetail;
	}

	public void setMemberdetail(MemberDetail memberdetail) {
		this.memberdetail = memberdetail;
	}

	@Column(name = "ACCOUNT")
	public String getAccount() {
		return account;
	}

	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "REGISTEREDTIME")
	public String getRegisteredtime() {
		return registeredtime;
	}

	public void setRegisteredtime(String registeredtime) {
		this.registeredtime = registeredtime;
	}

	@Id
	@Column(name = "MEMBER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	@Column(name = "ISACTIVE")
	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	@Column(name = "TOKEN")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHeadshot() {
		return headshot;
	}

	public void setHeadshot(String headshot) {
		this.headshot = headshot;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "member", fetch = FetchType.EAGER)
//	@JsonIgnore
	public Set<MemberHeadShot> getMemberheadshot() {
		return memberheadshot;
	}

	public void setMemberheadshot(Set<MemberHeadShot> memberheadshot) {
		this.memberheadshot = memberheadshot;
	}
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "member", fetch = FetchType.LAZY)
	public Set<Reply> getReply() {
		return reply;
	}

	public void setReply(Set<Reply> reply) {
		this.reply = reply;
	}

//	================================新消息要關聯的部分================================
//	@ManyToMany
//	@JoinTable(name="userlike",
//	joinColumns = @JoinColumn(name="member_id"),
//	inverseJoinColumns = @JoinColumn(name="newsid"))
//	private Set<News> likedNewses = new HashSet<News>();
//	
//	public Set<News> getLikedNewses() {
//		return likedNewses;
//	}
//
//	public void setLikedNewses(Set<News> likedNewses) {
//		this.likedNewses = likedNewses;
//	}

}
