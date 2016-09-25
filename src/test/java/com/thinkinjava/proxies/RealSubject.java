package com.thinkinjava.proxies;

/**
 * Created by Administrator on 2016/9/25 0025.
 */
public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println( "call doSomething()" );
    }
}
