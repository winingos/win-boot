package com.thinkinjava.concurrency.lockfreecontainer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 王宁 on 2017/5/11.
 * Framework to test performance of concurrency containers.
 */
public abstract class Tester<C> {
    static int testReps = 10;
    static int testCycles = 1000;
    static int containerSize = 1000;

    abstract C containerInitializer();

    abstract void startReadersAndWriters();

    C testContainer;
    String testId;
    int nReaders;
    int nWriters;
    volatile long readResult = 0;
    volatile long readTime = 0;
    volatile long writeTime = 0;
    CountDownLatch endLatch;
    static ExecutorService exec = Executors.newCachedThreadPool();
    Integer[] writeData;

    Tester(String testId, int nReaders, int nWriters) {
        this.testId = testId + " " +
                nReaders + "r " + nWriters + "w";
        this.nReaders = nReaders;
        this.nWriters = nWriters;
        writeData = Generated.arrayT(Integer.class,new RandomGenerator.Integer(), containerSize);
        for (int i = 0; i < testReps; i++) {
            runTest();
            readTime = 0;
            writeTime = 0;
        }
    }

    void runTest() {
        endLatch = new CountDownLatch(nReaders + nWriters);
        testContainer = containerInitializer();
        startReadersAndWriters();
        try {
            endLatch.await();
        } catch (InterruptedException ex) {
            System.out.println("endLatch interrupted");
        }
        System.out.printf("%-27s %14d %14d\n", testId, readTime, writeTime);
        if (readTime != 0 && writeTime != 0)
            System.out.printf("%-27s %14d\n", "readTime + writeTime =", readTime + writeTime);
    }

    abstract class TestTask implements Runnable {
        abstract void test();

        abstract void putResults();

        long duration;

        public void run() {
            long startTime = System.nanoTime();
            test();
            duration = System.nanoTime() - startTime;
            synchronized (Tester.this) {
                putResults();
            }
            endLatch.countDown();
        }
    }

    public static void initMain(String[] args) {
        if (args.length > 0)
            testReps = new Integer(args[0]);
        if (args.length > 1)
            testCycles = new Integer(args[1]);
        if (args.length > 2)
            containerSize = new Integer(args[2]);
        System.out.printf("%-27s %14s %14s\n", "Type", "Read time", "Write time");
    }

}


//page 939
class Generated {

    static Integer[] array(Class clazz, RandomGenerator.Integer gen,int size) {
        Integer[] objects  = new Integer[size];
        for (int i = 0; i < objects.length; i++) {
            objects[i]=gen.next();

        }
        //?List.toArray();
//        ArrayList<Object> ts = new ArrayList<>(size);
//        for (int i = 0; i < size; i++) {
//             ts.add(i,gen.next());
//        }
        return  objects;
    }

    static <T> T[] arrayT(Class clazz, RandomGenerator.Integer gen,int size) {
//        T[] objects  = new Object[size];
//        for (int i = 0; i < objects.length; i++) {
//            objects[i]=gen.next();
//
//        }
        ArrayList<Object> ts = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
             ts.add(i,gen.next());
        }
        T[] a=(T[])Array.newInstance(clazz,size);
        return  ts.toArray(a);
    }
}

abstract class RandomGenerator<T> {
    abstract  T next();
    static class Integer extends RandomGenerator {
        Random random = new Random(47);

        @Override
        java.lang.Integer next() {
            return random.nextInt(java.lang.Integer.MAX_VALUE);
        }
    }
}
