package com.shine.video.service.impl;

import com.shine.video.dao.CollectMapper;
import com.shine.video.dao.UserMapper;
import com.shine.video.dao.VideoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 7le on 2017/5/17 0017.
 */
@Slf4j
public class BaseServiceImpl {

    @Autowired
    protected CollectMapper collectMapper;

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected VideoMapper videoMapper;
}
