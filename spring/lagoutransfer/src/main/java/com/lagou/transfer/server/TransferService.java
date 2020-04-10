package com.lagou.transfer.server;

public interface TransferService {
    void transfer(String from,String to,int money) throws Exception;
}
