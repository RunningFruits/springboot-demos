package com.haiyu.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haiyu.manager.dao.UserAnswerMapper;
import com.haiyu.manager.dto.PermissionDTO;
import com.haiyu.manager.dto.UserAnswerDTO;
import com.haiyu.manager.pojo.UserAnswer;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.UserAnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserAnswerMapper userAnswerMapper;

    public List<UserAnswerDTO> list() {
        return null;
    }

    @Override
    public PageDataResult getList(Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<UserAnswerDTO> answers = userAnswerMapper.getList();

        PageHelper.startPage(pageNum, pageSize);

        if (answers.size() != 0) {
            PageInfo<UserAnswerDTO> pageInfo = new PageInfo<>(answers);
            pageDataResult.setList(answers);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }
        return pageDataResult;
    }

    @Override
    public Map<String, Object> delById(Integer id) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = userAnswerMapper.deleteByPrimaryKey(id);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "删除失败");
                logger.error("删除失败");
                return data;
            }
            data.put("code", 1);
            data.put("msg", "删除成功");
            logger.info("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除异常！", e);
        }
        return data;
    }

}
