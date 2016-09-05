package com.thinkinjava.innerclass;

/**
 * Can inner classes be overridden
 * Created by ning.wang on 2016/9/5.
 *  An inner class cannot be overriden like a method
 */

class Egg{
    private Yolk y;
    protected class Yolk{
        public Yolk(){
            System.out.println("Egg.Yolk()");
        }
    }
    public Egg(){
        System.out.println("New Egg()");
        y = new Yolk();
    }
}
public class BigEgg extends Egg {
    public class Yolk{
        public Yolk(){
            System.out.println("BigEgg.Yolk()");
        }
    }

    public static void main(String[] args) {
        new BigEgg();
    }
}

/**
 * Proper inheritance of an inner class.
 */
class Egg2 {
    protected class Yolk {
        public Yolk() { System.out.println("Egg2.Yolk()"); }
        public void f() { System.out.println("Egg2.Yolk.f()");}
    }
    private Yolk y = new Yolk();
    public Egg2() { System.out.println("New Egg2()"); }
    public void insertYolk(Yolk yy) { y = yy; }
    public void g() { y.f(); }
}

class BigEgg2 extends Egg2{
    public class Yolk extends Egg2.Yolk{
     public Yolk(){
         System.out.println("BigEgg2.Yolk()");
     }
        public void f(){
            System.out.println("BigEgg2.Yolk.f()");
        }
    }
    public BigEgg2(){
        insertYolk(new Yolk());
    }

    public static void main(String[] args) {
        new BigEgg2().g();
    }
}

