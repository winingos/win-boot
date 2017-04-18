package com.comment.concurrency;

/**
 * Created by Administrator on 17/4/18.
 * 一个对象如果在创建后不能被修改，那么就称为不可变对象。
 * 在并发编程中，一种被普遍认可的原则就是：尽可能的使用不可变对象来创建简单、可靠的代码
 * <p>
 * 但是程序员们通常并不热衷于使用不可变对象，因为他们担心每次创建新对象的开销。
 * 实际上这种开销常常被过分高估，而且使用不可变对象所带来的一些效率提升也抵消了这种开销。
 * 例如：使用不可变对象降低了垃圾回收所产生的额外开销，也减少了用来确保使用可变对象不出现并发错误的一些额外代码
 */
public class ImmutableObj {
    public static class SynchronizedRGB {

        // Values must be between 0 and 255.
        private int red;
        private int green;
        private int blue;
        private String name;

        private void check(int red,int green,int blue) {
            if (red < 0 || red > 255|| green < 0 || green > 255
                    || blue < 0 || blue > 255) {
                throw new IllegalArgumentException();
            }
        }

        public SynchronizedRGB(int red,int green,int blue,String name) {
            check(red, green, blue);
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }

        public void set(int red,int green,int blue,String name) {
            check(red, green, blue);
            synchronized (this) {
                this.red = red;
                this.green = green;
                this.blue = blue;
                this.name = name;
            }
        }

        public synchronized int getRGB() {
            return ((red << 16) | (green << 8) | blue);
        }

        public synchronized String getName() {
            return name;
        }

        public synchronized void invert() {
            red = 255 - red;
            green = 255 - green;
            blue = 255 - blue;
            name = "Inverse of " + name;
        }
    }

    /**
     * 使用SynchronizedRGB时需要小心，避免其处于不一致的状态。例如一个线程执行了以下代码：
     */
    public void test() {
        SynchronizedRGB color =
                new SynchronizedRGB(0, 0, 0, "Pitch Black");
        //...
        int myColorInt1 = color.getRGB();      //Statement 1
        String myColorName1 = color.getName(); //Statement 2
        /**
         * 如果有另外一个线程在Statement 1之后、Statement 2之前调用了color.set方法，那么myColorInt的值和myColorName的值就会不匹配
         */
        synchronized (color) {
            int myColorInt = color.getRGB();
            String myColorName = color.getName();
        }
    }

    /**
     * 1.不要提供setter方法。（包括修改字段的方法和修改字段引用对象的方法）
     * 2.将类的所有字段定义为final、private的。
     * 3.不允许子类重写方法。简单的办法是将类声明为final，更好的方法是将构造函数声明为私有的，通过工厂方法创建对象。
     * 4.如果类的字段是对可变对象的引用，不允许修改被引用对象。
     *   不提供修改可变对象的方法。
     *   不共享可变对象的引用。当一个引用被当做参数传递给构造函数，而这个引用指向的是一个外部的可变对象时，一定不要保存这个引用。
     *   如果必须要保存，那么创建可变对象的拷贝，然后保存拷贝对象的引用。同样如果需要返回内部的可变对象时，不要返回可变对象本身，而是返回其拷贝。
     */
    final static public class ImmutableRGB {

        // Values must be between 0 and 255.
        final private int red;
        final private int green;
        final private int blue;
        final private String name;

        private void check(int red,int green,int blue) {
            if (red < 0 || red > 255|| green < 0 || green > 255
                    || blue < 0 || blue > 255) {
                throw new IllegalArgumentException();
            }
        }

        public ImmutableRGB(int red,int green, int blue,String name) {
            check(red, green, blue);
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }

        public int getRGB() {
            return ((red << 16) | (green << 8) | blue);
        }

        public String getName() {
            return name;
        }

        public ImmutableRGB invert() {
            return new ImmutableRGB(255 - red,255 - green,255 - blue,"Inverse of " + name);
        }
    }

}
