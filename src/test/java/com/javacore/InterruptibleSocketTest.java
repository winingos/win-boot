package com.javacore;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 17/7/29.
 */
public class InterruptibleSocketTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
                    JFrame frame = new InterruptibleSocketFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                }
        );
    }
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

        cancelBtn = new JButton("Cancel");
        cancelBtn.setEnabled(false);
        northPanel.add(cancelBtn);
        cancelBtn.addActionListener(e -> {
            connectTrd.interrupt();
            cancelBtn.setEnabled(false);
        });
        server = new TestServer();
        new Thread(server).start();
    }

    /**
     * Connects to the test server, using blocking I/O
     *
     * @throws IOException
     */

    private void connectBlocking() throws IOException {
        messages.append("Blocking: \n");
        Socket sock = new Socket("localhost", 8189);
        try {
            Scanner in = new Scanner(sock.getInputStream());
            processer(in);
        } finally {
            sock.close();
            EventQueue.invokeLater(() -> {
                messages.append("Socket closed\n");
                interruptibleBtn.setEnabled(true);
                blockingBtn.setEnabled(true);
            });
        }
    }

    private void processer(Scanner in) {
        while (!Thread.currentThread().isInterrupted()) {
            messages.append("Reading ");
            if (in.hasNextLine()) {
                String s = in.nextLine();
                messages.append(s);
                messages.append("\n");
            }
        }
    }

    /**
     * Connects to the test server, using interruptible I/O
     *
     * @throws IOException
     */

    private void connectInterruptibly() throws IOException {
        messages.append("Interruptible:\n");
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8189));

        try {
            in = new Scanner(channel);
            processer(in);
        } finally {
            channel.close();
            EventQueue.invokeLater(() -> {
                messages.append("Channel closed\n");
                interruptibleBtn.setEnabled(true);
                blockingBtn.setEnabled(true);
            });
        }
    }

    /**
     * A multi-threaded server that listens to port 8189 and send numbers to the client,
     * simulating a hanging server after 10 numbers.
     */
    class TestServer implements Runnable {

        @Override
        public void run() {
            try (ServerSocket s = new ServerSocket(8189)) {
                while (true) {
                    Socket incoming = s.accept();
                    Runnable r = new TestServerHandler(incoming);
                    new Thread(r).start();
                }
            } catch (IOException e) {
                messages.append("\nTestServer.run: " + e);
            }
        }
    }

    /**
     * This class handles the client input for one server socket connection
     */
    class TestServerHandler implements Runnable {
        private Socket incoming;
        private int counter;

        /**
         * Constructs a handler
         */
        public TestServerHandler(Socket i) {
            this.incoming = i;
        }

        @Override
        public void run() {
            try (OutputStream outStream = incoming.getOutputStream()) {

                PrintWriter out = new PrintWriter(outStream, true);
                while (counter < 100) {
                    counter++;
                    if (counter <= 10) out.println(counter);
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
                incoming.close();
                messages.append("Closing server\n");
            } catch (Exception e) {
                messages.append("\nTestServerHandler.run: " + e);
            }

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