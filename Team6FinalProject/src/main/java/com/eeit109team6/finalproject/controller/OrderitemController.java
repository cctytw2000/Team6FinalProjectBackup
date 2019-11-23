package com.eeit109team6.finalproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eeit109team6.finalproject.model.Member;
import com.eeit109team6.finalproject.model.Orders;
import com.eeit109team6.finalproject.service.IMemberService;
import com.eeit109team6.finalproject.service.OrderService;
import com.eeit109team6.finalproject.service.OrderitemService;

@Controller
public class OrderitemController {

	OrderitemService observice;

	@Autowired
	public void setService(OrderitemService service) {
		this.observice = service;
	}

	IMemberService mservice;

	@Autowired
	public void setMservice(IMemberService mservice) {
		this.mservice = mservice;
	}

	OrderService oservice;

	@Autowired
	public void setOservice(OrderService oservice) {
		this.oservice = oservice;
	}

	// showOrdersBack.jsp測試
	public String showOrdersBack(Model model) {
		model.addAttribute("orders", observice.showOrders());
		model.addAttribute("members", mservice.findAll());
		return "showOrdersBack";
	}

	// 訂單後台-->ordersBack.jsp
	@RequestMapping("/ordersBack")
	public String ordersBack(Model model) {
		System.out.println("/ordersBack");
		model.addAttribute("Orders", oservice.findAll());
		return "ordersBack";
	}

	// 查詢訂單細項
	@RequestMapping(value = "/order")
	public String orderBack(@RequestParam("order_id") Integer order_id, Model model) {
		System.out.println("order_id" + order_id);
		model.addAttribute("order", oservice.getOrderById(order_id));
		return "orderBackDetail";
	}

	// 查詢訂單細項json
	@RequestMapping(value = "/orderDeail/{order_id}.json")
	public void orderDeail(@PathVariable("order_id") Integer order_id, Model model) {
		model.addAttribute("orderDeail", observice.getOrderItemsById(order_id));
	}

	// 取消訂單
	@RequestMapping(value = "/orderBack/delete", method = RequestMethod.POST)
	public String deleteorder(@RequestParam("order_id") Integer order_id, Model model) {
		System.out.println("delete");
		oservice.deleteOrderById(order_id);
		model.addAttribute("Orders", oservice.findAll());
		return "redirect:/ordersBack";
	}

	// 查詢個人訂單json
	@RequestMapping(value = "/memberOrder/{member_id}.json")
	public void memberOrder(@PathVariable("member_id") Integer member_id, Model model) {
		model.addAttribute("Orders", oservice.showOrder(member_id));
	}

	// 個人訂單數量
	@RequestMapping(value = "/memberOrdercount")
	public void memberOrdercount(Model model) {
		List<Mocount> list = new ArrayList<>();
		List<Member> mlist = mservice.findAll();
		for (Member m : mlist) {
			List<Orders> olist = oservice.showOrder(m.getMember_id());
			Mocount mo = new Mocount();
			mo.setId(m.getMember_id());
			mo.setName(m.getUsername());
			mo.setCount(olist.size());
			list.add(mo);
		}
		model.addAttribute("countlist", list);
	}

	class Mocount {
		private Integer id;
		private String name;
		private Integer count;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

	}

	// 銷售金額
	@RequestMapping("/dailySales")
	public void dailySales(Model model) {
		System.out.println("/dailySales");
		model.addAttribute("sales", oservice.dailySalescount());
	}

	// (已或未)付款
	@RequestMapping(value = "/money", method = RequestMethod.POST)
	public void mony(@RequestParam("state") Integer state, Model model) {
		System.out.println("/money");
		model.addAttribute("Orders", oservice.findAll(state));
	}

	// (已或未)付款 會員
	@RequestMapping(value = "/moneymember", method = RequestMethod.POST)
	public void monymember(@RequestParam("member_id") Integer member_id, @RequestParam("state") Integer state,
			Model model) {
		System.out.println("/monymember");
		model.addAttribute("Orders", oservice.showOrder(member_id, state));
	}

	// 關鍵字會員
	@RequestMapping(value = "/memberkeyword", method = RequestMethod.POST)
	public void memberkey(@RequestParam("keyWord") String keyWord, Model model) {
		System.out.println("/memberkeyword");
		model.addAttribute("members", oservice.getMemberByKeyWord(keyWord));
	}

}
