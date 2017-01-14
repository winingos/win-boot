package com.thinkinjava.concurrency;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/1/14 0014.
 */
class Sender implements Runnable{
    private Random rand = new Random(47);
    private PipedWriter out = new PipedWriter();
    public PipedWriter getPipedWriter() { return out; }
    @Override
    public void run() {
        try {
            for (int i = 'A'; i < 'z'; i++) {
                out.write(i);
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
            }
        } catch (IOException|InterruptedException e) {
            System.out.println("Sender " + e.getClass().getName());
        }
    }
}
class Receiver implements Runnable{
    private PipedReader in;
    public Receiver(Sender sender) throws IOException {
        in = new PipedReader(sender.getPipedWriter());
    }
    @Override
    public void run() {
        try {
            while(true) {
                // Blocks until characters are there:
                System.out.println("Read: " + (char)in.read() + ", ");
            }
        } catch(IOException e) {
            System.out.println(" Receiver read exception");
        }
    }
}
public class PipedIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(sender);
        es.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        es.shutdownNow();
    }
}
