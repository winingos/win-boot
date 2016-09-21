package com.thinkinjava.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * Created by ning.wang on 2016/9/19.
 * An exception that reports through
 */
class LoggingException extends Exception {
    public static Logger logger = Logger.getLogger("LoggingException");

    public LoggingException() {
        StringWriter tarce = new StringWriter();
        printStackTrace(new PrintWriter(tarce));
        logger.severe(tarce.toString());
        System.out.println("");
        System.out.println(tarce.toString());
    }
}

public class LoggingExceptons {
    public static void main(String[] args) {
        try {
            throw new LoggingException();
        } catch (LoggingException e) {
            System.err.println("Caught " + e);
        }
        try {
            throw new LoggingException();
        } catch (LoggingException e) {
            System.err.println("Caught " + e);
        }
    }
}

class LoggingExceptions2 {
    private static Logger logger = Logger.getLogger("LoggingExceptions2");
    static void logException(Exception e){
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }

    public static void main(String[] args) {
        try {

            throw new NullPointerException();
        }catch (NullPointerException e){
            logException(e);
            System.err.println("LoggingExceptions2.main");
            e.printStackTrace();
        }
    }
}
