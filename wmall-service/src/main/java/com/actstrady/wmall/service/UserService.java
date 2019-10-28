package com.actstrady.wmall.service;

import com.actstrady.wmall.dao.UserDao;
import com.actstrady.wmall.po.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/10/18 14:52
 * @fileName : UserService.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
public interface UserService {
    User login(User user);
}
