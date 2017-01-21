package com.thinkinjava.concurrency.deadlock;

/**
 * Created by 王宁 on 2017/1/16.
 */
public class Chopstick {
    private boolean taken = false;
public synchronized void take() throws InterruptedException {
//    while (!Thread.interrupted()){
        /*if (!taken){
            taken=true;
        }else{
            wait();
            */
        while(taken)
            wait();
        taken = true;
       /* 这是书本上的写法,我觉得,没有我写的优雅,
       2017-01-21,发现自己被外层的while循环坑了
            当我take的时候,将状态变为true,然后再循环进来wait()
            出不去了
       */

//    }
}

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
