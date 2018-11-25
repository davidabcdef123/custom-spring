package com.spring.servlet;

import com.spring.annotation.Autowried;
import com.spring.annotation.Controller;
import com.spring.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sc on 2018/11/18.
 */
public class DispachServlet extends HttpServlet {

    private Properties properties = new Properties();

    private Map<String, Object> iocBeans = new ConcurrentHashMap<>();

    private List<String> beanNames = new ArrayList();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("--------post----------------");
        try {
            //iocBeans.get("testAction").
          iocBeans.get("testAction").getClass().getDeclaredMethod("sayHello",HttpServletRequest.class,HttpServletResponse.class,String.class).invoke(iocBeans.get("testAction"),request,response,"tom");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("------------doget--------------------");
        doPost(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) {
        //定位--读取配置文件
        doloadConfig(config);
        //加载--加载bean
        doLoadBean(properties.getProperty("scan.package"));
        //注册--注册bean到ioc中
        doRegister();
        //依赖注入
        //在Spring中是通过调用getBean方法才出发依赖注入的
        doAutoWried();

    }

    private void doAutoWried() {
        if (iocBeans != null && iocBeans.size() > 0) {
            for (Map.Entry<String,Object> entry:iocBeans.entrySet()) {
                Field[] fields=entry.getValue().getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    if(!fields[i].isAnnotationPresent(Autowried.class)){
                        continue;
                    }
                    try {
                        fields[i].setAccessible(true);
                        fields[i].set(entry.getValue(),iocBeans.get(lowerFirstCase(fields[i].getType().getSimpleName())));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }


            }

        }
    }

    private void doRegister() {
        if (beanNames != null && beanNames.size() > 0) {
            for (int i = 0; i < beanNames.size(); i++) {
                Class clazz = null;
                try {
                    clazz = Class.forName(beanNames.get(i));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (clazz.isAnnotationPresent(Service.class)) {
                    try {
                        Class[] interfaces = clazz.getInterfaces();
                        if (interfaces != null && interfaces.length > 0) {
                            for (int j = 0; j < interfaces.length; j++) {
                                iocBeans.put(lowerFirstCase(interfaces[j].getSimpleName()), clazz.newInstance());
                            }
                        }
                        iocBeans.put(lowerFirstCase(clazz.getSimpleName()), clazz.newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    //在Spring中用的多个子方法来处理的
                } else if (clazz.isAnnotationPresent(Controller.class)) {
                    try {
                        //在Spring中在这个阶段不是不会直接put instance，这里put的是BeanDefinition
                        iocBeans.put(lowerFirstCase(clazz.getSimpleName()), clazz.newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private void doLoadBean(String basepackage) {
        URL url = this.getClass().getResource("/" + basepackage.replaceAll("\\.", "/"));
        File file = new File(url.getFile());
        if (file != null) {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    doLoadBean(basepackage + "." + fileList[i].getName());
                } else {
                    beanNames.add(basepackage + "." + fileList[i].getName().replaceAll(".class", ""));
                }
            }
        }

    }

    private void doloadConfig(ServletConfig config) {
        //在Spring中是通过Reader去查找和定位
        String location = config.getInitParameter("defaultConfig");
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:", ""));
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String lowerFirstCase(String beanName) {
        char[] charArr = beanName.toCharArray();
        charArr[0] += 32;
        return String.valueOf(charArr);
    }
}
