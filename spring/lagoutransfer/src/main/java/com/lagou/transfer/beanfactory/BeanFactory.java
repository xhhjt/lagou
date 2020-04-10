package com.lagou.transfer.beanfactory;

public interface BeanFactory {
    <T> Object getBean(T t);

    <T> Object getBean(String name);

    public void getBeanFactory(Class clazz) throws IllegalAccessException, InstantiationException;
}
