package com.comment.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Mtime on 2016/2/4.
 */
public class MyClass {
    public int count;
    public MyClass(int start) {
        count = start;
    }
    public void increase(int step) {
        count = count + step;
    }

    public static void main(String[] args) throws Exception {
        MyClass myClass = new MyClass(0);
        myClass.increase(2);
        System.out.println("Normal - >" + myClass.count);

        //反射
        Constructor<MyClass> constructor = MyClass.class.getConstructor(int.class);//获取构造方法
        MyClass aClass = constructor.newInstance(10);//创建对象
        Method increase = MyClass.class.getMethod("increase", int.class);//获取方法
        increase.invoke(aClass,5); //调用方法
        Field count = MyClass.class.getField("count");//获取域
        System.out.println("Reflect ->  [" + count.getInt(aClass) + "]");//或区域的值

        //Array类提供了一系列的静态方法用来创建数组和对数组中的元素进行访问和操作
        Object array = Array.newInstance(String.class, 10); //等价于 new String[10]
        Array.set(array, 0, "Hello");  //等价于array[0] = "Hello"
        Array.set(array, 1, "World");  //等价于array[1] = "World"
        System.out.println(Array.get(array, 0));  //等价于array[0]
    }
}
