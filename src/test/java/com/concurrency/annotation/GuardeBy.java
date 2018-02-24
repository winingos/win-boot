package com.concurrency.annotation;

/**
 * Created by 王宁 on 2018/2/24.
 */
public @interface GuardeBy {
    String value() default "";
}
