package com.thinkinjava.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 王宁 on 2017/4/1.
 */
public interface IFactory<T> {
    T create();
}

class Filter extends Part {}
class FuelFilter extends Filter {
    public static class Factory implements IFactory<FuelFilter> {
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}
class AirFilter extends Filter {
    public static class Factory implements IFactory<AirFilter> {
        public AirFilter create() {
            return new AirFilter();
        }
    }
}

class Belt extends Part {}
class FanBelt extends Belt {
    public static class Factory implements IFactory<FanBelt> {
        public FanBelt create() {
            return new FanBelt();
        }
    }
}
class GeneratorBelt extends Belt {
    public static class Factory implements IFactory<GeneratorBelt> {
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}

class PowerSteeringBelt extends Belt{
    public static class Factory implements IFactory<PowerSteeringBelt> {
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
    }
}

/**
 * Part类的实现如下，注意我们上面的实体类都是Part类的间接子类。在Part类我们注册了我们上面的声明的实体类
 * 所以以后我们如果要创建相关的实体类的话，只需要在调用Part类的相关方法了
 * 这么做的一个好处就是如果的业务中出现了CabinAirFilter或者PowerSteeringBelt的话，
 * 我们不需要修改太多的代码，只需要在Part类中将它们注册即可
 */
class Part {
    static List<IFactory<? extends Part>> partFactories =
            new ArrayList<IFactory<? extends Part>>();
    static {
        partFactories.add(new FuelFilter.Factory());
        partFactories.add(new AirFilter.Factory());
        partFactories.add(new FanBelt.Factory());
        partFactories.add(new PowerSteeringBelt.Factory());
    }
    private static Random rand = new Random(47);
    public static Part createRandom() {
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }
    public String toString() {
        return getClass().getSimpleName();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Part.createRandom());
        }
    }
}