package com.service;

import com.model.User;

import java.util.List;

public interface UserService {
    User findUserById(String id);

    List<User> findAllUser();

    User saveUser(User user);
    User findUserByName(String name);

}
