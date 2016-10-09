package com.deepjvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by ning.wang on 2016/9/28.
 * 可以不使用命令行,直接执行
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024*1024;

    public static void main(String[] args) throws Exception{
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
