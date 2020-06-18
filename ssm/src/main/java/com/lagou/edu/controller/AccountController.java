package com.lagou.edu.controller;

import com.lagou.edu.pojo.Account;
import com.lagou.edu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/account")
public class AccountController {

    /**
     * Spring容器和SpringMVC容器是有层次的（父子容器）
     * Spring容器：service对象+dao对象
     * SpringMVC容器：controller对象，，，，可以引用到Spring容器中的对象
     */


    @Autowired
    private AccountService accountService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Account> queryAll() throws Exception {
        return accountService.queryAccountList();
    }

    @RequestMapping(value = "list")
    public ModelAndView list(ModelAndView model) throws Exception {
        List<Account> accounts = accountService.queryAccountList();
        model.addObject("list", accounts);
        model.setViewName("list");
        return model;
    }

    @RequestMapping(value = "add")
    public String add(String name,
                      int money,
                      String cardNo) {
        Account account = new Account();
        if (cardNo == null || cardNo.equals("")) {
            account.setCardNo(UUID.randomUUID().toString());
            account.setMoney(money);
            account.setName(name);
            accountService.create(account);
        } else {
            account.setCardNo(cardNo);
            account.setName(name);
            account.setMoney(money);
            accountService.update(account);
        }
        return "redirect:/" + "account/list";
    }

    @RequestMapping(value = "edit")
    public String edit(@RequestParam(value = "cardNo",required = false) String cardNo) {
        return "add";
    }

    @RequestMapping(value = "delete")
    public String delete(@RequestParam(value = "cardNo") String cardNo) {
        if (cardNo != null) {
            accountService.delete(cardNo);
        }
        return "redirect:/" + "account/list";
    }
}
