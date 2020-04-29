package com.code.demo.backend.connect;

import com.code.demo.backend.utils.PagingResultSql;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 * 支持手写sql
 * 注意：能用jpa解决的请不要用这个类！！！！！！
 */
public class ConnectInfo {
    //    public static final String MYSQL_BUSINESS = "mysql-business";
//
//    public static final String ORACLE_PEAKE = "oracle-peake";
    // mysql
    @Value("${spring.datasource.url}")
    private String mysql_business_url;

    @Value("${spring.datasource.username}")
    private String mysql_business_username;

    @Value("${spring.datasource.password}")
    private String mysql_business_password;

//    @Value("${spring.datasource.driverClassName}")
//    private String mysql_business_driverclassname;


//    public DataSource getDataSource(String sourceType) {
//        DataSource dataSource = new DataSource();
//        if (MYSQL_BUSINESS.equals(sourceType)) {
//            dataSource.setDriverClassName(mysql_business_driverclassname);
//            dataSource.setUrl(mysql_business_url);
//            dataSource.setUsername(mysql_business_username);
//            dataSource.setPassword(mysql_business_password);
//        } else if (ORACLE_PEAKE.equals(sourceType)) {
//            dataSource.setDriverClassName(oracle_peake_driverclassname);
//            dataSource.setUrl(oracle_peake_url);
//            dataSource.setUsername(oracle_peake_username);
//            dataSource.setPassword(oracle_peake_password);
//        }
//        return dataSource;
//    }

    public DataSource getDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(mysql_business_url);
        dataSource.setUsername(mysql_business_username);
        dataSource.setPassword(mysql_business_password);
        return dataSource;
    }

    // 不支持条件查询 请自己拼好sql后执行
    public <T> PagingResultSql<T> getPageResult(String sql, Class<T> clazz, PageRequest pageRequest) {
        int pageNumber = pageRequest.getPageNumber();
        int pageSize = pageRequest.getPageSize();
        int begin = pageNumber * pageSize;
        String s1 = sql.substring(sql.indexOf("from"));
        String sqlCount = "select count(1) " + s1;
        int count = this.queryForOne(sqlCount, Integer.class);
        sql = sql + " limit " + begin + "," + pageSize;
        List<T> list = this.getResultDataList(sql, clazz);
        PagingResultSql pagingResult = new PagingResultSql(list, count);
        return pagingResult;
    }

    public <T> List<T> getResultDataList(String sql, Class<T> clazz) {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(clazz);
            List<T> list = jdbcTemplate.query(sql, rowMapper);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (dataSource != null)
                dataSource.close();
        }
    }

    public <T> T getResultData(String sql, Class<T> clazz) {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(clazz);
            T data = jdbcTemplate.queryForObject(sql, rowMapper);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (dataSource != null)
                dataSource.close();
        }
    }


    public <T> T queryForOne(String sql, Class<T> clazz) {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            //        调用方法获得记录数
            T data = jdbcTemplate.queryForObject(sql, clazz);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (dataSource != null)
                dataSource.close();
        }
    }


    public int batchUpdate(String sourceType, String sql, List params) {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.batchUpdate(sql, params);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (dataSource != null)
                dataSource.close();
        }
    }

    public int batchUpdate(String sourceType, String sql) {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.batchUpdate(sql);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (dataSource != null)
                dataSource.close();
        }
    }

    public int update(String sourceType, String sql, Object[] params) {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql, params);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (dataSource != null)
                dataSource.close();
        }
    }

    /**
     * 执行无参数的存储过程
     *
     * @param sql
     */
    public int executeFunction(String sourceType, String sql) {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate;
        try {
            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.execute(sql);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (dataSource != null)
                dataSource.close();
        }
    }
}
