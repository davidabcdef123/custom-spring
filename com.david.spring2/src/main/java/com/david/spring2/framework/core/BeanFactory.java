package com.david.spring2.framework.core;

/**
 * Created by sc on 2018/11/25.
 */
public interface BeanFactory {

    /**
    * Author: sc
    * Since: 2018/11/25
    * Describe:根据beanName从ioc容器获得一个实例bean
    * Update: [变更日期YYYY-MM-DD][更改人姓名][变更描述]
    */
    Object getBean(String beanName);
}
