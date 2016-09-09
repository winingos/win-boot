package com.comment.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by ning.wang on 2016/6/12.
 */
public class GetRuntimeParameterName {

    public void createUser(String name, int age, int version) {
        //
    }

    public static void main(String[] args) throws Exception {
        for (Method m : GetRuntimeParameterName.class.getMethods()) {
            System.out.println("----------------------------------------");
            System.out.println("   method: " + m.getName());
            System.out.println("   return: " + m.getReturnType().getName());
            for (Parameter p : m.getParameters()) {
                System.out.println("parameter: " + p.getType().getName() + ", " + p.getName());
            }
        }
    }
}
