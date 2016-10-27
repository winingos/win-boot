package com.thinkinjava.io;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by ning.wang on 2016/10/26.
 */
public class MemoryInput {
    public static void main(String[] args)
            throws IOException {
        StringReader in = new StringReader(
                BufferedInputFile.read("D:\\IdeaWorkSpaec\\win-boot\\src\\test\\java\\com\\thinkinjava\\io\\MemoryInput.java"));
        int c;
        while((c = in.read()) != -1)
            System.out.print((char)c);
    }
}
