package com.panda.utils.socket.handler;

public interface LoginHandler {

	/**
	 * client登陆的处理函数
	 *
	 * @param userId 用户id
	 *
	 * @return 是否验证通过
	 */
	boolean canLogin(String userId);
}
