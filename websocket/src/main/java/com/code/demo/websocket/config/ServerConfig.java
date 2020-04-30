package com.code.demo.websocket.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class ServerConfig {

    @Value("${server.port}")
    private int serverPort;

    @Value("${server.servlet.context-path}")
    private String serverServletContextPath;

    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        if (StringUtils.isEmpty(serverServletContextPath)) {
            serverServletContextPath = "";
        }
        serverServletContextPath = serverServletContextPath.trim();

        if (serverServletContextPath.equals("/")) {
            return "http://" + address.getHostAddress() + ":" + this.serverPort;
        }
        if (serverServletContextPath.startsWith("/")) {
            return "http://" + address.getHostAddress() + ":" + this.serverPort + serverServletContextPath.trim();
        } else {
            return "http://" + address.getHostAddress() + ":" + this.serverPort + "/" + serverServletContextPath.trim();
        }
    }

}

