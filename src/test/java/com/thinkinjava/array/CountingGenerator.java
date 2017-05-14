package com.thinkinjava.array;

import java.math.BigDecimal;

/**
 * Created by Administrator on 17/5/14.
 */
public class CountingGenerator {
    public static class Boolean implements Generator<java.lang.Boolean> {
        private boolean value = false;

        public java.lang.Boolean next() {
            value = !value; // Just flips back and forth
            return value;
        }
    }

    public static class Byte implements Generator<java.lang.Byte> {
        private byte value = 0;

        public java.lang.Byte next() {
            return value++;
        }
    }

    static char[] chars = ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    public static class Character implements Generator<java.lang.Character> {
        int index = -1;

        public java.lang.Character next() {
            index = (index + 1) % chars.length;
            return chars[index];
        }
    }

    public static class String implements Generator<java.lang.String> {
        private int length = 7;
        Generator<java.lang.Character> cg = new Character();

        public String() {
        }

        public String(int length) {
            this.length = length;
        }

        public java.lang.String next() {
            char[] buf = new char[length];
            for (int i = 0; i < length; i++)
                buf[i] = cg.next();
            return new java.lang.String(buf);
        }
    }

    public static class Short implements Generator<java.lang.Short> {
        private short value = 0;

        public java.lang.Short next() {
            return value++;
        }
    }

    public static class Integer implements Generator<java.lang.Integer> {
        private int value = 0;

        public java.lang.Integer next() {
            return value++;
        }
    }

    public static class Long implements Generator<java.lang.Long> {
        private long value = 0;

        public java.lang.Long next() {
            return value++;
        }
    }

    public static class Float implements Generator<java.lang.Float> {
        private float value = 0;

        public java.lang.Float next() {
            float result = value;
            value += 1.0;
            return result;
        }
    }

    public static class Double implements Generator<java.lang.Double> {
        private double value = 0.0;

        public java.lang.Double next() {
            double result = value;
            value += 1.0;
            return result;
        }
    }

    public static class BigDecimal implements Generator<java.math.BigDecimal> {
        private double value = 0.0;

        public java.math.BigDecimal next() {
            java.math.BigDecimal count = new java.math.BigDecimal(0);
            count.add(new java.math.BigDecimal(1.03));
            return count;
        }
    }

}

class GeneratorsTest {
    public static int size = 10;

    public static void test(Class<?> surroundingClass) {
        for (Class<?> type : surroundingClass.getClasses()) {
            System.out.print(type.getSimpleName() + ": ");
            try {
                Generator<?> g = (Generator<?>) type.newInstance();
                for (int i = 0; i < size; i++)
                    System.out.printf(g.next() + " ");
                System.out.println();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        test(CountingGenerator.class);
//        GeneratorsTest.size = 30;
//        test(RandomGenerator.class);
//        SkipGenerator.step=3;
//        test(SkipGenerator.class);
    }
}

