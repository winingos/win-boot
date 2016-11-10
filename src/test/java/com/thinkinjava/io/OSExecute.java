package com.thinkinjava.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by Mtime on 2016/11/1.
 * Run an operating system command
 * and send the output to the console
 */
public class OSExecute {
    public static void command(String command){
        boolean err =false;
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader result = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s=result.readLine())!=null)
                System.out.println("s = " + s);
            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream(),Charset.forName("GBK")));
            // Report errors and return nonzero value
            // to calling process if there are problems
            while ((s=errors.readLine())!=null){
                System.err.println(s);
                err =true;
            }
        } catch (IOException e) {
            // Compensate for windows 2000,which throws an
            // exception for the default command line:
//            if (!command.startsWith("CMD/C"))
//                command("CMD/C"+command);
//            else
                throw new RuntimeException(e);
        }
        if(err)
            throw new OSExecuteException("Errors executing "+command);
    }

    public static void main(String[] args) {
        OSExecute.command("javac");
    }
}
class OSExecuteException extends RuntimeException{
    public OSExecuteException(String why){super(why);}
}
