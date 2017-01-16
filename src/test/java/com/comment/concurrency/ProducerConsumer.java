package com.comment.concurrency;

import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Created by 王宁 on 2017/1/5.
 */
class Task {
    private String id;

    public Task() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Task " + id;
    }
}

class Consumer implements Runnable {
    private List<Task> list;

    public Consumer(List<Task> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                while (list.isEmpty()) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                Task task = list.remove(0);
                System.out.println("Consumer[" + Thread.currentThread().getName() + "] got " + task);
                list.notifyAll();

            }
        }
    }
}

class Producer implements Runnable {
    private List<Task> list;

    public Producer(List<Task> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                while (list.size() > 5) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Task e = new Task();
                list.add(e);
                list.notifyAll();
                System.out.println("Producer[" + Thread.currentThread().getName() + "] put " + e);

            }
        }

    }
}

class Producer1 implements Runnable {

    private BlockingQueue<Task> buf;

    public Producer1(BlockingQueue<Task> buf) {
        this.buf = buf;
    }

    @Override
    public void run() {
        while (true) {
            Task ta = new Task();
            try {
                buf.put(ta);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Producer[" + Thread.currentThread().getName() + "] put " + ta);
        }
    }
}

class Consumer1 implements Runnable {
    private BlockingQueue<Task> buf;

    public Consumer1(BlockingQueue<Task> buf) {
        this.buf = buf;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            try {
                Task task = buf.take();
                System.out.println("Consumer[" + Thread.currentThread().getName() + "] got " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ProducerConsumer {

    public static void main(String[] args) throws InterruptedException, ParseException {
        ArrayList<Task> tasks = new ArrayList<>();
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            es.execute(new Producer(tasks));
        }
        for (int i = 0; i < 5; i++) {
            es.execute(new Consumer(tasks));
        }
//            TimeUnit.SECONDS.sleep(12);
//            es.shutdownNow();
        System.out.println("over");
    }

    @Test
    public void testConsumer() throws InterruptedException {
        BlockingQueue<Task> tasks = new ArrayBlockingQueue<>(5);
        ExecutorService es = Executors.newFixedThreadPool(3);
        es.execute(new Producer1(tasks));
        es.execute(new Producer1(tasks));
        Future<?> submit = es.submit(new Consumer1(tasks));
//            es.submit(new Consumer1(tasks));

        TimeUnit.MILLISECONDS.sleep(190);


    }
}
