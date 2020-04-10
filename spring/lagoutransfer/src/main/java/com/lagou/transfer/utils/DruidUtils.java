package com.lagou.transfer.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 应癫
 */
public class DruidUtils {

    private DruidUtils(){
    }

    private static DruidDataSource druidDataSource = new DruidDataSource();


    static {
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/lagou");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("1592260916");

    }

    public static DruidDataSource getInstance() throws SQLException {

        return druidDataSource;
    }

}
