package com.lagou.transfer.utils;

import com.lagou.transfer.anno.Autowired;
import com.lagou.transfer.anno.Compent;

import java.sql.SQLException;

/**
 * @author 应癫
 * <p>
 * 事务管理器类：负责手动事务的开启、提交、回滚
 */
@Compent(value = "TransactionManager")
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;


    // 开启手动事务控制
    public void beginTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConn().setAutoCommit(false);
    }


    // 提交事务
    public void commit() throws SQLException {
        connectionUtils.getCurrentThreadConn().commit();
    }


    // 回滚事务
    public void rollback() throws SQLException {
        connectionUtils.getCurrentThreadConn().rollback();
    }
}
