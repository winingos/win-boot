package com.thinkinjava.proxies.samples;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ning.wang on 2016/9/27.
 * ASM 可以直接产生二进制 class 文件
 */
public class MyGenerator {
    public static void main(String[] args)throws IOException {
        System.out.println("");
        ClassWriter classWriter = new ClassWriter(0);
//        通过visit方法确定类的头部信息
        classWriter.visit(Opcodes.V1_8,//java 版本
                Opcodes.ACC_PUBLIC,//类修饰符
                "Programmer",//类的全限定名
                null,"java/lang/Object",null);
//        创建构造函数
        MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD,0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL,"java/lang/Object","<init>","()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1,1);
        mv.visitEnd();
//        定义code方法
        MethodVisitor code = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "code", "()V", null, null);
        code.visitCode();
        code.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System","out","Ljava/io/PringStream;");
        code.visitLdcInsn("I'm a Programmer,Just Coding...");
        code.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"java/io/PrintStream","println","(Ljava/lang/String;)V");
        code.visitInsn(Opcodes.RETURN);
        code.visitMaxs(2,2);
        code.visitEnd();
        classWriter.visitEnd();
//        使classWriter类已经完成
//        将classWriter转换成字节数组写到文件里面去
        byte[] data = classWriter.toByteArray();
        File file = new File("D://Programmer.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }
}
