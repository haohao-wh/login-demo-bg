package com.service;

import com.model.User;
import com.util.PageResultUtil;

import java.util.List;

public interface UserService {
    User findUserById(String id);

    List<User> findAllUser();

    User saveUser(User user);

    User findUserByName(String name);

    PageResultUtil queryAUserByPage(int page, int pageSize);

}
