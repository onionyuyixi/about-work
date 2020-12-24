package com.example.aboutwork.callBack;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Documented
public @interface MyCallBack {

    Class value();
}
