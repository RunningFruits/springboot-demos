package com.hosiang.dev.controller;

import com.hosiang.dev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/list")
	public String orderList() {
		return "order/orderList";
	}

}
