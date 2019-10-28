package com.actstrady.wmall.web.controller;

import com.actstrady.wmall.po.User;
import com.actstrady.wmall.service.UserService;
import com.actstrady.wmall.utils.Constant;
import com.actstrady.wmall.utils.Result;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/10/18 14:59
 * @fileName : UserController.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final Result<User> result;

    @Autowired
    public UserController(UserService userService, Result<User> result) {
        this.userService = userService;
        this.result = result;
    }

    @PostMapping("login")
    public Result<User> login(User user) {
        if (userService.login(user) == null){
            result.setStatus(Constant.ONE_SHORT);
            result.setCode(Constant.LOGIN_ERR);
            result.setData(null);
            return result;
        }
        result.setStatus(Constant.ZERO_SHORT);
        result.setData(userService.login(user));
        result.setCode(Constant.ZERO);
        return result;
    }
}
