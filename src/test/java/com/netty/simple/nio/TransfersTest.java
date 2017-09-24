package com.netty.simple.nio;

import com.alibaba.fastjson.JSON;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by 王宁 on 2017/9/18.
 */
public class TransfersTest {
    public static void main(String[] args)throws Exception {

        transferFrom();

    }
    public static final void transferTo() throws Exception{
        RandomAccessFile fromFile = new RandomAccessFile("from.txt", "rw");
        FileChannel      fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("to.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count    = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);
    }
    public static final void transferFrom() throws Exception{
        RandomAccessFile fromFile = new RandomAccessFile("from.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("to.txt", "rw");
        FileChannel toChannel = toFile.getChannel();
        long position =0;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel,position,count);
        Integer i=null;
        System.out.println("count = " + (i!=null&&i==4));
    }
}
