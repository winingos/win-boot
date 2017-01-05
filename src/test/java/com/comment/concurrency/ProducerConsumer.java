package com.comment.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

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
                System.out.println("Consumer["+ Thread.currentThread().getName() + "] got "+ task );
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
                System.out.println("Producer["+ Thread.currentThread().getName() + "] put "+ e);

            }
        }

    }
}

    public class ProducerConsumer {

        public static void main(String[] args) throws InterruptedException {
            ArrayList<Task> tasks = new ArrayList<>();
            ExecutorService es = Executors.newCachedThreadPool();
            for (int i = 0; i < 3; i++) {
                es.execute(new Producer(tasks));
            }
            for (int i = 0; i <5; i++) {
                es.execute(new Consumer(tasks));
            }
//            TimeUnit.SECONDS.sleep(12);
//            es.shutdownNow();
            System.out.println("over");
        }
    }
