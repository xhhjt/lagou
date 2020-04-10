package com.lagou.transfer.dao.impl;

import com.lagou.transfer.anno.Autowired;
import com.lagou.transfer.anno.Service;
import com.lagou.transfer.dao.TransferDao;
import com.lagou.transfer.pojo.Account;
import com.lagou.transfer.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service(value = "transferDao")
public class TransferDaoImpl implements TransferDao {

    @Autowired
    ConnectionUtils connectionUtils;


    public Account queryAccountByCardNo(String cardNo) throws Exception {
        //从连接池获取连接
        // Connection con = DruidUtils.getInstance().getConnection();
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "select * from account where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, cardNo);
        ResultSet resultSet = preparedStatement.executeQuery();

        Account account = new Account();
        while (resultSet.next()) {
            account.setCardNo(resultSet.getString("cardNo"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getInt("money"));
        }

        resultSet.close();
        preparedStatement.close();
        //con.close();

        return account;
    }


    public int updateAccountByCardNo(Account account) throws Exception {

        // 从连接池获取连接
        // 改造为：从当前线程当中获取绑定的connection连接
        //Connection con = DruidUtils.getInstance().getConnection();
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "update account set money=? where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, account.getMoney());
        preparedStatement.setString(2, account.getCardNo());
        int i = preparedStatement.executeUpdate();

        preparedStatement.close();
        //con.close();
        return i;
    }
}
