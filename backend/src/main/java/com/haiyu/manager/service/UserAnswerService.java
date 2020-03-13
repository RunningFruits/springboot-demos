package com.haiyu.manager.service;

import com.haiyu.manager.dto.UserAnswerDTO;
import com.haiyu.manager.response.PageDataResult;

import java.util.List;
import java.util.Map;

public interface UserAnswerService {

    List<UserAnswerDTO> list();

    PageDataResult getList(Integer pageNum, Integer pageSize);

    Map<String, Object> delById(Integer id);
}
