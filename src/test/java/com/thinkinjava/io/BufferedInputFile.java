package com.thinkinjava.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ning.wang on 2016/10/26.
 */
public class BufferedInputFile {
    //Throw exceptions to console
    public static String read(String fileName)throws IOException{
        //Reading input by lines:
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String s;
        StringBuilder stringBuilder = new StringBuilder();
        while ((s =reader.readLine())!=null){
            stringBuilder.append(s).append("\n");
        }
        reader.close();
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException{
        System.out.println(read("D:\\IdeaWorkSpaec\\win-boot\\src\\test\\java\\com\\thinkinjava\\io\\BufferedInputFile.java"));
    }
}
