package com.comment;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王宁 on 2018/2/27.
 */
public class ObjectList {

    public static void main(String[] args) {
        int count = 100000;
        List<TestObject> list = new ArrayList<>(count);
        List<TestObject> list1 = new ArrayList<>(count);
        for (int j = 0; j < 100; j++) {
            long l = System.currentTimeMillis();
            TestObject obj;
            for (int i = 0; i < count; i++) {
                obj = new TestObject();
                obj.setOnes(i + "");
                obj.setTwoi(i);
                list.add(obj);
            }
            long l1 = System.currentTimeMillis() - l;


            long l2 = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                TestObject obj1 = new TestObject();
                obj1.setOnes(i + "");
                obj1.setTwoi(i);
                list1.add(obj1);
            }
            long l3 = System.currentTimeMillis() - l2;
            if (j == count - 1)
                System.out.println("l3 = " + l3 + "      l1=" + l1);
        }


    }
}

@Getter
@Setter
class TestObject {
    String ones;
    Integer twoi;
}
