package com.comment.temp;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import ch.qos.logback.core.util.TimeUtil;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/1/2 0002.
 */
public class IdGenerator {
    private final AtomicInteger sequenceNumber = new AtomicInteger(0);

    public  int  next() {
        Thread.yield();
        return sequenceNumber.getAndIncrement();
    }
class TestAtomic implements Runnable{
    private IdGenerator gen;
    public TestAtomic(IdGenerator g){
        this.gen=g;
    }
    @Override
    public void run() {
        System.out.println("id="+gen.next());
    }
}
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        IdGenerator id = new IdGenerator();
        for (int i = 0; i < 10; i++) {
            exec.execute(id.new TestAtomic(id));
        }
        TimeUnit.MILLISECONDS.sleep(100);
        exec.shutdownNow();
    }
}
//CountDownLatchTest
class Woker{
    private Duration d;
    private String name;
    public Woker(Duration duration,String name){
        this.d=duration;
        this.name=name;
    }
    public void doWork() {
        System.out.println(this +" begin");
        try {
            TimeUnit.MILLISECONDS.sleep(d.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this +" finished");
    }

    @Override
    public String toString() {
        return name+" work need time:"+d.getSeconds();
    }
}

class DoWork implements Runnable{
    Woker woker;
    CountDownLatch cdl;
    public DoWork(CountDownLatch cdl,Woker woker){
        this.cdl=cdl;
        this.woker=woker;
    }

    @Override
    public void run() {
        woker.doWork();
        cdl.countDown();
    }
    private static final int MAX_WORK_DURATION = 5000;  // 最大工作时间
    private static final int MIN_WORK_DURATION = 1000;  // 最小工作时间

    // 产生随机的工作时间
    private static long getRandomWorkDuration(long min, long max) {
        return(long) (Math.random() * (max - min) + min);
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Woker wn = new Woker(Duration.ofMillis(getRandomWorkDuration(MIN_WORK_DURATION, MAX_WORK_DURATION)), "wn"),
        mm = new Woker(Duration.ofMillis(getRandomWorkDuration(MIN_WORK_DURATION, MAX_WORK_DURATION)), "mm");
        new Thread(new DoWork(latch,wn)).start();
        new Thread(new DoWork(latch,mm)).start();
        latch.await();
        System.out.println("all over");

    }
}
