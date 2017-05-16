package com.thinkinjava.io;

import com.win.common.dto.DtoUtils;
import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by ning.wang on 2016/10/28.
 * Static functions for reading and writing text files as
 * a single string,and treating a file
 */
public class TextFile extends ArrayList<String> {
    //Read a file as a single string:
    public static String read(String fileName){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
            try {
                String s;
                while ((s=in.readLine())!=null){
                    sb.append(s).append("\n");
                }
            }finally {
                in.close();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
    //Write a single file in one method call:
    public static void write(String fileName,String text){
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try {
                out.print(text);
            }finally {
                out.close();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    //Read a file .spilt by any regular expression:
    public TextFile(String fileName,String splitter){
        super(Arrays.asList(read(fileName).split(splitter)));
        //Regular expression split() often leaves an empty
        //String at the first position
        if (get(0).equals("")) remove(0);
    }
    //Normally read by lines:
    public TextFile(String fileName){
        this(fileName,"\n");
    }
    public void write(String fileName)throws IOException{
        PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
        try {
            for (String item : this) {
                out.println(item);
            }
        } finally {
            out.close();
        }
    }
    //Simple test:
    public static void main(String[] args)throws IOException {
        String file = read("D:\\IdeaWorkSpaec\\win-boot\\src\\test\\java\\com\\thinkinjava\\io\\TextFile.java");
        write("test.txt",file);
        TextFile text = new TextFile("test.txt");
        text.write("test2.txt");
        //Break into unique sorted list of words:
        TreeSet<String> words = new TreeSet<>(new TextFile("D:\\IdeaWorkSpaec\\win-boot\\src\\test\\java\\com\\thinkinjava\\io\\TextFile.java", "\\W+"));
        //Display the capitalized words:
        System.out.println(words.headSet("a"));
    }

}
