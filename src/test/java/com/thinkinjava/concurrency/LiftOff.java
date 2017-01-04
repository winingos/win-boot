package com.thinkinjava.concurrency;

/**
 * Created by 王宁 on 2016/11/14.
 * Demonstration of the Runnable interface.
 */
public class LiftOff implements Runnable{

    protected int countDown = 10; // Default
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff() {}
    public LiftOff(int countDown){
        this.countDown=countDown;
    }

    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }

    @Override
    public void run() {
        while(countDown-- > 0) {
            System.out.print(status());
            Thread.yield();
        }
    }

    public static void main(String[] args) {
//        LiftOff liftOff = new LiftOff();
//        liftOff.run();
        Thread thread = new Thread(new LiftOff());
        thread.start();
        System.out.println("Waiting for LiftOff");
//        Adding more threads.
        for(int i = 0; i < 5; i++)
            new Thread(new LiftOff()).start();
        System.out.println("Waiting for LiftOff");
    }
}
class Exercise1 implements Runnable
{
    public Exercise1(){
        System.out.println("Exercise1 created "+Thread.currentThread().getName());
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("call yield   "+Thread.currentThread().getName());
            Thread.yield();
        }
        return;

    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Exercise1()).start();
        }

        System.out.println("thread end");
    }
}
