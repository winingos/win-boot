package com.netty.simple.nio;

import ch.qos.logback.classic.net.SimpleSocketServer;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 王宁 on 2017/9/18.
 */
public class SelectorTest {
    public static void main(String[] args) throws Exception {
        SocketChannel channel = null;
        Selector selector = Selector.open();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);

        while (true) {
            int readyCha = selector.select();
            if (readyCha == 0) continue;

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                    //SelectableChannel channel1 = key.channel();
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.

                } else if (key.isReadable()) {
                    // a channel is ready for reading

                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }

                /**
                 * You have to do this
                 */
                iterator.remove();

            }
        }
    }
}
