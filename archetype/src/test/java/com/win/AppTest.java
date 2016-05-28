package com.win;

import com.alibaba.fastjson.annotation.JSONField;
import com.win.common.dto.DtoUtils;
import lombok.Data;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Unit test for simple ScheduledTasks.
 */
public class AppTest 
    /*extends TestCase*/
{
//    /**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
//    public AppTest( String testName )
//    {
//        super( testName );
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite()
//    {
//        return new TestSuite( AppTest.class );
//    }
//
//    /**
//     * Rigourous Test :-)
//     */
//    public void testApp()
//    {
//        assertTrue( true );
//    }

    @org.junit.Test
    public void nullTest(){
        Boolean a = null;
        if(a){
            System.out.println("a = " + 4);
        }
    }

    @Data
    static class JavaBean{
        @JSONField(name="t",format = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime time;
    }
    @Data
    static class TestBean{
        String time;
    }
    @Test
    public void test2(){
        JavaBean bean = new JavaBean();
        bean.setTime(LocalDateTime.now());
        TestBean dest = new TestBean();
        DtoUtils.copy(bean, dest);
        JavaBean dest1 = new JavaBean();
        DtoUtils.copy(dest, dest1);
        System.out.println("bean = " + bean);
        System.out.println(dest);
        System.out.println(dest1);
    }


}
