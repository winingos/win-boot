package com.concurrency.cancellation_shutdown;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.*;

import static com.concurrency.building_blocks.Preloader.launderThrowable;

/**
 * Created by 王宁 on 2018/5/10.
 */
public class NonInterruptible {
    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(4);
    int BUFSZ=1024;
    public static void timedRun(Runnable r, long timeout, TimeUnit unit)throws InterruptedException {
        Future<?> task = cancelExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            // task will be cancelled below
        } catch (ExecutionException e) {
            // exception thrown in task; rethrow
            throw launderThrowable(e.getCause());
        } finally {
            // Harmless if task already completed
            task.cancel(true); // interrupt if running
        }
    }

    public class ReaderThread extends Thread {
        private final Socket socket;
        private final InputStream in;
        public ReaderThread(Socket socket) throws IOException {
            this.socket = socket;
            this.in = socket.getInputStream();
        }
        public void interrupt() {
            try {
                socket.close();
            }
            catch (IOException ignored) { }
            finally {
                super.interrupt();
            }
        }
        public void run() {
            try {
                byte[] buf = new byte[BUFSZ];
                while (true) {
                    int count = in.read(buf);
                    if (count < 0)
                        break;
                    else if (count > 0)
                        processBuffer(buf, count);
                }
            } catch (IOException e) { /* Allow thread to exit */ }
        }
    }

    private void processBuffer(byte[] buf, int count) {

    }
}
