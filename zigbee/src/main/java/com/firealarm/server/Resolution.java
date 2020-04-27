package com.firealarm.server;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.firealarm.info.FireData;

public class Resolution implements Runnable {

    private byte[] data = new byte[1000];
    public FireData information = new FireData();
    ExecutorService exec = Executors.newCachedThreadPool();

    /*
     * 此线程的作用是处理数据，将数据封装成FireData对象 传递给下一个线程
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        int sensor_id;
        int temperature;
        int smoke;
        String address;
        Boolean warning;
        String time;
        MysqlDao mysqlDao = new MysqlDao();
        address = String.valueOf((char) data[3]) + String.valueOf((char) data[4]) + String.valueOf((char) data[5]) + String.valueOf((char) data[6]) + String.valueOf((char) data[7]) + String.valueOf((char) data[8]) + String.valueOf((char) data[9]) + String.valueOf((char) data[10]);
        sensor_id = mysqlDao.getSensorIdFromDatabase(address);
        if (sensor_id == 0) {
            mysqlDao.insertSensorToDatabase(address);
        }
        temperature = (int) (data[12] - '0') * 10 + (int) (data[13] - '0');
        smoke = (int) (data[24] - '0') * 100 + (int) (data[25] - '0') * 10 + (int) (data[26] - '0');

        //sensor_id 不由传感器发出，而是比对传感器地址，从数据库中得到sensor_id,地址不存在就加入数据库
        //检测是否警戒值
        if (temperature >= 28 || smoke >= 150) {
            warning = true;
        } else {
            warning = false;
        }
        Date now = new Date();
        DateFormat d = DateFormat.getInstance();
        time = d.format(now);
        information.setId(sensor_id);
        information.setTemperature(temperature);
        information.setSmoke(smoke);
        information.setWraning(warning);
        information.setTime(time);
        // 再次新起线程将封装好的对象存入数据库
        exec.execute(new MysqlDao(information));

        System.err.println("id " + address);
        System.out.println("sensor_id " + sensor_id);
        System.out.println("temperature " + temperature);
        System.out.println("somke " + smoke);
        System.out.println("warning " + warning);
        System.out.println("time " + time);
    }

    /**
     * @param data 从socket流中得到的字节数据
     */
    public Resolution(byte[] data) {
        this.data = data;
    }
}
