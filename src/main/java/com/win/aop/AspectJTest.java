package com.win.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 17/9/16.
 */
@Component
@Aspect
@EnableAspectJAutoProxy
public class AspectJTest {
    @Pointcut("execution(* *.test(..))")
    public void test1(){}
    @Before("test1()")
    public void beforeTes(){
        System.out.println("AspectJTest.beforeTes");
    }

    @After("test1()")
    public void afterTest(){
        System.out.println("AspectJTest.afterTest");
    }

    @Around("test1()")
    public Object aroundTest(ProceedingJoinPoint p){
        System.out.println("before1");
        Object o=null;
        try {
            o = p.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("after1");
        return o;
    }
}
