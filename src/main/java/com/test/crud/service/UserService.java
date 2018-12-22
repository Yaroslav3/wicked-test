package com.test.crud.service;


import com.test.crud.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User save (User user);

    User findByIdUser(Long id);

    void update(User user);

    void delete(Long id);
}
