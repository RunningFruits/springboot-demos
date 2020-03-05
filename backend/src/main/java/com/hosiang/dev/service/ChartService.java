package com.hosiang.dev.service;

import java.util.List;
import java.util.Map;

public interface ChartService {


    //订单数量 start
    List<Map<String, Object>> getCompanyOrderTotal(int chartTimeType, String cId);
    //订单数量 end

    //订单金额 start
    List<Map<String, Object>> getCompanyOrderMoney(int chartTimeType, String cId);
    //订单金额 end


    List<Map<String, Object>>  getUserRegisterMonthly();

}
