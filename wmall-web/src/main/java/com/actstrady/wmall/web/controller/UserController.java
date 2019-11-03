package com.actstrady.wmall.web.controller;

import com.actstrady.wmall.po.User;
import com.actstrady.wmall.service.PreferService;
import com.actstrady.wmall.service.UserService;
import com.actstrady.wmall.utils.Result;
import com.actstrady.wmall.vo.PreferList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    private final PreferService preferService;
    private final Result result;

    @Autowired
    public UserController(UserService userService, Result result, PreferService preferService) {
        this.userService = userService;
        this.preferService = preferService;
        this.result = result;
    }

    /**
     * 登录功能
     *
     * @param userMod     前端传入的user
     * @param httpSession spring的模型类
     * @return 成功就是用户的id，0表示失败
     */
    @PostMapping("login")
    public int login(@RequestBody User userMod, HttpSession httpSession) {
        User user = userService.login(userMod);
        if (user == null) {
            return 0;
        } else {
            // 保存到session
            httpSession.setAttribute("user", user);
            return user.getId();
        }
    }

    /**
     * 退出功能
     *
     * @param sessionStatus session状态
     * @return 成功
     */
    @GetMapping("logout")
    public Boolean execute(SessionStatus sessionStatus) {
        // 使session失效
        sessionStatus.setComplete();
        return sessionStatus.isComplete();
    }

    /**
     * 判断session里是否有user
     *
     * @param model 模型
     * @return 是否成功
     */
    @GetMapping("checkLogin")
    public boolean isLogin(Model model) {
        return model.getAttribute("user") != null;
    }

    /**
     * 查询是否已经有用户
     *
     * @param data 传来的用户名
     * @return 成功否
     */
    @GetMapping("check/{data}")
    public Boolean check(@PathVariable("data") String data) {
        return userService.checkUsername(data);
    }

    /**
     * 注册
     *
     * @param user 用户
     * @return 成功否
     */
    @PostMapping("register")
    public Boolean register(@RequestBody User user) {
        return userService.registered(user);
    }

    /**
     * 查询喜好列表
     * @param httpSession
     * @return
     */
    @GetMapping("checkPrefer")
    public boolean checkPrefer(HttpSession httpSession) {
        int userId = ((User) httpSession.getAttribute("user")).getId();
        List<PreferList> prefers = preferService.getByUserId(userId);
        return prefers == null || prefers.size() == 0;
    }

    /**
     * 插入喜好列表
     * @param arrList
     * @param httpSession
     */
    @GetMapping("addPrefer")
    public void addPrefer(@RequestBody List<PreferList> arrList, HttpSession httpSession) {
        int userId = ((User) httpSession.getAttribute("user")).getId();
        for (PreferList arr : arrList) {
            preferService.insertInfo(userId, arr.getCategoryId());
        }
    }
}
