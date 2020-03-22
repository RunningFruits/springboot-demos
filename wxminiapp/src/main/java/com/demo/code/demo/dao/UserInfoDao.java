package com.demo.code.demo.dao;

import com.demo.code.demo.bean.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao extends CrudRepository<UserInfo, Long> {


    @Query(value = "select * from user_info where openid=:openid limit 1", nativeQuery = true)
    UserInfo findByOpenid(String openid);
}
