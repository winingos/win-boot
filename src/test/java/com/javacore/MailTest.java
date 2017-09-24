package com.javacore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 王宁 on 2017/9/8.
 * This program shows how to sockets to send plain text mail messages.
 */
public class MailTest {
    public static void main(String[] args) {
//        EventQueue.invokeLater(()->{
//            JFrame frame = new MailTestFrame();
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setVisible(true);
//        });
        Float aFloat = Float.valueOf("");
        System.out.println("aFloat = " + aFloat);
    }
}
class MailTestFrame extends JFrame{
    public MailTestFrame(){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setTitle("MailTest");

        setLayout(new GridBagLayout());

//        //we use the GBC convenience class of Core Java Volume I,Chapter 9
//        add(new JLabel("From:"),new GridBagConstraints(0,0).setFill(GridBagConstraints.HORIZONTAL));
//
//        from = new JTextField(20);
//        add(from,new GBC);

        JPanel btnPanel = new JPanel();

        JButton sendButton = new JButton("Send");
        btnPanel.add(sendButton);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SwingWorker<Void,Void>(){

                    @Override
                    protected Void doInBackground() throws Exception {
                        comm.setText("");
                        sendMail();
                        return null;

                    }
                }.execute();
            }
        });
    }

    /**
     * Sends the mails message that has been authored in the GUI
     */
    private void sendMail() {
        try (Socket s = new Socket(smtpServer.getText(), 25)){

            InputStream inS = s.getInputStream();
            OutputStream outS = s.getOutputStream();
            Scanner in = new Scanner(inS);
            PrintWriter out = new PrintWriter(outS);
            String hostName = InetAddress.getLocalHost().getHostName();

            recive();
            send("HELLO"+hostName);
            recive();
            send("MAIL FROM:<"+from.getText()+">");
            recive();
            send("rept to:<"+to.getText()+">");
            recive();
            send("data");
            recive();
            send(message.getText());
            send(".");
            recive();
            s.close();
        } catch (IOException e) {
            comm.append("Error:"+e);
        }
        recive();
    }

    /**
     * Receives a string from the socket and displays it in the comm text area.
     */
    private void recive() {
        String s = in.nextLine();
        comm.append(s);
        comm.append("\n");
    }

    /**
     * Sends a string to the socket and echoes it in the comm text area.
     */
    private void send(String s)throws IOException{
        comm.append(s);
        comm.append("\n");
        out.print(s.replaceAll("\n","\r\n"));
        out.print("\r\n");
        out.flush();
    }

    private Scanner in;
    private PrintWriter out;
    private JTextField to;
    private JTextField from;
    private JTextField smtpServer;
    private JTextArea message;
    private JTextArea comm;

    public static final int DEFAULT_WIDTH=300;
    public static final int DEFAULT_HEIGHT=300;
}
