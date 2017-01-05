package com.thinkinjava.concurrency;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
class   NIOBlocked implements Runnable{
    SocketChannel sc;
    public NIOBlocked(SocketChannel sc){
        this.sc=sc;
    }
    @Override
    public void run() {
        try {
            System.out.println("Waiting for read() in "+this);
            sc.read(ByteBuffer.allocate(1));
        }catch (ClosedByInterruptException e) {
            System.out.println("ClosedByInterruptException");
        }catch (AsynchronousCloseException e){
            System.out.println("AsynchronousCloseException");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Exiting NIOBlocked.run() "+this);

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(9090);
//        InetSocketAddress isa = new InetSocketAddress(server.getInetAddress(),9091);
        SocketChannel sc1 = SocketChannel.open(server.getLocalSocketAddress());
        SocketChannel sc2 = SocketChannel.open(server.getLocalSocketAddress());
        Future<?> f = exec.submit(new NIOBlocked(sc1));
        exec.execute(new NIOBlocked(sc2));
        exec.shutdown();
        TimeUnit.SECONDS.sleep(1);
        f.cancel(true);

        sc2.close();


    }
}
public class NIOInterruption {
    public static void main(String[] args) throws IOException {

//        System.out.println("args = [" + server.getLocalSocketAddress() + "]");
    }
}
