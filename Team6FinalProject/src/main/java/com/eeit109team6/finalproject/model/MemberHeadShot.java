package com.eeit109team6.finalproject.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component(value = "member")
@Scope(value = "prototype")
@Entity
@Table(name = "memberHeadShot")
public class MemberHeadShot {

	private Integer id;
	private String headshotname;
	private Member member;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBERID")
	@JsonIgnore
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getHeadshotname() {
		return headshotname;
	}

	public void setHeadshotname(String headshotname) {
		this.headshotname = headshotname;
	}

}
