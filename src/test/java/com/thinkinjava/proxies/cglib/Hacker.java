package com.thinkinjava.proxies.cglib;

import com.thinkinjava.proxies.samples.Programmer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by ning.wang on 2016/9/27.
 * 实现了方法拦截器接口
 * <p/>
 * <p/>
 * ?给定一个class类对象,获取所有的属性,即反编译
 * ?如何将一个class类对象,输出到硬盘
 */
public class Hacker implements MethodInterceptor {
    public static void main(String[] args) {
        Programmer pro = new Programmer();
        Hacker hacker = new Hacker();
        //cglib 中加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();
        //设置要创建动态代理的类
        enhancer.setSuperclass(pro.getClass());
        // 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截
        enhancer.setCallback(hacker);

        /**
         * 使用内部类(jdk1.8 lambda表达式)
         */
//        enhancer.setCallback((MethodInterceptor) (o, m, arg, proxy) -> {
//            System.out.println("**** I am a hacker,Let's see what the poor programmer is doing Now...");
//            proxy.invokeSuper(o, arg);
//            System.out.println("**** I am a hacker,Let's see what the poor programmer is doing Now...");
//            return null;
//        });
        Programmer proxy = (Programmer) enhancer.create();

        proxy.code();
//        System.out.println("proxy = "+ proxy.getClass());

    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("**** I am a hacker,Let's see what the poor programmer is doing Now...");
        proxy.invokeSuper(o, args);
        System.out.println("****  Oh,what a poor programmer.....");
        return null;
    }
}
