package com.lagou.transfer.beanfactory;

import com.lagou.transfer.anno.Autowired;
import com.lagou.transfer.anno.Transactional;
import com.lagou.transfer.aop.AopProxyFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractBeanFactory implements BeanFactory {
    Map<String, Object> beanMap = new HashMap<String, Object>();
    Map<String, Object> beanEarlyMap = new HashMap<String, Object>();

    public <T> T getBean(T t) {
        String key = getKey(t);
        return (T) beanMap.get(key);
    }

    public <T> T getBean(String name) {
        return (T) beanMap.get(name);
    }

    public void getBeanFactory(Class clazz) throws IllegalAccessException, InstantiationException {

    }
    public Object initMethed(Object bean) throws IllegalAccessException, InstantiationException {
        boolean isTX = false;
        Object newbean=null;

        for (Method method : bean.getClass().getMethods()) {
            for (Annotation annotation : method.getAnnotations()) {
                if (annotation instanceof Transactional && !isTX) {
                    newbean=AopProxyFactory.CreateAOPOBject(bean);
                    isTX = true;
                }
            }
        }
        return null;
    }

    protected void addBean(String key, Object bean) {
        beanMap.put(key, bean);
    }

    protected <T> void addEarly(T key, Object bean) {
        String s = getKey(key);

        beanEarlyMap.put(s, bean);
    }

    protected void addEarly(String key, Object bean) {
        beanEarlyMap.put(key, bean);
    }

    protected void beanAttributeHandle() throws IllegalAccessException, InstantiationException {
        for (Map.Entry<String, Object> entry : beanEarlyMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
//            List<Field> fields = Arrays.asList(value.getClass().getDeclaredFields());
//            fields.addAll(Arrays.asList(value.getClass().getSuperclass().getDeclaredFields()));
            setValue(value, value.getClass());
            Object newBean=initMethed(value);
            beanMap.put(key, newBean==null?value:newBean);
        }
    }

    private void setValue(Object value, Class clazz) throws IllegalAccessException {
        if (clazz.getDeclaredFields().length == 0) return;
        for (Field field : clazz.getDeclaredFields()) {
            Annotation[] annotations = field.getAnnotations();
            if (annotations != null && annotations.length > 0) {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Autowired) {
                        Autowired autowired = (Autowired) annotation;
                        String beanKey = "";
                        if (autowired.Value() != null && !autowired.Value().trim().equals("")) {
                            beanKey = autowired.Value();
                        } else {
                            beanKey = getKey(field.getType());
                        }
                        Object object = beanEarlyMap.get(beanKey);
                        field.setAccessible(true);
                        field.set(value, object);
                    }
                }
            }
        }
        setValue(value, clazz.getSuperclass());
    }

    private <T> String getKey(T clazz) {
        String key = "";
        if (clazz instanceof Class) {
            key = ((Class) clazz).getName().toLowerCase();
        } else {
            key = (String) clazz;
        }
        return key;
    }
}
