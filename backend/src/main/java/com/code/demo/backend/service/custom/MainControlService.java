package com.code.demo.backend.service.custom;

import com.code.demo.backend.entity.custom.MainControl;
import com.code.demo.backend.repository.custom.MainControlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MainControlService {

    @Autowired
    private MainControlRepository dao;

    public Map<String,Object> findAll(Pageable pageable) {
        Page<MainControl> all = dao.findAll(pageable);

        Map<String,Object> result = new HashMap<>();
        result.put("totalElements", all.getTotalElements());
        result.put("content", all.getContent());

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code", true);
        resultMap.put("data", result);
        return resultMap;
    }


}
