package com.bjhxqh.controller;

import com.bjhxqh.model.po.UserInfo;
import com.bjhxqh.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017-07-17.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/create")
    //@ResponseBody
    String create(HttpServletRequest request) {
        UserInfo user = new UserInfo();
        user.setUsername("username123寇鑫");
        user.setPassword("password123寇鑫");
        userInfoService.save(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    ModelAndView detail(UserInfo user){
        ModelAndView result = new ModelAndView("test");
        if(user.getId()!=null){
            user = userInfoService.selectByKey(user.getId());
        }
        System.out.println(user.getId());
        System.out.println(user);
        result.addObject("user",user);
        return result;
    }

    //新增的接口方法
    @RequestMapping(value = {"/list",""})
    @ResponseBody
    List<UserInfo> list() {
        return userInfoService.selectALL();
    }
}
