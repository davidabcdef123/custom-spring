package com.david.spring2.framework.context;

/**
 * Created by sc on 2018/11/28.
 */
public abstract class AbstractApplicationContext {

    //提供给子类重写
    protected void onRefresh(){
        // For subclasses: do nothing by default.
    }

    protected abstract void refreshBeanFactory();

}
