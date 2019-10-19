package com.actstrady.wmall.dao;

import com.actstrady.wmall.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/10/18 14:23
 * @fileName : UserDao.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
public interface UserDao extends JpaRepository<User, Integer> {
}
