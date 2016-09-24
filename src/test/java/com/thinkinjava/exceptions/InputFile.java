package com.thinkinjava.exceptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ning.wang on 2016/9/19.
 * Paying attention to exceptions in constructors
 */
public class InputFile {
    private BufferedReader in;

    public InputFile(String name) throws Exception {
        try {
            in = new BufferedReader(new FileReader(name));
        }catch (FileNotFoundException e){
            System.out.println("Could not open " + name);
            //Wasn't open .so don't close it
            throw e;
        }catch (Exception e){
            //All other exceptions must close it
            try{
                in.close();
            }catch (IOException ex){
                System.out.println("in.close() unsuccessful");
            }
            throw e;//Rethrow
        }finally {
            //Don't close it here
        }
    }
    public String getLine(){
        String s;
        try {

            s = in.readLine();
        }catch (IOException e){
            throw new RuntimeException("readLine() failed");
        }
        return s;
    }
    public void dispose(){
        try {
            in.close();
            System.out.println("dispose() successful");

        }catch (IOException e){
            throw new RuntimeException("in.close() failed");
        }
    }

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        for (Object o : properties.keySet()) {
            System.out.println( o+"="+properties.get(o));
        }
    }
}
class Cleanup{
    public static void main(String[] args) {
        try {
            InputFile in = new InputFile("src\\test\\java\\com\\thinkinjava\\exceptions\\InputFile.java");

            //System.getProperties()
            try{
                String s;
                int i = 1;
                while((s = in.getLine())!=null){
                    System.out.println(s);
                    ;//Perform line-by-line processing here...
                }

            }catch (Exception e){
                System.out.println("Caught Exception in main");
                e.printStackTrace(System.err);
            }finally {
                in.dispose();
            }
        }catch (Exception e){
            System.out.println("InputFile construction failed");
        }
    }
}
