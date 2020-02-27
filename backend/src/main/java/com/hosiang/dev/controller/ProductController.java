package com.hosiang.dev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Blog：http://www.hosiang.cn
 *
 * @author Howe Hsiang
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	@GetMapping("/list")
	public String productList() {
		return "product/productList";
	}

}
