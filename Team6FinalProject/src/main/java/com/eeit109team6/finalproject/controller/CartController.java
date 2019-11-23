package com.eeit109team6.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eeit109team6.finalproject.model.Cart;
import com.eeit109team6.finalproject.model.CartItem;
import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.Product;
import com.eeit109team6.finalproject.service.ProductService;

@Controller
public class CartController {
	ProductService service;

	@Autowired
	public void setService(ProductService service) {
		this.service = service;
	}
	
	@RequestMapping("/addToCart")
	public String add(@RequestParam("game_id") Integer game_id, @RequestParam("count") Integer count, 
			HttpServletRequest request, HttpSession session, Model model) {
//		request.getSession().setAttribute("cart", new Cart()); //得到一台購物車，應該要寫到登入成功內?
//		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		Member member = (Member)session.getAttribute("mem");
		if(member == null) {
			Member mem = new Member();
			mem.setAccount("sandy60108@yahoo.com.tw");
			mem.setPassword("a14789632");
			mem.setUsername("andy");
			model.addAttribute("Member", mem);
			model.addAttribute("msg", "您必須先登入!");
			return "jump";
		}
		
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		//以上測試
		Product product = service.getProductById(game_id);
		CartItem cartItem = new CartItem(); //建立項目
		cartItem.setProduct(product); //設定項目商品
		cartItem.setCount(count); //設定項目數量
		cart.add(cartItem); //把項目加到購物車中
//		request.getSession().setAttribute("cart", cart);
		session.setAttribute("cart", cart);
		
		
		Member mem = new Member();
		mem.setAccount("sandy60108@yahoo.com.tw");
		mem.setPassword("a14789632");
		mem.setUsername("andy");
		model.addAttribute("Member", mem);
		
		return "cart";
	}
	
	@RequestMapping("/clearCart")
	public String clear(HttpServletRequest request, Model model) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clear();
		
		
		Member mem = new Member();
		mem.setAccount("sandy60108@yahoo.com.tw");
		mem.setPassword("a14789632");
		mem.setUsername("andy");
		model.addAttribute("Member", mem);
		
		return "cart";
	}
	
	@RequestMapping("/removeCartItem")
	public String remove(@RequestParam("game_id") Integer game_id, HttpServletRequest request, Model model) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.remove(game_id);
		
		
		Member mem = new Member();
		mem.setAccount("sandy60108@yahoo.com.tw");
		mem.setPassword("a14789632");
		mem.setUsername("andy");
		model.addAttribute("Member", mem);
		
		return "cart";
	}
	
	@RequestMapping("/showCart")
	public String showCart(HttpSession session, Model model) {
		
		Member mem = new Member();
		mem.setAccount("sandy60108@yahoo.com.tw");
		mem.setPassword("a14789632");
		mem.setUsername("andy");
		model.addAttribute("Member", mem);
		
		List<Product> list = service.getAllProducts();
		session.setAttribute("products", list);
		
		return "cart";
	}
}
