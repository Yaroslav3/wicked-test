package com.test.crud.service.impl;

import com.test.crud.dao.UserDao;
import com.test.crud.model.User;
import com.test.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll()
                .stream()
                .sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User findByIdUser(Long id) {
        return userDao.findByIdUser(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
