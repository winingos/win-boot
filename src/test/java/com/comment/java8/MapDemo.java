package com.comment.java8;


import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ning.wang on 2016/2/17.
 */
public class MapDemo {

    @Test
   public void test(){
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        map.forEach((t, val) -> System.out.println(val));

//        使用函数来计算map的编码
        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println("map = " + map.get(3));

        map.computeIfPresent(9, (num, val) -> null);
        System.out.println("map = " + map.containsKey(9));

        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println("map.containsKey(23) = " + map.containsKey(23));
//        map.containsKey(23);    // true

        map.computeIfAbsent(3, num -> "bam");
//        map.get(3);             // val33
        System.out.println("map.get(3) = " + map.get(3));
        boolean r = map.remove(3, "val3");
        String value =map.remove(4);
        boolean r1 = map.remove(3, "val33");
        map.get(3);
        map.get(4);
        System.out.println("value = " + value);
        System.out.println("r = " + r +" - r1 = " + r1+" - map.get(3) = " + map.get(3)+" - map.get(4) = " + map.get(4));

       String defaul = map.getOrDefault(42, "not found");
        System.out.println("defaul = " + defaul);

//        将map中的实例合并也是非常容易的：
        map.merge(9, "val9", (oldValue,newValue) -> oldValue.concat(newValue));
        String s = map.get(9);
        System.out.println("s = " + s);

        map.merge(9, "concat", (oldValue,newValue) -> oldValue.concat(newValue));
        String s1 = map.get(9);
        System.out.println("s1 = " + s1);

    }
}
