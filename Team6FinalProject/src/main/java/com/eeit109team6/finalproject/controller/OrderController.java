package com.eeit109team6.finalproject.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eeit109team6.finalproject.model.Cart;
import com.eeit109team6.finalproject.model.CartItem;
import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.Orders;
import com.eeit109team6.finalproject.model.Product;
import com.eeit109team6.finalproject.model.OrderItem;
import com.eeit109team6.finalproject.service.IMemberService;
import com.eeit109team6.finalproject.service.OrderService;
import com.eeit109team6.finalproject.service.ProductService;

@Controller
public class OrderController {
	OrderService service;
	ProductService serviceP;

	@Autowired
	public void setServiceP(ProductService serviceP) {
		this.serviceP = serviceP;
	}

	@Autowired
	public void setService(OrderService service) {
		this.service = service;
	}

	@RequestMapping("/makeOrder")
	public String makeOrder(HttpSession session, Model model, RedirectAttributes redirectAttributes) throws ParseException {
		Cart cart = (Cart) session.getAttribute("cart");
		Orders order = new Orders();

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d = new Date();
		String sd = sdFormat.format(d);
		order.setOrdertime(sd); // 訂單成立時間

		order.setTotal(cart.getTotal()); // 訂單總金額

		Member member = (Member) session.getAttribute("mem");
		order.setMember(member); // 訂單所屬人->可以用EL取名字、地址

//		order.setMember_id(member.getMember_id()); //member_id

		order.setAddress(member.getMemberdetail().getAddress()); // address

		order.setState(1); // 訂單狀態:1->表示未付款

		order.setIs_remove(false); // 不是取消->剛成立

		Set<OrderItem> orderItemSet = new LinkedHashSet<>();
		for (CartItem cartItem : cart.getCartItems()) {
			if(cartItem.getCount()>cartItem.getProduct().getStock()) { //如果項目數量>商品庫存
//				System.out.println("項目數量"+cartItem.getCount()+">商品庫存"+cartItem.getProduct().getStock());
				redirectAttributes.addFlashAttribute("msg", "很抱歉... "+cartItem.getProduct().getName()+" 庫存不足!");
				return "redirect:/jump";
			}
			
			OrderItem orderItem = new OrderItem();

			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
//			orderItem.setGame_id(cartItem.getProduct().getGame_id()); //test
			orderItem.setOrder(order);

			orderItemSet.add(orderItem);
		}
		order.setOrderItems(orderItemSet); // 將購物項目放進訂單中

//		cart.clear(); //購物車清空

		service.insertOrder(order); // 寫入資料庫

		session.setAttribute("order", order);
		session.setAttribute("orderItems", order.getOrderItems());

		Member mem = new Member();
		mem.setAccount("sandy60108@yahoo.com.tw");
		mem.setPassword("a14789632");
		mem.setUsername("andy");
		model.addAttribute("Member", mem);

		return "redirect:/showOrder";
	}

	@RequestMapping("/showOrder")
	public String showOrder(Model model, HttpSession session) {
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

		List<Orders> list = service.showOrder(member.getMember_id());
		model.addAttribute("orders", list);

		Member mem = new Member();
		mem.setAccount("sandy60108@yahoo.com.tw");
		mem.setPassword("a14789632");
		mem.setUsername("andy");
		model.addAttribute("Member", mem);

		List<Product> listP = serviceP.getAllProducts();
		session.setAttribute("products", listP);

		return "showOrder";
	}

	@RequestMapping("/showOrderDetail")
	public String getOrderById(@RequestParam("order_id") Integer order_id, Model model, HttpSession session) {
		Orders order = service.getOrderById(order_id);
		model.addAttribute("order", order);

		Member mem = new Member();
		mem.setAccount("sandy60108@yahoo.com.tw");
		mem.setPassword("a14789632");
		mem.setUsername("andy");
		model.addAttribute("Member", mem);

		return "showOrderDetail";
	}

}
