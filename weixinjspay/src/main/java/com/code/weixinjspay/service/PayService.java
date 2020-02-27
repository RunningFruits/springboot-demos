package com.code.weixinjspay.service;

import com.code.weixinjspay.model.AuthToken;

public interface PayService {

    /**
     * 统一下单接口
     *
     * @param authToken  授权token
     * @param remoteAddr 请求主机ip
     * @return prepayId 预支付id
     */
    String unifiedOrder(AuthToken authToken, String remoteAddr);

    /**
     * 申请退款接口
     */
    String refund();
}
