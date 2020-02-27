package com.hosiang.dev.service;

import java.util.List;

import com.hosiang.dev.bean.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	/**
	 * 获取全部
	 * 
	 * @return
	 */
    List<UserInfo> findAll();

	/**
	 * 持久化
	 * 
	 * @param userInfo
	 * @return
	 */
    void save(UserInfo userInfo) throws Exception;
}
