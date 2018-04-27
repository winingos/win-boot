package com.win.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * Created by Administrator on 17/12/10.
 */
public class UserServiceImpl implements UserService {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into user(name,age,sex)values(?,?,?)",
                new Object[]{user.getName(), user.getAge(), user.getSex()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR}
        );
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("select * from user", new UserMapper());
    }
}
