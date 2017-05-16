package com.javacore;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Administrator on 17/7/23.
 */
public class EchoServer {
    public static void main(String[] args) {

        try(ServerSocket socket = new ServerSocket(8189)){
            Socket inc = socket.accept();
            InputStream inS = inc.getInputStream();
            OutputStream outS = inc.getOutputStream();
            Scanner in = new Scanner(inS);
            PrintWriter out = new PrintWriter(outS, true/*autoFlush*/);
            out.println("Hello! Enter BYE to exit.");
//            echo client input
            boolean done =false;
            while (!done&&in.hasNextLine()){
                String s = in.nextLine();
                out.println("Echo:"+s);
                if (s.trim().equalsIgnoreCase("bye"))
                    done=true;
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
