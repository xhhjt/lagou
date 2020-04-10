package com.lagou.transfer.listener;

import com.lagou.transfer.anno.AnnoConfig;
import com.lagou.transfer.beanfactory.AnnoBeanFactory;
import com.lagou.transfer.beanfactory.IOCContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.annotation.Annotation;

public class ContextLoaderListener implements ServletContextListener {
    private static final String CONFIGCLASS = "configClass";

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("contextInitialized");
        String parameter = servletContextEvent.getServletContext().getInitParameter(CONFIGCLASS);
        try {
            Class<?> forName = Class.forName(parameter);
            IOCContext.getBeanFactory().getBeanFactory(forName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroyed");
    }
}
