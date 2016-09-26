package com.thinkinjava.typeinfo.toys;

/**
 * Created by ning.wang on 2016/9/22.
 * Testing class Class
 */
interface HasBatteries{}
interface Waterproof{}
interface Shoots{}
class Toy{
    //Comment out the following default constructor
//    to see NoSuchMethodError form(*1*)
    Toy(){}
    Toy(int i){}
}
class FancyToy extends Toy implements HasBatteries,Waterproof,Shoots{
    FancyToy(){super(1);}
}
public class ToyTest {
    static void printInfo(Class cc){
        System.out.println("Class name: "+cc.getName()+
        " is interface? [" +cc.isInterface()+"]");
        System.out.println("Simple name:"+cc.getSimpleName());
        System.out.println("Canonical name:"+cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class c=null;
        try {
            c=Class.forName("com.thinkinjava.typeinfo.toys.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("Cant find FancyToy" );
            System.exit(1);
        }
        printInfo(c);
        for (Class face : c.getInterfaces()) {
            printInfo(face);
        }
        Class up = c.getSuperclass();
        Object obj =null;
        //Requires default constructor
        try {
           obj = up.newInstance();
        } catch (InstantiationException e) {
            System.err.println("Cannot instantiate");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.err.println("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }

}
