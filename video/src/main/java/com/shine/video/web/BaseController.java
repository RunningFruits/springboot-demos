package com.shine.video.web;

import com.shine.video.redis.RedisUtil;
import com.shine.video.service.CollectService;
import com.shine.video.service.LoginService;
import com.shine.video.service.UserService;
import com.shine.video.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BaseController {

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
