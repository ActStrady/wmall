package com.actstrady.wmall.service;

import com.actstrady.wmall.po.User;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/10/18 14:52
 * @fileName : UserService.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
public interface UserService {
    /**
     * 登录
     * @param user 用户
     * @return 用户
     */
    User login(User user);

    /**
     * 检查是否以及注册
     * @param username 用户名
     * @return User对象
     */
    Boolean checkUsername(String username);

    /**
     * 注册
     * @param user user对象
     * @return 影响行数
     */
     Boolean registered(User user);
}
