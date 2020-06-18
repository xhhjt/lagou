package com.lagou.edu.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lagou.edu.util.Constant.LOGINCOOKKEY;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isLogin = false;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(LOGINCOOKKEY)) {
                isLogin = true;
                break;
            }
        }
        if (request.getRequestURI().contains("tologin")
                ||request.getRequestURI().contains("login")) {
            isLogin = true;
        }
        if (!isLogin) {
            response.sendRedirect("tologin");
        }
        return isLogin;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
