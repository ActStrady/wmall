package com.actstrady.wmall.web.controller;

import com.actstrady.wmall.po.User;
import com.actstrady.wmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/10/18 14:59
 * @fileName : UserController.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getAll() {
        return userService.getAll();
    }
}
