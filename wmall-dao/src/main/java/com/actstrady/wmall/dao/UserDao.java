package com.actstrady.wmall.dao;

import com.actstrady.wmall.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 使用RepositoryRestResource注解表示自定义路径
 * 默认是表的名称的复数，如这里是users
 *
 * @author : ActStrady@tom.com
 * @date : 2019/10/18 14:23
 * @fileName : UserDao.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
public interface UserDao extends JpaRepository<User, Integer> {
    User getUserByUsernameAndPassword(String username, String password);
    User getUserByUsername(String username);
}
