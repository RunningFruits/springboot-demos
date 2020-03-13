package com.haiyu.manager.dao;

import com.haiyu.manager.dto.UserAnswerDTO;
import com.haiyu.manager.pojo.UserAnswer;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;


@Repository
public interface UserAnswerMapper extends MyMapper<UserAnswer> {

    List<UserAnswerDTO> getList();
}