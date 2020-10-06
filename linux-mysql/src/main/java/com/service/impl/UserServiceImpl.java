package com.service.impl;

import cn.hutool.core.util.PageUtil;
import com.dao.AUserDao;
import com.dao.UserDao;
import com.model.AUser;
import com.model.User;
import com.service.UserService;
import com.util.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassNmae UserServiceImpl
 * @Discription
 * @Author wh
 * @Date 2020/10/1  19:01
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    AUserDao AuserDao;

    @Override
    public PageResultUtil queryAUserByPage(int page, int pageSize) {
        int count = AuserDao.queryPageCout();
//        int totalPage = PageUtil.totalPage(count, pageSize);
        int start = PageUtil.getStart(page, pageSize);
//        int end = PageUtil.getEnd(page, pageSize);
        List<AUser> aUsers = AuserDao.queryAllAUser(start,pageSize);
        return new PageResultUtil(count,aUsers,page,pageSize);
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public User findUserById(String id) {
        return userDao.findUserById(id);
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAll();
    }
}
