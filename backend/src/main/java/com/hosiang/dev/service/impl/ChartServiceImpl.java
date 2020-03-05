package com.hosiang.dev.service.impl;

import com.hosiang.dev.dao.OrderRepository;
import com.hosiang.dev.dao.UserRepository;
import com.hosiang.dev.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Map<String, Object>> getCompanyOrderTotal(int chartTimeType, String cId) {
        if (chartTimeType == 1) {
            return orderRepository.getCompanyOrderTotalToday(cId);
        } else if (chartTimeType == 2) {
            return orderRepository.getCompanyOrderTotalWeekly(cId);
        } else if (chartTimeType == 3) {
            return orderRepository.getCompanyOrderTotalMonthly(cId);
        }
        return orderRepository.getCompanyOrderTotalToday(cId);
    }

    @Override
    public List<Map<String, Object>> getCompanyOrderMoney(int chartTimeType, String cId) {
        if (chartTimeType == 1) {
            return orderRepository.getCompanyOrderMoneyToday(cId);
        } else if (chartTimeType == 2) {
            return orderRepository.getCompanyOrderMoneyWeekly(cId);
        } else if (chartTimeType == 3) {
            return orderRepository.getCompanyOrderMoneyMonthly(cId);
        }
        return orderRepository.getCompanyOrderMoneyToday(cId);
    }

    @Override
    public List<Map<String, Object>> getUserRegisterMonthly() {
        return userRepository.getUserRegisterMonthly();
    }
}
