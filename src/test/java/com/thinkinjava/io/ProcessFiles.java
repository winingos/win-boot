package com.thinkinjava.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by ning.wang on 2016/10/22.
 */
public class ProcessFiles {
    public interface Strategy{
        void process(File file);
    }
    private Strategy strategy;
    private String ext;
    public ProcessFiles(Strategy strategy,String ext){
        this.strategy=strategy;
        this.ext=ext;
    }
    public void start(String[] args)throws IOException{
            if (args.length==0){
                processDirectoryTree(new File("."));
            }else {
                for (String arg : args) {
                    File fileArg = new File(arg);
                    if (fileArg.isDirectory())
                        processDirectoryTree(fileArg);
                    else {
                        //Allow user to leave off extension
                        if (!arg.endsWith("."+ext))
                            arg+="."+ext;
                        strategy.process(new File(arg).getCanonicalFile());
                    }
                }
            }
        }
    public void processDirectoryTree(File root)throws IOException{
        for(File file : Directory.walk(root.getAbsolutePath(),".*\\."+ext))
            strategy.process(file.getCanonicalFile());

    }
//Demonstration of how to use it:
    public static void main(String[] args)throws IOException {
        new ProcessFiles(new Strategy() {
            @Override
            public void process(File file) {
                System.out.println("file = " + file);
            }
        },"java").start(args);

    }
}
