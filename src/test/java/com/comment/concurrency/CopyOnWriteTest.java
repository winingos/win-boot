package com.comment.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by 王宁 on 2017/1/4.
 */
class AddArray implements Runnable {

    private List<String> list;
    private int c;

    public AddArray(List<String> list, int count) {
        this.list = list;
        this.c = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.c&&!Thread.currentThread().isInterrupted(); i++) {
            list.add(UUID.randomUUID().toString());
        }
        if (Thread.currentThread().isInterrupted()){
            System.out.println("isInterrupted");
        }
        System.out.println("list = " + this);
    }
}

public class CopyOnWriteTest {

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new CopyOnWriteArrayList();//new ArrayList<>();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new AddArray(list, 100));
        exec.execute(new AddArray(list, 100));
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("size=" + list.size());
        exec.shutdownNow();
        System.out.println("shutDownNow");
        System.out.println("size=" + list.size());
    }

    public void tst() {
        System.out.println("");
    }
}
