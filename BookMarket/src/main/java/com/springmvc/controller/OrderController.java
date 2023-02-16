package com.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.service.OrderService;

/**
 * 실제로 작동하지 않고 웹 플로우가 주문 처리를 작동한다. 
 * @author jamesj
 *
 */

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order/ISBN1234/2")
	public String process() {
		orderService.confirmOrder("ISBN1234", 2);
		return "redirect:/books";
	}
	
}
