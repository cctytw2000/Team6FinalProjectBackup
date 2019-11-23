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
@Table
public class SubjectType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subjectTypeId;//發文分類編號
	private String subjectName;//發文分類名稱
	
	@JsonIgnore
	private Blob subjectImage;// 看板圖片
	
	@Transient
	private MultipartFile sImage; //除了Blob，也要另設一個MultipartFile型態的屬性，控制器方法才能使用
	
	
	//mappedBy="對方變數名稱"
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectType", fetch = FetchType.LAZY)
	private Set<Discussion> discussion = new LinkedHashSet<Discussion>();

	public Integer getSubjectTypeId() {
		return subjectTypeId;
	}

	public void setSubjectTypeId(Integer subjectTypeId) {
		this.subjectTypeId = subjectTypeId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Set<Discussion> getDiscussion() {
		return discussion;
	}

	public void setDiscussion(Set<Discussion> discussion) {
		this.discussion = discussion;
	}
	
	public Blob getSubjectImage() {
		return subjectImage;
	}

	public void setSubjectImage(Blob subjectImage) {
		this.subjectImage = subjectImage;
	}

	public MultipartFile getsImage() {
		return sImage;
	}

	public void setsImage(MultipartFile sImage) {
		this.sImage = sImage;
	}
}
