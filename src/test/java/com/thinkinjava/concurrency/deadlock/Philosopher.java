package com.thinkinjava.concurrency.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by 王宁 on 2017/1/16.
 */
public class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private final int id;
    //
    private final int ponderFactor;
    private static Random rand = new Random(47);

    private void pause() throws InterruptedException {
        if(ponderFactor == 0) return;
        int timeout = rand.nextInt(ponderFactor * 250);
//        System.out.println("timeout value = " + timeout);
        TimeUnit.MILLISECONDS.sleep(
                timeout);
    }

    public Philosopher(Chopstick left, Chopstick right,
                       int ident, int ponder) {
        this.left = left;
        this.right = right;
        id = ident;
        ponderFactor = ponder;
    }

    @Override
    public String toString() {
        return "Philosopher"+id;
    }


    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                System.out.println(this+" thinking");
                pause();
                // Philosopher becomes hungry
                System.out.println(this + " grabbing right");
                right.take();
                System.out.println(this+" grabbing left");
                left.take();
                System.out.println(this + " eating");
                pause();
                right.drop();
                left.drop();
            }
        }catch (InterruptedException e){
            System.out.println(this + " " + "exiting via interrupt");
        }
    }
}
