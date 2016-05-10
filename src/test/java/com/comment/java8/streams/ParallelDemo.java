package com.comment.java8.streams;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by ning.wang on 2016/2/17.
 * 并行流进行操作来提高运行效率
 */
public class ParallelDemo {
    final int max = 1000000;
    List<String> values = new ArrayList<>(max);
//    List<String> values = new ArrayList<>()
    @Before
    public void init(){

        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
    }
    @Test
    public void parallelTest(){
        serialSort();
        concurrentSort();
    }

    private void serialSort() {
        //        顺序排序
        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println("count = " + count);
        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
    }
    private void concurrentSort() {
        //        并行排序
        long t0 = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println("count = " + count);
        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
    }

}
