package com.comment.gotostat;

import com.google.common.primitives.Bytes;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by ning.wang on 2016/8/23.
 */
public class TestCase {
    @Test
    public  void test(){
        int y=0;
        continuehere:
        for (int i = 0; i < 10; i++) {
            if(i==6){
                continue continuehere;
            }
            System.out.println("i = " + i);
        }
        System.out.println("------------------------------------");

        breakhere:

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <10 ; j++) {
                System.out.println("j = " + j);
                if(i==6){
                    break;
                }
            }
            System.out.println("i = " + i);
        }

    }
    @Test
    public void test1(){
        byte[] bytes = Base64.encodeBase64("Nzc3Nzc".getBytes());
        for (byte aByte : bytes) {
            System.out.println("aByte = " + (char)aByte);
        }

        UUID uuid = UUID.randomUUID();
        System.out.println("bytes = " +uuid.toString());
    }
}
