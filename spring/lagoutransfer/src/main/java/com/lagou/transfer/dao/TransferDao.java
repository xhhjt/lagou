package com.lagou.transfer.dao;

import com.lagou.transfer.pojo.Account;


public interface TransferDao {
    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccountByCardNo(Account account) throws Exception;
}
