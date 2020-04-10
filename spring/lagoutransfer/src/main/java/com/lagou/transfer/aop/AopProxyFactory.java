package com.lagou.transfer.aop;

import com.lagou.transfer.anno.Transactional;
import com.lagou.transfer.beanfactory.IOCContext;
import com.lagou.transfer.utils.TransactionManager;
import net.sf.cglib.proxy.Enhancer;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

public class AopProxyFactory {

    public static Object CreateAOPOBject(final Object object) {
        //JDK动态代理
        if (object.getClass().isInterface()) {
            Proxy.newProxyInstance(AopProxyFactory.class.getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return AopProxyFactory.invoke(method, args, object);
                }
            });
        } else {
            Enhancer e = new Enhancer();
            e.setClassLoader(object.getClass().getClassLoader());
            //2.3设置代理对象父类类型
            e.setSuperclass(object.getClass());
            //2.4设置回调函数
            e.setCallback(new net.sf.cglib.proxy.InvocationHandler() {
                public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                    return AopProxyFactory.invoke(method, objects, object);
                }
            });
            //2.5创建代理对象
            return e.create();
        }

        return null;
    }

    private static Object invoke(Method method, Object[] args, Object object) throws Exception {
        for (Annotation annotation : method.getAnnotations()) {
            if (annotation instanceof Transactional) {
                TransactionManager transactionManager = (TransactionManager) IOCContext.getBeanFactory().getBean("TransactionManager");

                Object obj = null;
                try {
                    transactionManager.beginTransaction();
                    obj = method.invoke(object, args);
                    transactionManager.commit();
                } catch (Exception e) {
                    transactionManager.rollback();
                    throw e;
                }
                return obj;

            }
        }
        return method.invoke(object, args);

    }

}
