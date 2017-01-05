package com.thinkinjava.concurrency;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/1/1 0001.
 */
class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int ident) {
        id = ident;
        System.out.println("NeedsCleanup " + id);
    }

    public void cleanup() {
        System.out.println("clean up " + id);
    }
}

class Block3 implements Runnable {
    private volatile double d = 0.0d;

    @Override
    public void run() {
        try {
            NeedsCleanup n1 = new NeedsCleanup(1);
            while (!Thread.interrupted()) {
                try {
                    System.out.println("sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    try {
                        System.out.println("Calculating");
                        for (int i = 0; i < 2500000; i++) {
                            d = d + (Math.PI + Math.E);
                        }
                        System.out.println("Finished time-consuming operation");
                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
            System.out.println("Exiting via while() test");
        } catch (InterruptedException e) {
            System.out.println("Exit via InterruptedException");
        }
    }
}

public class InterruptingIdiom {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Block3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(2100);
        t.interrupt();
    }
}
