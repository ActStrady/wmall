package com.actstrady.wmall.service;

import com.actstrady.wmall.dao.UserDao;
import com.actstrady.wmall.po.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/10/18 14:52
 * @fileName : UserService.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public List<User> getAll() {
        return userDao.findAll();
    }
}
