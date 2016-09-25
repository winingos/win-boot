package com.thinkinjava.typeinfo;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public class GenericClassReferences {
    public static void main(String[] args) {
        Class intClass = int.class;
        Class<Integer> iCls = int.class;
        iCls = Integer.class;
        intClass = double.class;
//        iCls = double.class; Illegal
//        Class<Number> inCls = int.class; Illegal
        Class<?> intCls = int.class;
        intCls = double.class;
        Class<? extends Number> nCls = int.class;
        nCls = double.class;
//        nCls = Object.class;
    }
}
