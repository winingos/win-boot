package com.thinkinjava.concurrency.deadlock;

/**
 * Created by 王宁 on 2017/1/16.
 */
public class Chopstick {
    private boolean taken = false;
public synchronized void take() throws InterruptedException {
    while (!Thread.interrupted()){
        if (!taken){
            taken=true;
        }else{
            wait();
        }/*
        while(taken)
            wait();
        taken = true;
        这是书本上的写法,我觉得,没有我写的优雅
        */
    }
}

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
