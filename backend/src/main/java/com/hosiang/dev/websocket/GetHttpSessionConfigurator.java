package com.hosiang.dev.websocket;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

/**
 * 获取HttpSession
 */
@Component
public class GetHttpSessionConfigurator extends Configurator {

    Logger logger =LoggerFactory.getLogger(GetHttpSessionConfigurator.class);

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // TODO Auto-generated method stub
    }

}
