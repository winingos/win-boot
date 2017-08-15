package com.comment.design_pattern;

/**
 * Created by 王宁 on 2017/7/11.
 */
class Source {
    public void method1() {
        System.out.println("this is original method!");
    }
}

interface Targetable {
    /* 与原类中的方法相同 */
    public void method1();

    /* 新类的方法 */
    public void method2();
}
public class Adapter extends Source implements Targetable {
    @Override
    public void method2() {
        System.out.println("method2");
    }

    public static void main(String[] args) {
        Targetable adapter = new Adapter();
        adapter.method1();
        adapter.method2();
    }
}

class Wrapper implements Targetable{
    private Source source;

    public Wrapper(Source source){
        super();
        this.source=source;
    }

    @Override
    public void method1() {
       source.method1();
    }

    @Override
    public void method2() {
        System.out.println("method2");
    }

    public static void main(String[] args) {
        Source source = new Source();
        Targetable wrapper = new Wrapper(source);
        wrapper.method1();
        wrapper.method2();
    }
}
