package com.win.jdbc;

import com.win.jdbc.batis.Blog;
import com.win.jdbc.batis.BlogMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 18/4/30.
 */
public class spr_batTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-mybatis.xml");
        BlogMapper mapper = (BlogMapper) context.getBean("blogMapper");
        mapper.insertBlog(new Blog(111,"spring"));
        System.out.println(mapper.getBlogList());
    }
}
