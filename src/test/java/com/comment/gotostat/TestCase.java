package com.comment.gotostat;

import org.junit.Test;

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
}
