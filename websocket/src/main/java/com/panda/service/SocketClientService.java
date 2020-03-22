package com.panda.service;

public interface SocketClientService {

	/**
	 * 开始一个socket客户端
	 *
	 * @param userId 用户id
	 */
	void startOneClient(String userId);

	void closeOneClient(String userId);
}
