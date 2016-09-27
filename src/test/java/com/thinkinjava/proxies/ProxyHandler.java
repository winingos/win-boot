package com.thinkinjava.proxies;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2016/9/25 0025.
 */
public class ProxyHandler implements InvocationHandler{
    private Object proxied;

    public ProxyHandler( Object proxied )
    {
        this.proxied = proxied;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args ) throws Throwable
    {
        //在转调具体目标对象之前，可以执行一些功能处理
        // 在目标对象的方法执行之前简单的打印一下
        System.out.println("------------------before------------------");
        //转调具体目标对象的方法
        Object result = method.invoke(proxied, args);

        //在转调具体目标对象之后，可以执行一些功能处理
        System.out.println("------------------after------------------");
        return result;
    }
}
class DynamicProxy{
    public static void main( String args[] ){
        RealSubject real=new RealSubject();
        Subject proSub = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                RealSubject.class.getInterfaces(),
                new ProxyHandler(real));
        proSub.doSomething();
        //write proxySubject class binary data to file
        createProxyClassFile();
    }
    public static void createProxyClassFile(){
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass(name
        ,new Class[]{Subject.class});
        try {
            FileOutputStream out = new FileOutputStream(name + ".class");
            out.write(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
