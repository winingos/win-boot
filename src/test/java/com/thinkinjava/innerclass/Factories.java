package com.thinkinjava.innerclass;

/**
 * Created by ning.wang on 2016/9/1.
 */
interface Service{
    void method1();
    void method2();
}
interface ServiceFactory{
    Service getService();
}
class Implementation1 implements Service{

    @Override
    public void method1() {
        System.out.println("Implementation1 method1");
    }

    @Override
    public void method2() {
        System.out.println("Implementation1 method2");
    }
    public static ServiceFactory factory =
            new ServiceFactory() {
                @Override
                public Service getService() {
                    return new Implementation1();
                }
            };
}

class Implementation2 implements Service{

    @Override
    public void method1() {
        System.out.println("Implementation2 method1");
    }

    @Override
    public void method2() {
        System.out.println("Implementation2 method2");
    }

    public static ServiceFactory factory=new ServiceFactory() {
        @Override
        public Service getService() {
            return new Implementation2();
        }
    };
}
public class Factories {
    public static void serviceConsumer(ServiceFactory factory){
        Service s = factory.getService();
        s.method1();
        s.method2();
    }

    public static void main(String[] args) {
        serviceConsumer(Implementation1.factory);
        // Implementations are completely interchangeable:
        serviceConsumer(Implementation2.factory);
    }
}
