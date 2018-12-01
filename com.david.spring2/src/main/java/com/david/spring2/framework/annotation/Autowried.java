package com.david.spring2.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by sc on 2018/11/18.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowried {

    String value() default "";
}