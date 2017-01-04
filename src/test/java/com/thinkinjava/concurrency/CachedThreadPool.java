package com.thinkinjava.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 王宁 on 2016/11/23.
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = //Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }

    }
}
