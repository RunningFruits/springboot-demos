package com.firealarm.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FireAlarmServer {

    private final int PORT = 5566;
    private ExecutorService exec = Executors.newCachedThreadPool();
    protected ServerSocket serverSocket;

    public void acceptConnections() {
        try {
            ServerSocket server = new ServerSocket(PORT);
            Socket socket = null;
            System.out.println("服务器在" + PORT + "监听。。。");
            while (true) {
                socket = server.accept();
                System.out.println("Socket连接已建立");
                handleConnection(socket);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 将socket对象传递给PooledConnectionHandler.processRequest();
     *
     * @param connectiontohandle Socket对象
     */
    public void handleConnection(Socket connectiontohandle) {
        PooledConnectionHandler.processRequest(connectiontohandle);
    }

    /**
     * 新建一个处理数据的线程
     */
    public void setHandlers() {
        exec.execute(new PooledConnectionHandler());
    }



}
