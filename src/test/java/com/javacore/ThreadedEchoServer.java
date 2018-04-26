package com.javacore;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 17/7/29.
 * This program implement a mutilThread server that
 * listens to port 8189 and echos back all client input
 */
public class ThreadedEchoServer {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        try(ServerSocket s = new ServerSocket(8189)){
            int i=1;
            while (true){
                Socket accept = s.accept();
                System.out.println("Spawning" + i);
//                ThreadedEchoHandler echoHandler = new ThreadedEchoHandler(accept);
                service.execute(new ThreadedEchoHandler(accept));
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

/**
 * This class handles the client input for one server socket connection.
 */
class ThreadedEchoHandler implements Runnable {

    private Socket incoming;

    public ThreadedEchoHandler(Socket i) {
        this.incoming = i;
    }

    @Override
    public void run() {
       try( InputStream ins = incoming.getInputStream();
        OutputStream outs = incoming.getOutputStream()){
           Scanner in = new Scanner(ins);
           PrintWriter out = new PrintWriter(outs, true);
           out.println("Hello! Enter BYE to exit.");
           out.println(Thread.currentThread().getName());
           System.out.println("Hello! Enter BYE to exit.");
           //echo client input
           boolean done=false;
           while (!done&&in.hasNextLine()){
               String line = in.nextLine();
               System.out.println("client:" + line);
               out.println("Echo:"+line);
               if (line.trim().equalsIgnoreCase("bye")){
                   done=true;
               }
           }

       }catch (Exception e){
           e.printStackTrace();
       }finally {
           if (incoming!=null){
               try {
                   incoming.close();
               }catch (IOException e){
                   e.printStackTrace();
               }

           }
       }

    }
}
