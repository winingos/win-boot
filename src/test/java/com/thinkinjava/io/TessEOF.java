package com.thinkinjava.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * Created by ning.wang on 2016/10/26.
 * Testing for end of file while reading a byte at a time.
 */
public class TessEOF {
    public static void main(String[] args) throws Exception{
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("D:\\IdeaWorkSpaec\\win-boot\\src\\test\\java\\com\\thinkinjava\\io\\TessEOF.java")));
        while(in.available() != 0){
            System.out.print((char)in.readByte());
        }
    }
}
