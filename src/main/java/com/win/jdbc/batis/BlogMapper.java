package com.win.jdbc.batis;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by Administrator on 18/4/29.
 */
@Mapper
public interface BlogMapper {
//    @Select("SELECT * FROM blog WHERE id = #{id}")
    Blog selectBlog(int id);


//    @Insert("Insert into blog(id,content)values(#{id},#{content})")
//    @SelectKey(statement="",keyProperty="id", before=false, resultType=int.class,)
    void insertBlog(Blog blog);
    List<Blog> getBlogList();
    void updateBlog(Blog Blog);
    void deleteBlog(int BlogId);

}
