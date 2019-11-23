package com.eeit109team6.finalproject.model;

//購物車內細項
public class CartItem {
	private Product product; //商品
	private int count; //數量
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSubtotal() { //小計方法
		int sum = product.getPrice()*count;
		return sum;
	}
}
