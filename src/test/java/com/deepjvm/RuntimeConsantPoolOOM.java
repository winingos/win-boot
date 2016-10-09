package com.deepjvm;

import java.util.ArrayList;

/**
 * Created by ning.wang on 2016/9/28.
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * jdk 1.6之前可以出现 OutOfMemoryError
 */
public class RuntimeConsantPoolOOM {
    public static void main(String[] args) {
        //使用List保持着常量池引用,避免Full GC回收常量池行为
        ArrayList<String> list = new ArrayList<>();
//        10MB的PerSize在integer范围内足够产生OOM了
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
class Test{
    public static void main(String[] args) {
        String s1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s1.intern()==s1);

        String s = new StringBuilder("ja").append("va").toString();
        System.out.println(s.intern() == s);
    }
}
