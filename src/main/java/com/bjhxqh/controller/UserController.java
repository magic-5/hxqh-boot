package com.bjhxqh.controller;

import com.bjhxqh.model.po.UserInfo;
import com.bjhxqh.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017-07-17.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/create")
    @ResponseBody
    String create(HttpServletRequest request) {
        UserInfo user = new UserInfo();
        user.setUsername("username123寇鑫");
        user.setPassword("password123寇鑫");
        userInfoService.insertUser(user);
        return "插入成功";
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Object update(HttpServletRequest request) {
        return "PUT a user";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    UserInfo detail(@PathVariable Integer id){
        UserInfo user = new UserInfo();
        user.setUsername("张三");
        user.setPassword("123456");
        return user;
    }

    //新增的接口方法
    @RequestMapping(value = "/list")
    @ResponseBody
    List<UserInfo> list(HttpServletRequest request) {
        return userInfoService.selectALL();
    }
}
