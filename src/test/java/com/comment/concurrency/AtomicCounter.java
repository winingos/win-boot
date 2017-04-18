package com.comment.concurrency;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by Administrator on 17/4/18.
 * 原子变量
 */
public class AtomicCounter {
    private AtomicInteger c = new AtomicInteger(0);

    public void increment() {
        c.incrementAndGet();
        //ThreadLocalRandom，当应用程序期望在多个线程或ForkJoinTasks中使用随机数时
        //TheadLocalRandom代替Math.random()可以减少竞争
        ThreadLocalRandom.current().nextInt(4,77);
    }

    public void decrement() {
        c.decrementAndGet();
    }

    public int value() {
        return c.get();
    }
}

class CounterAdder{

    LongAdder l = new LongAdder();
    public void increment(){
        l.increment();
    }
    public void decrement() {
        l.decrement();
    }

    public int value() {
        return l.intValue();
    }

}

