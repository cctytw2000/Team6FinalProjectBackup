package com.eeit109team6.finalproject.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component(value = "liLoInfo")
@Scope(value = "prototype")
@Entity
@Table(name = "LiLoInfo")
public class LiLoInfo {
//	private Integer memberID;
	private String type;
	private String clientIP;
	private String loginTime;
	private String accountType;
	private Integer isSuccess;

	private Member member;
	private Integer id ;


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


//	@Column(name = "MEMBERID")
//	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "member"))
//	@GeneratedValue(generator = "generator")
//	public Integer getMemberID() {
//		return memberID;
//	}
//
//	public void setMemberID(Integer memberID) {
//		this.memberID = memberID;
//	}
//
//
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MEMBERID" )
	@JsonIgnore
	public Member getMember() {
		return member;
	}



	public void setMember(Member member) {
		this.member = member;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Integer getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}

}
