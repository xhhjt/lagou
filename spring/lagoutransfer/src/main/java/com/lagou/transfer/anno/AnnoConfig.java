package com.lagou.transfer.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoConfig {
    String ScanPath() default "";

}
