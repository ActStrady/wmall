package com.actstrady.wmall.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import po.UserPO;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/10/12 11:27
 * @fileName : UserController.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public UserPO getUser() {
        return new UserPO("2", "2", "2", "2");
    }
}
