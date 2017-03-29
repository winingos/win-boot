package com.thinkinjava.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by 王宁 on 2017/3/29.
 */
public class Util {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }

    public static <T> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            // if (e > elem)  // compiler error
            ++count;
        return count;
    }

    /**
     * 边界符
     * 类型参数T代表的都是实现了Comparable接口的类
     *
     * @param anArray
     * @param elem
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)
                ++count;
        return count;
    }


    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<>(1, "apple");
        Pair<Integer, String> p2 = new Pair<>(2, "pear");
        boolean same = Util.compare(p1, p2);
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

/**
 * 通配符
 */
class Fruit {
}

class Apple extends Fruit {
}

class Orange extends Fruit {
}

class GenericReading {
    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruit = Arrays.asList(new Fruit());

    static class Reader<T> {
        T readExact(List<T> list) {
            return list.get(0);
        }
    }

    static void f1() {
        Reader<Fruit> fruitReader = new Reader<Fruit>();
//         Errors: List<Fruit> cannot be applied to List<Apple>.
//         Fruit f = fruitReader.readExact(apples);
    }

    /**
     * 使用通配符解决f1()中的问题
     *
     * @param <T>
     */
    static class CovariantReader<T> {
        /**
         * readCovariant方法接受的参数只要是满足Fruit的子类就行
         *
         * @param list
         * @return
         */
        T readCovariant(List<? extends T> list) {
            return list.get(0);
        }
    }

    static void f2() {
        CovariantReader<Fruit> fruitReader = new CovariantReader<Fruit>();
        Fruit f = fruitReader.readCovariant(fruit);
        Fruit a = fruitReader.readCovariant(apples);
    }

    public static void main(String[] args) {
        f2();
    }
}

/**
 * PECS原则
 * “Producer Extends” - 如果你需要一个只读List，用它来produce T，那么使用? extends T。
 * “Consumer Super” - 如果你需要一个只写List，用它来consume T，那么使用? super T。
 * 如果需要同时读取以及写入，那么我们就不能使用通配符了。
 */
//泛型读
class GenericsAndCovariance {
    public static void main(String[] args) {
        // Wildcards allow covariance:
        /**
         * 所以对于实现了<? extends T>的集合类只能将它视为Producer向外提供(get)元素
         */
        List<? extends Fruit> flist = new ArrayList<Apple>();
        // Compile Error: can't add any type of object:
//         flist.add(new Apple())
//         flist.add(new Orange())
//         flist.add(new Fruit())
//         flist.add(new Object())
        flist.add(null); // Legal but uninteresting
        // We Know that it returns at least Fruit:
        Fruit f = flist.get(0);
    }
}

//泛型写
class GenericWriting {
    static List<Apple> apples = new ArrayList<Apple>();
    static List<Fruit> fruit = new ArrayList<Fruit>();

    static <T> void writeExact(List<T> list, T item) {
        list.add(item);
    }

    static void f1() {
        writeExact(apples, new Apple());
        writeExact(fruit, new Apple());
    }

    /**
     * “Consumer Super” - 如果你需要一个只写List，用它来consume T，那么使用? super T
     *
     * @param list
     * @param item
     * @param <T>
     */
    static <T> void writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }

    static void f2() {
        writeWithWildcard(apples, new Apple());
        writeWithWildcard(fruit, new Apple());
    }

    public static void main(String[] args) {
        f1();
        f2();
    }
}

/**
 * 如果需要同时读取以及写入，那么我们就不能使用通配符了
 */
class Collections {
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++)
            dest.set(i, src.get(i));
    }
}

/**
 * 类型擦除
 */
class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }
    // ...

    //在Java中不允许创建泛型数组，类似下面这样的做法编译器会报错：
    public void arrayTest() {
        //List<Integer>[] arrayOfLists = new List<Integer>[2];  // compile-time error
        Object[] strings = new String[2];
        strings[0] = "hi";   // OK
        strings[1] = 100;    // An ArrayStoreException is thrown.

    }

    /**
     * http://www.ziwenxie.site/2017/03/01/java-generic/
     * @param args
     */
    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2); // true
    }
}
