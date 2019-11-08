package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.UserDao;
import com.actstrady.wmall.po.UserPO;
import com.actstrady.wmall.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/10/26 16:09
 * @fileName : UserServiceImpl.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserPO login(UserPO user) {
        return userDao.getByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public Boolean checkUsername(String username) {
        return userDao.getByUsername(username) == null;
    }

    @Override
    public Boolean registered(UserPO user) {
        UserPO saveUser = userDao.save(user);
        return saveUser != null;
    }
}
