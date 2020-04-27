package com.firealarm;


import com.firealarm.server.FireAlarmServer;

public class Boot {

    public static void main(String[] args) {
        FireAlarmServer sever = new FireAlarmServer();
        sever.setHandlers();
        sever.acceptConnections();
    }

}
