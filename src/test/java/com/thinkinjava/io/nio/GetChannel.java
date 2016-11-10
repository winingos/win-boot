package com.thinkinjava.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Mtime on 2016/11/1.
 * Getting channels from streams
 */
public class GetChannel {
    public static final int BSIZE=1024;

    public static void main(String[] args)throws IOException {
        // Write a file:
        FileChannel fc = new FileOutputStream("data.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text ".getBytes()));
        fc.close();
        // Add to the end of the file:
        fc = new RandomAccessFile("data.txt","rw").getChannel();
        fc.position(fc.size());//Move to the end
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();
        //Read the file
        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fc.read(buffer);
        buffer.flip();
        while (buffer.hasRemaining())
            System.out.print((char)buffer.get());
    }
}
