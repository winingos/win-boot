package com.comment.reflect;

import java.text.NumberFormat;
import java.util.*;

/**
 * Created by 王宁 on 2017/4/1.
 * http://www.ziwenxie.site/2017/03/22/java-reflection/
 * 解决泛型擦除
 * 我们有一个泛型集合类List<Class<? extends Pet>>，我们需要统计出这个集合类中每种具体的Pet有多少个
 * 由于Java的泛型擦除，注意类似List<? extends Pet>的做法肯定是不行的,因为编译器做了静态类型检查之后
 * 到了运行期间JVM会将集合中的对象都视为Pet，但是并不会知道Pet代表的究竟是Cat还是Dog，
 * 所以到了运行期间对象的类型信息其实全部丢失了
 */
class Pet extends Individual {
    public Pet(String name) {
        super(name);
    }

    public Pet() {
        super();
    }
}

class Cat extends Pet {
    public Cat(String name) {
        super(name);
    }

    public Cat() {
        super();
    }
}

class Dog extends Pet {
    public Dog(String name) {
        super(name);
    }

    public Dog() {
        super();
    }
}

class EgyptianMau extends Cat {
    public EgyptianMau(String name) {
        super(name);
    }

    public EgyptianMau() {
        super();
    }
}

class Mutt extends Dog {
    public Mutt(String name) {
        super(name);
    }

    public Mutt() {
        super();
    }
}


class Individual implements Comparable<Individual> {
    private static long counter = 0;
    private final long id = counter++;
    private String name; // name is optional

    public Individual(String name) {
        this.name = name;
    }

    public Individual() {
    }

    public String toString() {
        return getClass().getSimpleName() + (name == null ? "" : " " + name);
    }

    public long id() {
        return id;
    }

    public boolean equals(Object o) {
        return o instanceof Individual && id == ((Individual) o).id;
    }

    public int hashCode() {
        int result = 17;
        if (name != null) {
            result = 37 * result + name.hashCode();
        }
        result = 37 * result + (int) id;
        return result;
    }

    /**
     * 对象比较
     * @param arg
     * @return
     */
    public int compareTo(Individual arg) {
        // Compare by class name first:
        String first = getClass().getSimpleName();
        String argFirst = arg.getClass().getSimpleName();
        int firstCompare = first.compareTo(argFirst);
        if (firstCompare != 0) {
            return firstCompare;
        }
        if (name != null && arg.name != null) {
            int secendCompare = name.compareTo(arg.name);
            if (secendCompare != 0) {
                return secendCompare;
            }
        }
        return (arg.id < id ? -1 : (arg.id == id ? 0 : 1));
    }
}

/**
 * PetCreator，以后我们通过调用arrayList()方法便可以直接获取相关Pet类的集合
 */
abstract class PetCreator {
    private Random rand = new Random(47);
    // The List of the different getTypes of Pet to create:
    public abstract List<Class<? extends Pet>> getTypes();
    public Pet randomPet() {
        // Create one random Pet
        int n = rand.nextInt(getTypes().size());
        try {
            return getTypes().get(n).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public Pet[] createArray(int size) {
        Pet[] result = new Pet[size];
        for (int i = 0; i < size; i++) {
            result[i] = randomPet();
        }
        return result;
    }
    public ArrayList<Pet> arrayList(int size) {
        ArrayList<Pet> result = new ArrayList<Pet>();
        Collections.addAll(result, createArray(size));
        return result;
    }
}

class LiteralPetCreator extends PetCreator {
    @SuppressWarnings("unchecked")
    public static final List<Class<? extends Pet>> allTypes = Collections.unmodifiableList(
            Arrays.asList(Pet.class, Dog.class, Cat.class, Mutt.class, EgyptianMau.class));
    private static final List<Class<? extends Pet>> types = allTypes.subList(
            allTypes.indexOf(Mutt.class), allTypes.size());
    public List<Class<? extends Pet>> getTypes() {
        return types;
    }
}


public class TypeCounter extends HashMap<Class<?>, Integer> {

    private Class<?> baseType;
    public TypeCounter(Class<?> baseType) {
        this.baseType = baseType;
    }
    public void count(Object obj) {
        Class<?> type = obj.getClass();
        if (!baseType.isAssignableFrom(type)) {
            throw new RuntimeException(
                    obj + " incorrect type " + type + ", should be type or subtype of " + baseType);
        }
        countClass(type);
    }
    private void countClass(Class<?> type) {
        Integer quantity = get(type);
        put(type, quantity == null ? 1 : quantity + 1);
        Class<?> superClass = type.getSuperclass();
        if (superClass != null && baseType.isAssignableFrom(superClass)) {
            countClass(superClass);
        }
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (Map.Entry<Class<?>, Integer> pair : entrySet()) {
            result.append(pair.getKey().getSimpleName());
            result.append("=");
            result.append(pair.getValue());
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("} ");
        return result.toString();
    }

    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Pet.class);
        List<Pet> list = new LiteralPetCreator().arrayList(100);
        for (Pet pet : list) {
            counter.count(pet);
        }
        System.out.println("list = " + counter);

        Double v = Double.parseDouble("4.9E-324");
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(true);
        String dou_str = nf.format(v);
        System.out.println(dou_str);

//        String str="mobile content cinema_code";
//        List<String> strings =new ArrayList<>(Arrays.asList(str.split(" ")));
//        strings.sort(String.CASE_INSENSITIVE_ORDER);
//        System.out.println(Arrays.deepToString(strings.toArray()));

    }
}
