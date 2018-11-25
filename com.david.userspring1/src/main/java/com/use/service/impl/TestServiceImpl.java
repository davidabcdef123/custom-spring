package com.use.service.impl;

import com.spring.annotation.Service;
import com.use.service.ITestService;

/**
 * Created by sc on 2018/11/18.
 */
@Service
public class TestServiceImpl implements ITestService {
    @Override
    public void sayHello(String name) {
        System.out.println("你好："+name);
    }
}
