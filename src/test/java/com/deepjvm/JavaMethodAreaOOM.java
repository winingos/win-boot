package com.deepjvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by ning.wang on 2016/9/28.
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(HeapOOM.OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(o,args);
                }
            });
           // enhancer.setCallback((MethodInterceptor)(obj,method,argss,proxy)->proxy.invokeSuper(obj,args));
            enhancer.create();
        }
    }
}
