package com.comment;


import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * Created by 王宁 on 2017/3/16.
 * assertion(断言)
 *  VM arguments里面输入-ea就可以了 打开断言
 */
public class AssertDemo {
    public static void main(String[] args) {

        test1(5);
        test2(-3);
        SpringAssertTest();
    }

    private static void test1(int a){
        assert a > 0;
        System.out.println(a);
    }
    private static void test2(int a){
        /*
         * assert expression1: expression2;
         * expression1表示一个boolean表达式
         * expression2表示一个基本类型、表达式或者是一个Object，用于在失败时输出错误信息
         */
        assert a > 0 : "something goes wrong here, a cannot be less than 0";
        System.out.println(a);
    }

    /**
     * spring Assert 工具不需要开启 -ea
     */
    private static void SpringAssertTest(){
        Assert.hasText("t");
        Assert.isInstanceOf(Object.class,"e");
        Assert.isAssignable(Object.class,Integer.class);
    }
}
