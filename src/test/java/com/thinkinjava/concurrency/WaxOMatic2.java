package com.thinkinjava.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/1/14 0014.
 */
class Car {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private boolean waxOn = false;

    public void waxed() {
        lock.lock();
        try {
            waxOn = true; // Ready to buff
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void buffed() {
        lock.lock();
        try {
            waxOn = false;//// Ready for another coat of wax
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void watingForWax() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void watingFowBuff() throws InterruptedException {
        lock.lock();
        try {
            while (!waxOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }
}

class Wax implements Runnable {

    Car car;

    public Wax(Car c) {
        this.car = c;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                System.out.println("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.watingForWax();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");

    }
}

class Buff implements Runnable {

    Car car;

    public Buff(Car c) {
        this.car = c;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                car.watingFowBuff();
                System.out.println("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");

    }
}

public class WaxOMatic2 {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Wax(car));
        exec.execute(new Buff(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }

}
