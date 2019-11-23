package com.eeit109team6.finalproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="orderItem")
public class OrderItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer item_id;   //商品細項編號
	private Integer count;  //商品數量
	private Integer subtotal;  //小計
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Product product;   //購買的遊戲	
	@JsonIgnore	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders order;  //所屬訂單
//	private Integer game_id;
//	@Transient
//	private Integer order_id;
	
	
	

	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

//	public Integer getGame_id() {
//		return game_id;
//	}
//	public void setGame_id(Integer game_id) {
//		this.game_id = game_id;
//	}
	
}
