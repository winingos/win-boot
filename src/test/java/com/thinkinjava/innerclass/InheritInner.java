package com.thinkinjava.innerclass;

/**
 * Inheriting from inner classes
 * Created by ning.wang on 2016/9/5.
 * reference to the enclosing class object must be initialized
 */
class WithInner{
    class Inner{}
}
public class InheritInner extends WithInner.Inner {
    //InheritInner(){}// Won’t compile
    InheritInner(WithInner wi){
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
}
