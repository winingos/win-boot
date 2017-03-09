package com.thinkinjava.concurrency;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by 王宁 on 2017/3/9.
 * 两个线程之间数据交互,添加输出更加容易理解
 */
class ExchangerProducer<T> implements Runnable {
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;

    ExchangerProducer(Exchanger<List<T>> exchg,
                      Generator<T> gen, List<T> holder) {
        exchanger = exchg;
        generator = gen;
        this.holder = holder;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (int i = 0; i < ExchangerDemo.size; i++)
                    holder.add(generator.next());
                // Exchange full for empty:
                System.out.println("ExchangerProducer exchang before = " + holder);
                holder = exchanger.exchange(holder);
                System.out.println("ExchangerProducer exchang after = " + holder);
            }
        } catch (InterruptedException e) {
            // OK to terminate this way.
        }
    }
}




class ExchangerConsumer<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;

    ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder) {
        exchanger = ex;
        this.holder = holder;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("ExchangerConsumer exchang before " + holder);
                holder = exchanger.exchange(holder);
                System.out.println("ExchangerConsumer exchang after " + holder);
                for (T x : holder) {
                    value = x; // Fetch out value
                    holder.remove(x); // OK for CopyOnWriteArrayList
                }
            }
        } catch (InterruptedException e) {
// OK to terminate this way.
        }
        System.out.println("Final value: " + value);
    }
}

public class ExchangerDemo {
    static int size = 3;
    static int delay = 1; // Seconds

    public static void main(String[] args) throws Exception {
        if (args.length > 0)
            size = new Integer(args[0]);
        if (args.length > 1)
            delay = new Integer(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> xc = new Exchanger<List<Fat>>();
        List<Fat>
                producerList = new CopyOnWriteArrayList<Fat>(),
                consumerList = new CopyOnWriteArrayList<Fat>();
        exec.execute(new ExchangerProducer<Fat>(xc,
                Generator.create(Fat.class), producerList));
        exec.execute(
                new ExchangerConsumer<Fat>(xc, consumerList));
        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }
}

class Generator<T> {
    Class<T> clazz;

    public static Generator  create(Class clazz) {
        return new Generator(clazz);
    }
    public Generator(Class<T> clz){
        this.clazz=clz;
    }

    T next() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException|IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

