package com.comment.prettyInstance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Mtime on 2016/2/4.
 * 抛出特定异常
 */
public class TestableExceptionHandler {
    public static void process(Class<?> clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        int passed = 0;
        int failed = 0;
        Object obj = clazz.newInstance();
        for(Method method : clazz.getMethods()){
            if(method.isAnnotationPresent(TestableException.class)){
                try {
                    method.invoke(obj,null);
                    ++passed;
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    int oldPassed = passed;
                   for(Class exeType : method.getAnnotation(TestableException.class).value()){
                       if(exeType.isInstance(cause)){
                           ++passed;
                           break;
                       }
                   }
                    if (oldPassed == passed) {
                        ++failed;
                        System.out.printf("Test <%s> failed <%s> %n", method, e);
                    }
                }
            }
        }
        System.out.println("共运行" + (failed + passed) + "个方法, 成功" + passed + "个, 失败" + failed + "个");
    }
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        process(TestCase.class);
    }
}
