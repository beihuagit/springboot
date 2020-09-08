package com.xiaole.test.service;

import com.xiaole.test.common.ServerResponse;
import com.xiaole.test.pojo.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getOne(Integer id);

    void add(User user);

    void update(User user);

    void delete(Integer id);

    User getByUsernameAndPassword(String username, String password);

    ServerResponse login(String username, String password);

    void batchInsert(List<User> list);

    void batchUpdate(List<User> list);

}
