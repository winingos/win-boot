package com.javacore;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Administrator on 17/7/29.
 */
public class InterruptibleSocketTest {
}

class InterruptibleSocketFrame extends JFrame {

    public InterruptibleSocketFrame() {
        setSize(WIDTH, HEIGHT);
        setTitle("InterruptibleSocketTest");

        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);

        messages = new JTextArea();
        add(new JScrollPane(messages));

        interruptibleBtn = new JButton("Interruptible");
        blockingBtn = new JButton("Blocking");

        northPanel.add(interruptibleBtn);
        northPanel.add(blockingBtn);

        interruptibleBtn.addActionListener(e -> {
            interruptibleBtn.setEnabled(false);
            blockingBtn.setEnabled(false);
            cancelBtn.setEnabled(true);
            connectTrd = new Thread(() -> {
                try {
                    connectInterruptibly();
                } catch (IOException e1) {
                    messages.append("\n InterruptibleSocketTest.connectInterruptibly:" + e);
                }
            });
            connectTrd.start();
        });

        blockingBtn.addActionListener(e -> {
            interruptibleBtn.setEnabled(false);
            blockingBtn.setEnabled(false);
            cancelBtn.setEnabled(true);
            connectTrd = new Thread(() -> {
                try {
                    connectBlocking();
                } catch (IOException e1) {
                    messages.append("\n InterruptibleSocketTest.connectBlocking:" + e);
                }
            });
            connectTrd.start();
        });

        cancelBtn=new JButton("Cancel");
        cancelBtn.setEnabled(false);
        northPanel.add(cancelBtn);
        cancelBtn.addActionListener(e -> {
            connectTrd.interrupt();
            cancelBtn.setEnabled(false);
        });
        server=new TestServer();
        new Thread(server).start();
    }


    private void connectBlocking()throws IOException {

    }


    private void connectInterruptibly() throws IOException {

    }

    class TestServer implements Runnable{

        @Override
        public void run() {

        }
    }

    private Scanner in;
    private JButton interruptibleBtn;
    private JButton blockingBtn;
    private JButton cancelBtn;
    private JTextArea messages;
    private TestServer server;
    private Thread connectTrd;

    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
}