package com.thinkinjava.array;

import java.util.Arrays;
import static com.thinkinjava.array.PrintHelper.*;

/**
 * Created by Administrator on 17/5/14.
 */
public class ConvertTo {
    public static boolean[] primitive(Boolean[] in) {
        boolean[] result = new boolean[in.length];
        for (int i = 0; i < in.length; i++)
            result[i] = in[i]; // Autounboxing
        return result;
    }

    public static char[] primitive(Character[] in) {
        char[] result = new char[in.length];
        for (int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }

    public static byte[] primitive(Byte[] in) {
        byte[] result = new byte[in.length];
        for (int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }

    public static short[] primitive(Short[] in) {
        short[] result = new short[in.length];
        for (int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }

    public static int[] primitive(Integer[] in) {
        int[] result = new int[in.length];
        for (int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }

    public static long[] primitive(Long[] in) {
        long[] result = new long[in.length];
        for (int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }

    public static float[] primitive(Float[] in) {
        float[] result = new float[in.length];
        for (int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }

    public static double[] primitive(Double[] in) {
        double[] result = new double[in.length];
        for (int i = 0; i < in.length; i++)
            result[i] = in[i];
        return result;
    }



    public static void main(String[] args) {
        Integer[] a = Generated.array(Integer.class,new CountingGenerator.Integer(), 15);
        int[] b = ConvertTo.primitive(a);
        System.out.println(Arrays.toString(b));

        boolean[] c = ConvertTo.primitive(Generated.array(Boolean.class,new CountingGenerator.Boolean(), 7));
        System.out.println(Arrays.toString(c));
    }
}

class TestArrayGeneration {
    public static void main(String[] args) {
        int size = 6;
        boolean[] a1 = ConvertTo.primitive(Generated.array(Boolean.class, new RandomGenerator.Boolean(), size));
        print("a1 = " + Arrays.toString(a1));

        byte[] a2 = ConvertTo.primitive(Generated.array(Byte.class, new RandomGenerator.Byte(), size));
        print("a2 = " + Arrays.toString(a2));

        char[] a3 = ConvertTo.primitive(Generated.array(Character.class,new RandomGenerator.Character(), size));
        print("a3 = " + Arrays.toString(a3));

        short[] a4 = ConvertTo.primitive(Generated.array(Short.class, new RandomGenerator.Short(), size));
        print("a4 = " + Arrays.toString(a4));

        int[] a5 = ConvertTo.primitive(Generated.array(Integer.class, new RandomGenerator.Integer(), size));
        print("a5 = " + Arrays.toString(a5));

        long[] a6 = ConvertTo.primitive(Generated.array(Long.class, new RandomGenerator.Long(), size));
        print("a6 = " + Arrays.toString(a6));

        float[] a7 = ConvertTo.primitive(Generated.array(Float.class, new RandomGenerator.Float(), size));
        print("a7 = " + Arrays.toString(a7));

        double[] a8 = ConvertTo.primitive(Generated.array(Double.class, new RandomGenerator.Double(), size));
        print("a8 = " + Arrays.toString(a8));
    }
}
