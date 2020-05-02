package com.firealarm.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.firealarm.info.FireData;

public class MysqlDao implements Runnable {

    String driverName = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/firealarm";
    String user = "root";
    String password = "123456";
    String insql;

    @SuppressWarnings("unused")
    private static String sensor_address = "sensor_address";
    private Connection sqlconnection;
    private ResultSet result = null;
    private FireData information;

    /* 此线程的作用是将封装好数据插入到数据库
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        ConnectMysql();
        try {
            insql = "insert into firedata(sensor_id,temperature,smoke,warning,time) values(?,?,?,?,?)";
            PreparedStatement preparedStatement = sqlconnection.prepareStatement(insql);
            preparedStatement.setInt(1, information.getId());
            preparedStatement.setInt(2, information.getTemperature());
            preparedStatement.setInt(3, information.getSmoke());
            preparedStatement.setBoolean(4, information.getWarning());
            preparedStatement.setString(5, information.getTime());

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("数据插入成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MysqlDao(FireData information) {
        this.information = information;
    }

    public MysqlDao() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 建立到数据库的连接
     *
     * @return connection
     */
    public Connection ConnectMysql() {
        try {
            Class.forName(driverName);
            sqlconnection = (Connection) DriverManager.getConnection(url, user, password);
            if (!sqlconnection.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
            } else {
                System.out.println("Falled connecting to the Database!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlconnection;
    }

    /**
     * 在数据库中查询传感器节点地址
     *
     * @param address
     * @return sensor_id or 0 if can't query the address from database
     */
    public int getSensorIdFromDatabase(String address) {
        ConnectMysql();
        String querysql = "select * from sensoraddress";
        PreparedStatement ps;
        try {
            ps = sqlconnection.prepareStatement(querysql);
            ResultSet resultSet = ps.executeQuery(querysql);
            while (resultSet.next()) {
                if (resultSet.getString("sensor_address").equals(address)) {

                    return resultSet.getInt("sensor_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 向表sensoraddress表中插入新节点的网络地址
     *
     * @param address
     */
    public void insertSensorToDatabase(String address) {
        ConnectMysql();
        String insql = "insert into sensoraddress(sensor_address) values(?)";
        PreparedStatement ps;
        try {
            ps = sqlconnection.prepareStatement(insql);
            ps.setString(1, address);
            ps.execute();
            System.out.println("新增节点成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void CutConnection(Connection conn) throws SQLException {
        try {
            if (result != null)
                ;
            if (conn != null)
                ;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
