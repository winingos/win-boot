package com.comment.design_pattern;

/**
 * Created by 王宁 on 2017/7/11.
 * 装饰对象和真实对象有相同的接口
 * 装饰对象包含一个真实对象的引用
 * 装饰对象接受所有来自客户端的请求。它把这些请求转发给真实的对象。
 * 装饰对象可以在转发这些请求以前或以后增加一些附加功能。这样就确保了在运行时，不用修改给定对象的结构就可以在外部增加附加的功能。在面向对象的设计中，通常是通过继承来实现对给定类的功能扩展。继承不能做到这一点，继承的功能是静态的，不能动态增删
 */

interface Sourceable {
    public void method();
}



public class Decorator  implements Sourceable{
    private Sourceable source;

    public Decorator(Sourceable source){
        super();
        this.source=source;
    }
    public Decorator(){
        super();
        this.source=new Source();
    }
    @Override
    public void method() {
        System.out.println("before decorator");
        source.method();
        System.out.println("after decorator");
    }

    class Source implements Sourceable {

        @Override
        public void method() {
            System.out.println("the original method!");
        }
    }

    public static void main(String[] args) {
        Decorator decorator = new Decorator();
        decorator.method();

    }

}
