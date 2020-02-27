package com.hosiang.dev.controller;

import com.hosiang.dev.bean.UserInfo;
import com.hosiang.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Blog：http://www.hosiang.cn
 *
 * @author Howe Hsiang
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/list")
	public String userList() {
		return "user/userList";
	}

	@GetMapping("/collect")
	public String userCollect() {
		return "user/userCollect";
	}

	/**
	 * 获取全部
	 *
	 * @return
	 */
	@PostMapping("/findAll")
	public @ResponseBody Object findAll() {
		return userService.findAll();
	}

	/**
	 * 持久化
	 * 
	 * @param userInfo
	 * @return
	 */
	@PostMapping("/save")
	public @ResponseBody Map<String, Object> save(@RequestBody UserInfo userInfo) {
		Map<String, Object> result = new HashMap<>();
		try {
			userService.save(userInfo);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		return result;
	}

}
