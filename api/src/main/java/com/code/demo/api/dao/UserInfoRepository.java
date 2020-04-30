package com.code.demo.api.dao;

import com.code.demo.api.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo,Long>{

    public UserInfo findByUsernameAndPassword(String username, String password);

}
