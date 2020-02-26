package com.demo.service;


import com.demo.bean.UserInfo;
import com.demo.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;

    public UserInfo selectByOpenId(String open_id) {
//        userInfoDao.findByUsernameAndPassword();
        return null;
    }

    public void updateById(UserInfo user) {
    }

    public Boolean insert(UserInfo insert_user) {
        return null;
    }

}
