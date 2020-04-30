package com.code.demo.websocket.service;


import com.code.demo.websocket.entity.MainControl;
import com.code.demo.websocket.repository.MainControlRepository;
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
