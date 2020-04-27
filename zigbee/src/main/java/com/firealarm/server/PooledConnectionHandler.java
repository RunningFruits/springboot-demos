package com.firealarm.server;

import java.io.BufferedInputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PooledConnectionHandler implements Runnable {
    protected Socket connection; // connection是真正处理的socket
    protected static LinkedList<Socket> pool = new LinkedList<Socket>();
    private byte[] data = new byte[35];
    private ExecutorService exec = Executors.newCachedThreadPool();

    @Override
    public void run() {
        // TODO Auto-generated method stub

        while (true) {
            synchronized (pool) {
                while (pool.isEmpty()) {
                    try {
                        pool.wait();
                        // 对池上的wait的调用释放 锁，而wait接着就在自己返回之前再次取得该锁
                        // 在连接池上等待 ，一有连接就处理
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                connection = (Socket) pool.remove(0);
            }
            handleConnection();
        }
    }

    public PooledConnectionHandler() {
    }


    /**
     * 将把传入请求添加到池中 并告诉其他真正等待的对象 池里已有一些内容
     *
     * @param requestToHandle socket对象
     */
    public static void processRequest(Socket requestToHandle) {
        synchronized (pool) {
            pool.add(pool.size(), requestToHandle); // 把传入是socket 添加到尾端
            pool.notifyAll();
        }
    }


    /**
     * 获得连接的流，并使用他们，并在任务结束会清除他们
     */
    public void handleConnection() {
        int num;
        try {
            BufferedInputStream buff = new BufferedInputStream(
                    connection.getInputStream());
            while ((num = buff.read(data)) != -1) {
                //exec.execute(new Resolution(data));
                System.out.println(num);
                if (num == 32) {
                    exec.execute(new Resolution(data));
                    for (int i = 0; i < data.length; i++) {
                        System.out.print((char) data[i]);
                    }
                } else {
                    System.out.println("接受的数据异常");
                    System.out.println(data.length);
                    for (int i = 0; i < data.length; i++) {

                        System.out.print((char) data[i]);
                    }
                }
            }
            buff.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
