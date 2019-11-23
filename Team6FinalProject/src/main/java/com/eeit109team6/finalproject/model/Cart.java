package com.eeit109team6.finalproject.model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

//購物車主體
public class Cart {
	// add 添加項目
	// remove 刪除項目
	// clear 清空項目
	
	private Map<Integer, CartItem> map = new LinkedHashMap<>();
	
	//添加項目到購物車中
	public void add(CartItem cartItem) {
		if(map.containsKey(cartItem.getProduct().getGame_id())) { //項目已存在在購物車中，數量要相加
			System.out.println("項目已存在在購物車中");
			CartItem _cartItem = map.get(cartItem.getProduct().getGame_id()); //拿出原項目
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount()); //設置原項目數量為:原項目數量+新項目數量
			map.put(cartItem.getProduct().getGame_id(), _cartItem); //放回原項目
		}else { //項目不存在在原購物車中，直接把項目加進購物車中
			System.out.println("項目不存在在原購物車中");
			map.put(cartItem.getProduct().getGame_id(), cartItem);
		}
	}
	
	//刪除指定項目
	public void remove(Integer game_id) { 
		map.remove(game_id);
	}
	
	//清空購物車中所有項目
	public void clear() { 
		map.clear();
	}
	
	//獲取所有項目
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	//計算購物車合計=項目所有小計之和
	public int getTotal() {
		int total = 0;
		for(CartItem cartItem : map.values()) {
			total += cartItem.getSubtotal();
		}
		return total;
	}

	
	
}
