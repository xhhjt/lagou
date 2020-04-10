package com.lagou.transfer.beanfactory;

public class IOCContext {
    private static BeanFactory beanFactory;

    public static BeanFactory getBeanFactory() {
        if (beanFactory == null) {
            synchronized (IOCContext.class) {
                if (beanFactory == null) {
                    beanFactory = new AnnoBeanFactory();
                }
            }
        }
        return beanFactory;
    }
}
