package com.lagou.edu.controller;

import com.lagou.edu.pojo.Account;
import com.lagou.edu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

import static com.lagou.edu.util.Constant.LOGINCOOKKEY;

@Controller
public class LoginController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "login")
    public String login(HttpServletRequest request, HttpServletResponse response,String name) throws Exception {
        if (name==null){
            return "login";
        }
        Cookie cookie = new Cookie(LOGINCOOKKEY, UUID.randomUUID().toString());
        cookie.setDomain("localhost");
        cookie.setMaxAge(60 * 60 * 12);
        response.addCookie(cookie);

//        return "list";
        return "redirect:/"+"account/list";
    }

    @RequestMapping(value = "tologin")
    public String tologin() {
        return "login";
    }
}
