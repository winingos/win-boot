package com.win.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 17/9/17.
 */
@Component
public class AopallianceTest implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String name = invocation.getMethod().getName();
        System.out.println("name = " + name);
        invocation.proceed();
        System.out.println("after");
        return null;
    }
}
