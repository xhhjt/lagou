package com.lagou.transfer.beanfactory;

import com.lagou.transfer.anno.AnnoConfig;
import com.lagou.transfer.anno.Compent;
import com.lagou.transfer.anno.Service;
import com.lagou.transfer.anno.Transactional;
import com.lagou.transfer.aop.AopProxyFactory;
import com.lagou.transfer.utils.ClassUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

public class AnnoBeanFactory extends AbstractBeanFactory {

    public AnnoBeanFactory() {
    }

    public void getBeanFactory(Class clazz) throws IllegalAccessException, InstantiationException {

        Object obj = clazz.newInstance();
        Annotation[] annotations = obj.getClass().getAnnotations();

        String scanPath = "";
        for (Annotation annotation : annotations) {
            if (annotation instanceof com.lagou.transfer.anno.AnnoConfig) {
                AnnoConfig annoConfig = (AnnoConfig) annotation;
                scanPath = annoConfig.ScanPath();
            }
        }
        if (scanPath == null || scanPath.trim().equals("")) {
            throw new RuntimeException("AnnoConfig ScanPath can not null");
        }

        Set<Class<?>> classes = ClassUtil.getClassSet(scanPath);
        for (Class<?> aClass : classes) {
            try {


                IOCBean bean = initIOCBean(aClass);
                if (bean != null) {
                    if (bean.getClazz() != null) {
                        addEarly(bean.getClazz(), bean.getBean());
                    } else {
                        addEarly(bean.getKey(), bean.getBean());
                    }
                }
            } catch (Exception e) {
                System.out.println("yichang:" + aClass.getName());
            }
        }
        beanAttributeHandle();
    }


    private IOCBean initIOCBean(Class clazz) {
        try {


            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                IOCBean iocBean = null;
                if (annotation instanceof Service) {
                    Object bean = clazz.newInstance();
                    iocBean = new IOCBean();
                    Service service = (Service) annotation;
                    if (service.value() != null && !service.value().trim().equals("")) {
                        iocBean.setKey(service.value());
                    } else {
                        iocBean.setClazz(clazz);
                    }
                    iocBean.setBean(bean);

                    return iocBean;
                } else if (annotation instanceof Compent) {
                    Object bean = clazz.newInstance();
                    iocBean = new IOCBean();
                    Compent compent = (Compent) annotation;
                    if (compent.value() != null && !compent.value().trim().equals("")) {
                        iocBean.setKey(compent.value());
                    } else {
                        iocBean.setClazz(clazz);
                    }
                    iocBean.setBean(bean);

                    return iocBean;
                }
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




}
