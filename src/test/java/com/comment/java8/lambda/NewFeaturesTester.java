package com.comment.java8.lambda;

/**
 * Created by 王宁 on 2017/1/24.
 */
public class NewFeaturesTester {
    public static void main(String[] args) {

        NewFeaturesTester tester = new NewFeaturesTester();

        MathOperation addition = (a,b)->a+b;
        MathOperation subtraction = (a,b)->a-b;
        MathOperation multiplication = (a,b)->a*b;
        MathOperation division = (a,b)->a/b;



        // 输出结果
        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        GreetingService gs=message -> System.out.println("Hello "+ message);
        gs.sayMessage("Shiyanlou");

    }
    // 下面是定义的一些接口和方法

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
}
