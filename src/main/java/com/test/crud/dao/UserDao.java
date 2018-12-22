package com.test.crud.dao;

import com.test.crud.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    User save(User user);

    User findByIdUser(Long id);

    void update(User user);

    void delete(Long id);
}
