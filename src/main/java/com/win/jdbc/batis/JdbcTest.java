package com.win.jdbc.batis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 18/4/29.
 */
public class JdbcTest {
    @Test
    public void makeSqlSession() throws Exception {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
//            mapper.insertBlog(new Blog(102,"insert"));
//            mapper.updateBlog(new Blog(1, "update"));
            mapper.deleteBlog(102);
            session.commit();

            List<Blog> list = mapper.getBlogList();

            System.out.println(list);
        } finally {
            session.close();
        }
    }

//    public void buildSqlSessionWithOutXml()throws Exception{
//        DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
//        TransactionFactory transactionFactory =
//                new JdbcTransactionFactory();
//        Environment environment =
//                new Environment("development", transactionFactory, dataSource);
//        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(BlogMapper.class);
//        SqlSessionFactory sqlSessionFactory =
//                new SqlSessionFactoryBuilder().build(configuration);
//
//
//
//    }
}
