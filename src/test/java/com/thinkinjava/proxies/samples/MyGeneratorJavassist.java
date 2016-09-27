package com.thinkinjava.proxies.samples;

import javassist.*;

/**
 * Created by ning.wang on 2016/9/27.
 * 而不需要了解虚拟机指令，就能动态改变类的结构，或者动态生成类
 */
public class MyGeneratorJavassist {
    public static void main(String[] args)throws Exception {
        ClassPool pool = ClassPool.getDefault();

        CtClass cc = pool.makeClass("com.samples.Programmer");

        CtMethod method = CtNewMethod.make("public void code(){}", cc);

        method.insertBefore("System.out.println(\"I'm a Programmer,Just Coding.....\");");

        cc.addMethod(method);

        cc.writeFile("d://tmp");
    }
}
