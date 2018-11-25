package com.spring.annotation;

import java.lang.annotation.*;

/**
 * Created by sc on 2018/11/18.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

    String value() default "";

}
