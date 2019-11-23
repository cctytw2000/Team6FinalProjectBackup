package com.eeit109team6.finalproject.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "newstype")
public class NewsType {

	@Id
	@Column(name = "NEWSTYPEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer newsTypeId;
	@Column(name = "NEWSTYPENAME")
	private String newsTypeName;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "newsType", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<News> newses = new HashSet<News>();

	public Integer getNewsTypeId() {
		return newsTypeId;
	}

	public void setNewsTypeId(Integer newsTypeId) {
		this.newsTypeId = newsTypeId;
	}

	public String getNewsTypeName() {
		return newsTypeName;
	}

	public void setNewsTypeName(String newsTypeName) {
		this.newsTypeName = newsTypeName;
	}

	public Set<News> getNewses() {
		return newses;
	}

	public void setNewses(Set<News> newses) {
		this.newses = newses;
	}

}
