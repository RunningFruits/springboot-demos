package com.demo.code.service;


import com.demo.code.entity.MainControl;
import com.demo.code.repository.MainControlRepository;
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
