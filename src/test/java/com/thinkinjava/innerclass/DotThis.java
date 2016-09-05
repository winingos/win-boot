package com.thinkinjava.innerclass;

/**
 * Created by ning.wang on 2016/8/31.
 * If you need to produce the reference to the outer-class object, you name the outer class
 * followed by a dot and this.
 *
 * Qualifying access to the outer-class object
 */
public class DotThis {
    void f(){
        System.out.println("DotThis.f()");
    }
    public class Inner{
        public DotThis outer(){
            // A plain "this" would be Inner’s "this"
            return DotThis.this;
        }
    }
    public Inner inner(){return new Inner();}
    public static void main(String[] args) {
        DotThis dt= new DotThis();
        DotThis.Inner dti = dt.inner();
        DotThis.Inner dtn = dt.new Inner();
        dti.outer().f();
        dtn.outer().f();
    }
}
