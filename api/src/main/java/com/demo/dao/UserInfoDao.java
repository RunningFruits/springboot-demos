package com.demo.dao;

import com.demo.bean.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao extends CrudRepository<UserInfo,Long>{

    public UserInfo findByUsernameAndPassword(String username, String password);
}
