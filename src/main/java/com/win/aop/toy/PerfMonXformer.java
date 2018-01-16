package com.win.aop.toy;

import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by Administrator on 17/12/9.
 */
public class PerfMonXformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed=null;
//        System.out.println("transforming " + className);
        ClassPool pool = ClassPool.getDefault();
        CtClass cl=null;
        try {
            cl = pool.makeClass(new ByteArrayInputStream(classfileBuffer));
            if (!cl.isInterface()){
                CtBehavior[] behaviors = cl.getDeclaredBehaviors();
                for (CtBehavior method : behaviors) {

                    if(!method.isEmpty()&&method.getName().equals("test")){
                        System.out.println("method.getSignature() = " + method.getSignature());
                        System.out.println("method.getName() = " + method.getName());
                        System.out.println("method.getLongName() = " + method.getLongName());
                        // 修改字节码
                        doMethod(method);


                    }
                }
                transformed=cl.toBytecode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cl!=null)
                cl.detach();
        }
        return transformed;
    }

    private void doMethod(CtBehavior method) throws CannotCompileException {

        method.insertBefore("long result = System.currentTimeMillis(); System.out.println(result);");

        method.insertAfter("System.out.println(\"leave   " + method.getName()+"   and time:\"+(System.currentTimeMillis()-result)+\"ms.\");");
        //        behavior.instrument(new ExprEditor() {
//            public void edit(MethodCall m) throws CannotCompileException {
//                m.replace("{ long stime = System.nanoTime(); $_ = $proceed($$); System.out.println(\""
//                        + m.getClassName()+"."+m.getMethodName()
//                        + ":\"+(System.nanoTime()-stime)+\"ns.\");}");
//            }
//        });
    }


}
