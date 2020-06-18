package com.lagou.edu.mvcframework.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Security {
    String value() default "";
}
