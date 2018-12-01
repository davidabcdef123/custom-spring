package com.david.spring2.framework.beans;

import com.david.spring2.framework.aop.AopConfig;
import com.david.spring2.framework.aop.AopProxy;
import com.david.spring2.framework.core.FactoryBean;

/**
 * Created by sc on 2018/11/28.
 * bean的包装类
 */

public class BeanWrapper extends FactoryBean {

    private AopProxy aopProxy=new AopProxy();

    private Object wrapperInstance;
    //原始的通过反射new出来，要把包装起来，存下来
    private Object originalInstance;

    //还会用到  观察者  模式
    //1、支持事件响应，会有一个监听
    private BeanPostProcessor postProcessor;

    public BeanWrapper(Object instance) {
        //从这里开始，我们要把动态的代码添加进来了
        this.wrapperInstance = aopProxy.getProxy(instance);
        this.originalInstance=instance;
    }

    public void setPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.postProcessor=postProcessor;
    }
    public BeanPostProcessor getPostProcessor(){
        return postProcessor;
    }

    public Object getWrappedInstance(){
        return this.wrapperInstance;
    }

    // 返回代理以后的Class
    // 可能会是这个 $Proxy0
    public Class<?> getWrappedClass(){
        return this.wrapperInstance.getClass();
    }

    public void setAopConfig(AopConfig aopConfig){
        aopProxy.setAopConfig(aopConfig);
    }

    public Object getOriginalInstance() {
        return originalInstance;
    }


}
