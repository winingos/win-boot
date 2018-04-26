package com.netty.simple.nio;

import com.thinkinjava.concurrency.SleepingTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by 王宁 on 2017/11/9.
 */
public class JClientNIO {
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public JClientNIO(String host, int port) {
        InetSocketAddress address = new InetSocketAddress(host, port);
        try {
            SocketChannel sc = SocketChannel.open(address);
            sc.configureBlocking(false);
            while (true) {
                read(sc);
                write(sc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void write(SocketChannel sc) {
        try {
            Scanner scanner = new Scanner(System.in);
            buffer.clear();
            String next = scanner.next();
            System.out.println("debug:"+next);
            buffer.put(next.getBytes());
            //复位操作
            buffer.flip();
            sc.write(buffer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void read(SocketChannel sc) {
        try {
            while (true) {
                //1 清空缓冲区中的旧数据
                buffer.clear();
                //2 获取之前注册的SocketChannel通道
//            SocketChannel sc = (SocketChannel) key.channel();
                //3 将sc中的数据放入buffer中
                int count = sc.read(buffer);
                if (count == 0) { // == -1表示通道中没有数据
                    return;
                }
                //读取到了数据，将buffer的position复位到0
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                //将buffer中的数据写入byte[]中
                buffer.get(bytes);
                String body = new String(bytes).trim();
                System.out.println(body);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

//        new JClientNIO("localhost", 8189);
        System.out.println("args = " +String.format("%.2f",3.33333));
    }
}
