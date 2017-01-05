package com.thinkinjava.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
class BlockedMutex{
    private Lock lock= new ReentrantLock();
    public BlockedMutex(){
        lock.lock();
    }
    public  void f(){
        try{
            lock.lockInterruptibly();
        }catch (Exception e){
            out.println("Interrupted from lock acquisition in f()");
        }
    }
}
class Blocked2 implements Runnable{
    BlockedMutex blocked = new BlockedMutex();
    @Override
    public void run() {
        out.println("Waiting for f() in BlockedMutex");
        blocked.f();
        out.println("Broken out of blocked call");
    }
}
public class Interrupting2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked2());
        t.start();
//        Duration.of(30L, ChronoUnit.SECONDS);
        TimeUnit.SECONDS.sleep(1);
        out.println("Issuing t.interrupt()");
        t.interrupt();
    }
}
//同一个互斥如何被同一个任务多次获得

class MultiLock{
    public synchronized void f1(int count){
        if(count-- >0){
            out.println("f1() calling f2() with count "+count);
            f2(count);
        }
    }
    public synchronized void f2(int count){
        if (count-- >0){
            out.println("f2() calling f1() with count "+count);
            f1(count);
        }
    }

    public static void main(String[] args) {
        final MultiLock multiLock = new MultiLock();
        new Thread(()->multiLock.f1(10)).start();
    }
}
