package com.thinkinjava.concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/1/14 0014.
 */
class LiftOffRunner implements Runnable{
    private BlockingQueue<LiftOff> rockets;
    public LiftOffRunner(BlockingQueue<LiftOff> queue) {
        rockets = queue;
    }
    public void add(LiftOff of){
        try {
            rockets.put(of);
        } catch (InterruptedException e) {
            System.out.print("Interrupted during put()");
        }
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                LiftOff take = rockets.take();
                take.run();// use the same Thread
            }
        } catch (InterruptedException e) {
            System.out.print("Waking from take()");
        }
        System.out.println("Exiting LiftOffRunner");
    }
}
public class TestBlockingQueues {
    //user stdin to block main Thread  使用标准输入阻塞主线程
    static void getkey(){
        try {
            // Compensate for Windows/Linux difference in the
            // length of the result produced by the Enter key:
             new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static void getkey(String message) {
        System.out.print(message);
        getkey();
    }

    static void test(String msg,BlockingQueue<LiftOff> queue) throws InterruptedException {
        System.out.println(msg);

        LiftOffRunner r = new LiftOffRunner(queue);
        Thread t = new Thread(r);
        t.start();

        for (int i = 0; i < 5; i++) {
            r.add(new LiftOff(5));
        }
        getkey("Press ‘Enter’ (" + msg + ")");
//        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
        System.out.println("Finished " + msg + " test");
    }

    public static void main(String[] args) throws InterruptedException {
        test("LinkedBlockingQueue", // Unlimited size
                new LinkedBlockingQueue<LiftOff>());
        test("ArrayBlockingQueue", // Fixed size
                new ArrayBlockingQueue<LiftOff>(3));
        test("SynchronousQueue", // Size of 1
                new SynchronousQueue<LiftOff>());
    }
}
