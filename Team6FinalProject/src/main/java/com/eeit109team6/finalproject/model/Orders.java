package com.eeit109team6.finalproject.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="orders")
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer order_id;
	private String ordertime;
	private Integer total;
	private Integer state;
	@JsonIgnore	
	@Transient	
	private Integer member_id;
	private String address;
	private boolean is_remove;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id" )
	//@JsonIgnoreProperties("orders")
	private Member member;
	
	@JsonIgnore
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL)
	private Set<OrderItem> orderItems = new LinkedHashSet<>();
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	
  //訂單所有項目
	
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public boolean isIs_remove() {
		return is_remove;
	}
	public void setIs_remove(boolean is_remove) {
		this.is_remove = is_remove;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
}
