package com.win.jdbc;

import java.util.List;

/**
 * Created by Administrator on 17/12/10.
 */
public interface UserService {
    void save(User user);

    List<User> getUsers();
}
