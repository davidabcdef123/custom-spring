package com.use.action;

import com.spring.annotation.Autowried;
import com.spring.annotation.Controller;
import com.spring.annotation.RequestMapping;
import com.spring.annotation.RequestParam;
import com.use.service.ITestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sc on 2018/11/18.
 */
@Controller
@RequestMapping("/test/")
public class TestAction {

    @Autowried
    private ITestService testService;

    public void sayHello(HttpServletRequest request,HttpServletResponse response,@RequestParam("name")String name){
        testService.sayHello(name);
    }

}
