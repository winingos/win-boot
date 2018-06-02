package com.concurrency.cancellation_shutdown;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by 王宁 on 2018/6/2.
 * 将日志放入消息队列,使用其他线程来处理
 */
public class LogWriter {
    private static final int CAPACITY = 3 /*n*/;
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;
    private volatile boolean shutdownRequested=false;

    public LogWriter(Writer writer) {
        this.queue = new LinkedBlockingQueue<String>(CAPACITY);
        this.logger = new LoggerThread(writer);
    }

    public void start() {
        logger.start();
    }
//    1.不支持关闭
//    public void log(String msg) throws InterruptedException {
//        queue.put(msg);
//    }

    public void log(String msg) throws InterruptedException {
        //这里有一个竞态条件?
        if (!shutdownRequested)
            queue.put(msg);
        else
            throw new IllegalStateException("logger is shut down");
    }

    private class LoggerThread extends Thread {
        private final PrintWriter writer;

        public LoggerThread(Writer writer) {
            this.writer = (PrintWriter) writer;
        }

        public void run() {
            try {
                while (true)
                    writer.println(queue.take());
            } catch (InterruptedException ignored) {
            } finally {
                writer.close();
            }
        }
    }
}