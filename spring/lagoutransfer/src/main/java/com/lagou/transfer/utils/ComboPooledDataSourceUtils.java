package com.lagou.transfer.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class ComboPooledDataSourceUtils {
    static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    static {
        try {
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/lagou");
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("1592260916");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return comboPooledDataSource;
    }
}
