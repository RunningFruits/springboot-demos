package com.hosiang.dev.service;

import java.util.List;

import com.hosiang.dev.bean.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	/**
	 * 获取全部
	 * 
	 * @return
	 */
    List<User> findAll();

	/**
	 * 持久化
	 * 
	 * @param user
	 * @return
	 */
    void save(User user) throws Exception;
}
