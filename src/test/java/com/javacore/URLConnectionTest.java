package com.javacore;

import com.alibaba.fastjson.JSON;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by 王宁 on 2017/9/11.
 * This program connects to a URL and displays the response data and the first 10
 * lines of the requested data.
 * Supply the URL and an optional username and password(for HTTP basic authentication) on
 * the command line.
 */
public class URLConnectionTest {
    public static void main(String[] args) {
        try {
            String urlName;
            if (args.length>0) urlName=args[0];
            else urlName="http://java.sun.com";

            URL url = new URL(urlName);
            URLConnection connection = url.openConnection();

            //set username,password if specified on command line

            if (args.length>2){
                String uname = args[1];
                String pwd = args[2];
                String input = uname + ":" + pwd;
                String encode =Base64Encode(input);
                connection.setRequestProperty("Authorization","Basic"+encode);
            }

            connection.connect();

            //print header fields

            Map<String, List<String>> headers = connection.getHeaderFields();
            System.out.println(JSON.toJSONString(headers));
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                for (String s : entry.getValue()) {
                    System.out.println(key+":" + s);
                }
            }

            //print convenience functions

            System.out.println("------------------");
            System.out.println("getContentType:"+connection.getContentType());
            System.out.println("getContentLength:"+connection.getContentLength());
            System.out.println("getContentEncoding:"+connection.getContentEncoding());
            System.out.println("getDate:"+connection.getDate());
            System.out.println("getExpiration:"+connection.getExpiration());
            System.out.println("getLastModified:"+connection.getLastModified());
            System.out.println("------------------");

            Scanner in = new Scanner(connection.getInputStream());

            //print first ten lines of contents

            for (int i = 1; i < 10&&in.hasNext(); i++) {
                System.out.println(in.nextLine());

            }
            if (in.hasNextLine()){
                System.out.println("...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Computes the Base64 encoding of a string
     * @param s
     * @return
     */
    private static String Base64Encode(String s) {
        ByteArrayOutputStream bOut = null;
        try {
            bOut = new ByteArrayOutputStream();
            Base64OutputStream out = new Base64OutputStream(bOut);
            out.write(s.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bOut.toString();
    }
}

/**
 * This stream filter converts a steam of bytes to their Base64 encoding
 *
 * Base64 encoding encodes 3 bytes into 4 characters |11111122|22223333|33444444| Each set
 * of 6 bits is encoded according to the toBase64 map. If the number of input bytes is not a
 * multiple of 3 , then the last group of characters is padded with one or two = signs. Each
 * output line is at most 76 characters
 *
 *
 */

class Base64OutputStream extends FilterOutputStream{

    private int col=0;
    private int i=0;
    private int[] inbuf = new int[3];
    private static char[] toBase64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    /**
     * Creates an output stream filter built on top of the specified
     * underlying output stream.
     *
     * @param out the underlying output stream to be assigned to
     *            the field <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying stream.
     */
    public Base64OutputStream(OutputStream out) {
        super(out);
    }

    public void write(int c) throws IOException{
        inbuf[i]=c;
        i++;
        if (i==3){
            super.write(toBase64[(inbuf[0]&0xFC)>>2]);
            super.write(toBase64[((inbuf[0]&0x03)<<4)|((inbuf[1]&0xf0)>>4)]);
            super.write(toBase64[((inbuf[1]&0x0f)<<2)|((inbuf[2]&0xc0)>>6)]);
            super.write(toBase64[inbuf[2]&0x3f]);
            col +=4;
            i=0;
            if (col>=76){
                super.write('\n');
                col=0;
            }
        }
    }

    public void flush() throws IOException{
        if (i==1){
            super.write(toBase64[(inbuf[0]&0xfc)>>2]);
            super.write(toBase64[(inbuf[0]&0x03)<<4]);
            super.write('=');
            super.write('=');
        }else if(i==2){
            super.write(toBase64[(inbuf[0]&0xFC)>>2]);
            super.write(toBase64[((inbuf[0]&0x03)<<4)|((inbuf[1]&0xf0)>>4)]);
            super.write(toBase64[(inbuf[1]&0x0f)<<2]);
        }
        if (col>0){
            super.write('\n');
            col=0;
        }
    }
}
