package com.win.common.dto.log;


import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;

/**
 * Created by ning.wang on 2016/5/31.
 */

public class LoggerManager {
//    static {
//        String configPath = ConfigManager.findConfigPath("log4j", ".conf", ".xml");
//        if (configPath == null) {
//            logInternal("config > log4j.conf/log4j.xml can't be found in [%s], default settings will be used", ConfigManager.getConfigDir());
//            setDefaultConfig();
//        } else {
//            try {
//                DOMConfigurator.configure(configPath);
//            } catch (Exception ex) {
//                logInternal("config > failed to load log4j.conf at %s: %s-%s", configPath, ex.getMessage(), Exceptions.getStackTrace(ex));
//            }
//        }
//    }

//    public static boolean hasLogger(String name) {
//        try {
//            return org.apache.log4j.LogManager.exists(name) != null;
//        } catch (NoSuchMethodError e) {
//            // TODO: to dig why not found in some project in fact 1.2.6 existing ?
//            logInternal("not found method: org.apache.log4j.LogManager.exists");
//            return false;
//        }
//    }

    public static Logger getLogger(Class clazz) {
        return null;
    }

//    public static mtime.lark.util.log.Logger getLogger(String name) {
//        return new mtime.lark.util.log.Logger.LoggerImp(org.slf4j.LoggerFactory.getLogger(name));
//    }

    // TODO: to support more output, e.g.log into java.util.logging and then to console by default
    private static void logInternal(String msg) {
        System.out.println(msg);
    }

    private static void logInternal(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static void setDefaultConfig() {
        Properties properties = new Properties();

        properties.put("log4j.rootLogger", "DEBUG,C,F");
//        properties.put("log4j.logger.mtime.lark.net.rpc.server.$stats", "INFO,R");
//        properties.put("log4j.additivity.mtime.lark.net.rpc.server.$stats", "false");

        properties.put("log4j.appender.C", "org.apache.log4j.ConsoleAppender");
        properties.put("log4j.appender.C.Threshold", "INFO");
        properties.put("log4j.appender.C.layout", "org.apache.log4j.PatternLayout");
        properties.put("log4j.appender.C.layout.ConversionPattern", "[%-5p] %d{yyyy-MM-dd HH:mm:ss.SSS} %m%n");

        properties.put("log4j.appender.F", "mtime.lark.util.log.DailyFileAppender");
        properties.put("log4j.appender.F.Threshold", "INFO");
        properties.put("log4j.appender.F.File", "default.log");
        properties.put("log4j.appender.F.layout", "org.apache.log4j.PatternLayout");
        properties.put("log4j.appender.F.layout.ConversionPattern", "%d{yy-MM-dd HH:mm:ss} %-5p %m%n");

//        properties.put("log4j.appender.R", "mtime.lark.util.log.DailyFileAppender");
//        properties.put("log4j.appender.R.Threshold", "INFO");
//        properties.put("log4j.appender.R.File", "rpc.log");
//        properties.put("log4j.appender.R.layout", "org.apache.log4j.PatternLayout");
//        properties.put("log4j.appender.R.layout.ConversionPattern", "%m%n");

        PropertyConfigurator.configure(properties);
    }
}


