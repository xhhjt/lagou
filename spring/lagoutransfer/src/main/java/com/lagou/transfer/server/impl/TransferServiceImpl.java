package com.lagou.transfer.server.impl;

import com.lagou.transfer.anno.Autowired;
import com.lagou.transfer.anno.Service;
import com.lagou.transfer.anno.Transactional;
import com.lagou.transfer.dao.TransferDao;
import com.lagou.transfer.pojo.Account;
import com.lagou.transfer.server.TransferService;

@Service(value = "transferService")
public class TransferServiceImpl implements TransferService {
    @Autowired(Value = "transferDao")
    TransferDao transferDao;

    @Transactional
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {

        Account from = transferDao.queryAccountByCardNo(fromCardNo);
        Account to = transferDao.queryAccountByCardNo(toCardNo);

        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);
        int i = 1 / 0;
        transferDao.updateAccountByCardNo(to);
        transferDao.updateAccountByCardNo(from);
    }
}
