package com.code.demo.miniapp.dao;

import com.code.demo.miniapp.bean.UserAnswer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswerDao extends CrudRepository<UserAnswer, Long> {

    @Query(value = "select * from user_answer where openid=:openid limit 1", nativeQuery = true)
    UserAnswer findByOpenid(@Param("openid") String openid);
}
