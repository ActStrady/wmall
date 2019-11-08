package com.actstrady.wmall.web.controller;

import com.actstrady.wmall.po.UserPO;
import com.actstrady.wmall.service.PreferService;
import com.actstrady.wmall.service.UserService;
import com.actstrady.wmall.vo.PreferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public UserController(UserService userService, PreferService preferService) {
        this.userService = userService;
        this.preferService = preferService;
    }

    /**
     * 登录功能
     *
     * @param userMod     前端传入的user
     * @param httpSession spring的模型类
     * @return 成功就是用户的id，0表示失败
     */
    @PostMapping("login")
    public int login(@RequestBody UserPO userMod, HttpSession httpSession, Model model) {
        UserPO user = userService.login(userMod);
        if (user == null) {
            return 0;
        } else {
            // 保存到session
            httpSession.setAttribute("user", user);
            // 设置session时间
            httpSession.setMaxInactiveInterval(60 * 60);
            model.addAttribute("user", user);
            return user.getId();
        }
    }

    /**
     * 退出功能
     *
     * @return 成功
     */
    @GetMapping("logout")
    public Boolean execute(HttpSession httpSession) {
        // 使session失效
        httpSession.invalidate();
        return true;
    }

    /**
     * 判断是否登录
     * session里是否有user
     *
     * @return 是否成功
     */
    @GetMapping("checkLogin")
    public boolean isLogin(HttpSession httpSession) {
        return httpSession.getAttribute("user") != null;
    }

    /**
     * 注册前查询是否已经有了user
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
    public Boolean register(@RequestBody UserPO user) {
        return userService.registered(user);
    }

    /**
     * 查询喜好列表(暂时这样)
     *
     * @param httpSession session
     * @return 成功否
     */
    @GetMapping("checkPrefer")
    public boolean checkPrefer(HttpSession httpSession) {
        int userId = ((UserPO) httpSession.getAttribute("user")).getId();
        List<PreferVO> prefers = preferService.getByUserId(userId);
        return prefers == null || prefers.size() == 0;
    }

    /**
     * 插入喜好列表
     *
     * @param arrList 喜好数据
     * @param httpSession session
     */
    @PostMapping("addPrefer")
    public void addPrefer(@RequestBody List<PreferVO> arrList, HttpSession httpSession) {
        int userId = ((UserPO) httpSession.getAttribute("user")).getId();
        for (PreferVO arr : arrList) {
            preferService.insertInfo(userId, arr.getCategoryId());
        }
    }
}
