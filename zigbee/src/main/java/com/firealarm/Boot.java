package com.firealarm.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.firealarm.info.FireData;


public class Boot {

    public static void main(String[] args) {
        FireAlarmServer sever = new FireAlarmServer();
        sever.setHandlers();
        sever.acceptConnections();
    }

}
