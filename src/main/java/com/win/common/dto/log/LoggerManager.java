package com.win.common.dto.log;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class LoggerManager {
   public static final Logger getLogger(Class klass){
       return new Logger();
   }
}
