package com.eeit109team6.finalproject.model;

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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component(value = "memberLevel")
@Scope(value = "prototype")
@Entity
@Table(name = "memberLevel")
public class MemberLevel {
	private Integer levelId;
	private String levelName;
	@JsonIgnore
	private Set<Member> member = new LinkedHashSet<Member>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "memberlevel", fetch = FetchType.LAZY)

	public Set<Member> getMember() {
		return member;
	}

	public void setMember(Set<Member> member) {
		this.member = member;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

}
