package com.javacore;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/**
 * Created by Administrator on 17/7/23.
 */
public class SocketTest {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 8189); Scanner sc = new Scanner(socket.getInputStream())) {
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);
            writer.println("hello socket");

            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
            writer.println("bye");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
