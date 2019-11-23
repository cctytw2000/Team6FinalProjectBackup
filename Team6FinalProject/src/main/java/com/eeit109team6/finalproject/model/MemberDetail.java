package com.eeit109team6.finalproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component(value = "memberDetail")
@Scope(value = "prototype")
@Entity
@Table(name = "memberDetail")
public class MemberDetail {
	private Integer memberID;
	private String idnumber;
	private String sex;
	private String birth;
	private String nickname;
	private String tel;
	private String address;
	private Member member;

	
	
	
	
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
	
	
	
	@Transient
	private String token;
	@Transient
	private Integer id;
	@Transient
	private String username;

	@Transient
	private String type;

	@Transient
	@JsonIgnore
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Transient
	@JsonIgnore
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Transient
	@JsonIgnore
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Transient
	@JsonIgnore
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@Column(name = "MEMBERID")
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "member"))
	@GeneratedValue(generator = "generator")
	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	@Column(name = "IDNUMBER")
	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	@Column(name = "SEX")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "BIRTH")
	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@PrimaryKeyJoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



}
