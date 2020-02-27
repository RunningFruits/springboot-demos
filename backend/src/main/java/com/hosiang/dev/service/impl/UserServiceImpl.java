package com.hosiang.dev.service.impl;

import com.hosiang.dev.bean.UserInfo;
import com.hosiang.dev.repos.UserRepository;
import com.hosiang.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = false, rollbackFor = Throwable.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 获取全部
	 * 
	 * @return
	 */
	public List<UserInfo> findAll() {
		return userRepository.findAll();
	}

	/**
	 * 持久化
	 * 
	 * @param userInfo
	 * @return
	 */
	public void save(UserInfo userInfo) {
		// 持久化
		userRepository.save(userInfo);
		// 如果持久化成功，就抛出异常。如果开启事务，那么刚才持久化的数据应回滚
		/*if (!StringUtils.isNullOrEmpty(entity.id))
			throw new Exception("测试无法持久化第一条数据的异常");*/
	}
}
