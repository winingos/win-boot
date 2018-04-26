package com.netty.simple.nio;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Created by 王宁 on 2017/11/9.
 * 一个单线程的客户端,可以向socket服务发消息,
 * 而且可以接收并回显,服务端返回的消息
 * ***如果使用BIO,要么只能使用循环单行处理,要么就是在读取的数据的时候会无限期block***
 * **或者使用两个线程,一个监听输入流,一个监听输出流**
 */
public class JClientBIO {
    public static void main(String[] args) {
        new JClientBIO("localhost", 8189);
    }

//    private String url;
//    private int port;

    public JClientBIO(String url, int port) {
//        this.url = url;
//        this.port = port;
        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress(url, port);
        try {
            socket.connect(address);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            new Thread(new ProcessIn(inputStream)).start();
            new Thread(new ProcessOut(outputStream)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void processOut(OutputStream outputStream) {
//        PrintWriter writer = new PrintWriter(outputStream);
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            String b = sc.next();
//            writer.write(b);
//            writer.flush();
//        }
//    }

//    private void processIn(InputStream inputStream) {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")));
//        PrintStream out = System.out;
////        while (true) {
//            try {
//                String s = reader.readLine();
////                if (s==null)break;
//                out.println(s);
//                out.flush();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
////        }
//    }

}

class ProcessOut implements Runnable {
    OutputStream out;

    public ProcessOut(OutputStream outputStream) {
        out = outputStream;
    }

    @Override
    public void run() {
        PrintWriter writer = new PrintWriter(out,true);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext() && !Thread.currentThread().isInterrupted()) {
            String b = sc.next();
            writer.println(b);
            writer.flush();
        }
    }
}

class ProcessIn implements Runnable {
    InputStream in;

    public ProcessIn(InputStream inputStream) {
        in = inputStream;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("utf-8")));
        PrintStream out = System.out;
        while (true) {
            try {
                String s = reader.readLine();
                if (s==null){
                    System.out.println("service closed");
                    System.exit(0);
                }
                out.println(s);
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
