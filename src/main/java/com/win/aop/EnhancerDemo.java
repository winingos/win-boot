package com.win.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * Created by Administrator on 17/9/24.
 */
public class EnhancerDemo {
    public void test(){
        System.out.println("EnhancerDemo.test");
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnhancerDemo.class);
        enhancer.setCallback((MethodInterceptor)(o, m, a,p)->{
            System.out.println("Before invoke"+m);
            Object ret = p.invokeSuper(o, a);
            System.out.println("After invoke"+m);
            return ret;
        });
        EnhancerDemo demo = (EnhancerDemo) enhancer.create();
        demo.test();
        System.out.println(demo);

    }
}
