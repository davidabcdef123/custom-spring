package com.david.spring2.framework.context;

import com.david.spring2.framework.beans.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sc on 2018/11/28.
 */
public class DefaultListableBeanFactory extends AbstractApplicationContext {

    //beanDefinitionMap用来保存配置信息
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    protected void onRefresh(){

    }

    @Override
    protected void refreshBeanFactory() {

    }
}
