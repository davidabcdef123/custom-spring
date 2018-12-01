package com.david.spring2.framework.beans;

/**
 * Created by sc on 2018/11/25.
 */
//用做事件监听的
public class BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }
    public Object postProcessAfterInitialization(Object bean, String beanName){
        return bean;
    }
}
