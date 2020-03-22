package com.init.demo.service.system;


import com.init.demo.entity.system.SystemLog;
import com.init.demo.entity.system.SystemUser;
import com.init.demo.repository.SystemLogRepository;
import com.init.demo.utils.PagingResult;
import com.init.demo.utils.SessionUtil;
import com.init.demo.utils.TableQueryUtil;
import com.init.demo.utils.WebUtil;
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
