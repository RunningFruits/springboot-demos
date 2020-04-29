package com.code.demo.backend.api.code.service;


import com.code.demo.backend.api.code.entity.MainControl;
import com.code.demo.backend.api.code.repository.MainControlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MainControlService {

    @Autowired
    private MainControlRepository dao;

    public Object report(MainControl mainControl) {
        return dao.save(mainControl);
    }
}
