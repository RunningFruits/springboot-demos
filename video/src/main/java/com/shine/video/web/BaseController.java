package com.shine.video.web;

import com.shine.video.redis.RedisUtil;
import com.shine.video.service.CollectService;
import com.shine.video.service.LoginService;
import com.shine.video.service.UserService;
import com.shine.video.service.VideoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseController {

    protected static final Logger videoLogger = Logger.getLogger("video");

    //业务层
    @Autowired
    protected LoginService loginService;

    @Autowired
    protected CollectService collectService;

    @Autowired
    protected VideoService videoService;

    @Autowired
    protected UserService userService;

    //缓存
    @Autowired
    protected RedisUtil redisUtil;
}
