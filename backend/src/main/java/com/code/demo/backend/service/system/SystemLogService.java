package com.code.demo.backend.service.system;


import com.code.demo.backend.entity.system.SystemLog;
import com.code.demo.backend.entity.system.SystemUser;
import com.code.demo.backend.repository.system.SystemLogRepository;
import com.code.demo.backend.utils.PagingResult;
import com.code.demo.backend.utils.SessionUtil;
import com.code.demo.backend.utils.TableQueryUtil;
import com.code.demo.backend.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SystemLogService {

    @Autowired
    private SystemLogRepository systemLogRepository;

    @Autowired
    private TableQueryUtil tableQueryUtil;

    @Autowired
    private SessionUtil sessionUtil;


    public PagingResult<SystemLog> findAllLog(PageRequest pageRequest, Map advQuery, String keyword) {
        return tableQueryUtil.findAll(advQuery, keyword, pageRequest, SystemLog.class, systemLogRepository);
    }

    // 手动保存log  params随便填，看得懂就行
    public void saveLog(String operation, String params) {
        SystemUser systemUser = sessionUtil.getCurrentUser();
        SystemLog log = new SystemLog();
        if(systemUser != null){
            log.setUserId(systemUser.getId());
            log.setUserName(systemUser.getUserName());
        }
        log.setOperation(operation);
        log.setMethod(WebUtil.getUrlAddr());
        log.setParams(params);
        log.setIp(WebUtil.getIpAddr());
        systemLogRepository.save(log);
    }
}
