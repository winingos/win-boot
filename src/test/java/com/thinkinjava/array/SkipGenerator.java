package com.thinkinjava.array;

/**
 * Created by Administrator on 17/5/14.
 */
abstract class SkipAbstract<T> implements Generator<T>{
    int s;
    Generator<T> gen;
    public SkipAbstract(int step,Generator g){
        this.s=step;
        this.gen=g;
    }
    public T next(){
        int i=s;
        while(--i>0){
            gen.next();
        }

        return gen.next();
    }
}
abstract class SkipAbstract2<T> implements Generator<T>{
    int s;
    Generator gen;
    public SkipAbstract2(int step){
        this.s=step;
        Class<?>[] classes = CountingGenerator.class.getClasses();
        for (Class<?> aClass : classes) {
            if (this.getClass().getSimpleName().equals(aClass.getSimpleName())){
                try {
                    gen=(Generator<?>)aClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (gen==null){
            throw new RuntimeException("没有匹配的类型:"+this.getClass().getSimpleName());
        }
    }
    public T next(){
        int i=s;
        while(--i>0){
            gen.next();
        }

        return (T) gen.next();
    }
}
public class SkipGenerator {
        public static int step=3;

    public static class Boolean extends SkipAbstract<java.lang.Boolean> {
        public Boolean(){
            super(step,new CountingGenerator.Boolean());
        }
        public Boolean(int step){
            super(step,new CountingGenerator.Boolean());
        }
    }
    public static class Byte extends SkipAbstract<java.lang.Byte>{

        public Byte(){
            super(step,new CountingGenerator.Byte());
        }
        public Byte(int step){
            super(step,new CountingGenerator.Byte());
        }
    }

    public static class Character extends SkipAbstract<java.lang.Character>{
        public Character(){
            super(step,new CountingGenerator.Character());
        }
        public Character(int step){
            super(step,new CountingGenerator.Character());
        }
    }

    public static class Double extends SkipAbstract<java.lang.Double>{
        public Double(){
            super(step,new CountingGenerator.Double());
        }
        public Double(int step){
            super(step,new CountingGenerator.Double());
        }
    }

    public static class Float extends SkipAbstract<java.lang.Float>{
        public Float(){
            super(step,new CountingGenerator.Float());
        }
        public Float(int step){
            super(step,new CountingGenerator.Float());
        }
    }

    public static class Integer extends SkipAbstract<java.lang.Integer>{
        public Integer(){
            super(step,new CountingGenerator.Integer());
        }
        public Integer(int step){
            super(step,new CountingGenerator.Integer());
        }
    }

    public static class Long extends SkipAbstract<java.lang.Long>{
        public Long(){
            super(step,new CountingGenerator.Long());
        }
        public Long(int step){
            super(step,new CountingGenerator.Long());
        }
    }

    public static class Short extends SkipAbstract<java.lang.Short>{
        public Short(){
            super(step,new CountingGenerator.Short());
        }
        public Short(int step){
            super(step,new CountingGenerator.Short());
        }
    }

    public static class String extends SkipAbstract<java.lang.String>{
        public String(){
            super(step,new CountingGenerator.String(step));
        }
        public String(int step){
            super(step,new CountingGenerator.String(step));
        }
    }

}

class SkipGenerator2{
    public static int step=5;

    public static class Boolean extends SkipAbstract2<java.lang.Boolean> {
        public Boolean(){
            super(step);
        }
        public Boolean(int step){
            super(step);
        }
    }
    public static class Byte extends SkipAbstract2<java.lang.Byte>{

        public Byte(){
            super(step);
        }
        public Byte(int step){
            super(step);
        }
    }

    public static class Character extends SkipAbstract2<java.lang.Character>{
        public Character(){
            super(step);
        }
        public Character(int step){
            super(step);
        }
    }

    public static class Double extends SkipAbstract2<java.lang.Double>{
        public Double(){
            super(step);
        }
        public Double(int step){
            super(step);
        }
    }

    public static class Float extends SkipAbstract2<java.lang.Float>{
        public Float(){
            super(step);
        }
        public Float(int step){
            super(step);
        }
    }

    public static class Integer extends SkipAbstract2<java.lang.Integer>{
        public Integer(){
            super(step);
        }
        public Integer(int step){
            super(step);
        }
    }

    public static class Long extends SkipAbstract2<java.lang.Long>{
        public Long(){
            super(step);
        }
        public Long(int step){
            super(step);
        }
    }

    public static class Short extends SkipAbstract2<java.lang.Short>{
        public Short(){
            super(step);
        }
        public Short(int step){
            super(step);
        }
    }

    public static class String extends SkipAbstract2<java.lang.String>{
        public String(){
            super(step);
        }
        public String(int step){
            super(step);
        }
    }
}
