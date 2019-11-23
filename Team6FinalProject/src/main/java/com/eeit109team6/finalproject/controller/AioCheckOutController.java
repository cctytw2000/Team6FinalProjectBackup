package com.eeit109team6.finalproject.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eeit109team6.finalproject.model.OrderItem;
import com.eeit109team6.finalproject.model.Orders;
import com.eeit109team6.finalproject.model.Product;
import com.eeit109team6.finalproject.service.OrderService;
import com.eeit109team6.finalproject.service.ProductService;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
import ecpay.payment.integration.domain.InvoiceObj;
import ecpay.payment.integration.exception.EcpayException;

@Controller
public class AioCheckOutController {

	OrderService service;

	@Autowired
	public void setService(OrderService service) {
		this.service = service;
	}

	ProductService serviceP;

	@Autowired
	public void setServiceP(ProductService serviceP) {
		this.serviceP = serviceP;
	}

	AllInOne all;

	// 信用卡一次付清
	@RequestMapping(value = "/aioCheckOutOneTime", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String aioCheckOutDevide(@RequestParam("order_id") Integer order_id, HttpSession session) {
		System.out.println("進來了");
		Orders order = service.getOrderById(order_id);
		session.setAttribute("order", order);
		all = new AllInOne("");

		AioCheckOutOneTime aio = new AioCheckOutOneTime();

//		QueryTradeObj qto = new QueryTradeObj();
//		qto.set
//		String info = all.queryTrade(qto);

		InvoiceObj invoice = new InvoiceObj();
		// 模擬不開發票
		invoice = null;
		// 廠商系統自行產生
		String s = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
		aio.setMerchantTradeNo(s);

		// 廠商可自行決定交易時間
		String time = order.getOrdertime().replace(".0", "").replace("-", "/");
		aio.setMerchantTradeDate(time);

		// 從廠商DB撈出的商品資訊
		order.getOrderItems();
		String items = "";
		int i = order.getOrderItems().size();
		for (OrderItem item : order.getOrderItems()) {
			items += item.getProduct().getName();
			if (i > 1) {
				items += "#";
				i--;
			}
		}
		System.out.println("games:" + items);
		System.out.println("time:" + time);
		System.out.println("pay:" + s);
		System.out.println("order:" + order.getOrder_id().toString());
		aio.setItemName(items);
		aio.setTotalAmount(order.getTotal().toString());
		aio.setTradeDesc("item desc");
		// 廠商可自行決定是否延遲撥款
		aio.setHoldTradeAMT("0");
		// 後端設定付款完成通知回傳網址
		aio.setReturnURL("http://localhost:8080/Team6FinalProject/transaction");
		aio.setOrderResultURL("http://localhost:8080/Team6FinalProject/transaction");
		try {
			String html = all.aioCheckOut(aio, invoice);

			System.out.println("html === " + html);
			return html;
		} catch (EcpayException e) {
			throw new Error(e.getNewExceptionMessage());
		}

	}

	@RequestMapping(value = "/transaction")
	public String transaction(HttpSession session, Model model) {
		Orders order = (Orders) session.getAttribute("order");
		Boolean state = service.updateOrderstate(order.getOrder_id());
		if (state) {
			for (OrderItem oitem : order.getOrderItems()) {
				System.out.println(oitem.getProduct().getName());
				Product p = serviceP.getProductById(oitem.getProduct().getGame_id());
				p.setStock(p.getStock() - oitem.getCount());
				serviceP.updateProductById(p);
			}
		}
		return "purchaseSuccess";
	}

}
