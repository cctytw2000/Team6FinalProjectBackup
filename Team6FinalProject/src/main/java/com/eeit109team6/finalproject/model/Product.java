package com.eeit109team6.finalproject.model;

import java.sql.Blob;
import java.util.Date;
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
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
public class Product {
	@Transient
	private Integer category_;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer game_id;
	private String name;
	private String publisher;
	private Integer price;
	private String game_desc;
	private Date date;
	private Integer stock;	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	@JsonIgnore
	private Blob photo;
	private Integer is_remove;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "product", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<OrderItem> orderItems = new LinkedHashSet<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Comment> comment = new LinkedHashSet<>();

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

	@Transient
	public Integer getCategory_() {
		return category_;
	}

	public void setCategory_(Integer category_) {
		this.category_ = category_;
	}

	@Transient
	private MultipartFile productImage;

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	public Integer getGame_id() {
		return game_id;
	}

	public void setGame_id(Integer game_id) {
		this.game_id = game_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getGame_desc() {
		return game_desc;
	}

	public void setGame_desc(String game_desc) {
		this.game_desc = game_desc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getIs_remove() {
		return is_remove;
	}

	public void setIs_remove(Integer is_remove) {
		this.is_remove = is_remove;
	}
}
