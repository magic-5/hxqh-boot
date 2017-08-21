package com.bjhxqh.controller;

import com.bjhxqh.model.po.SysUser;
import com.bjhxqh.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

/**
 * Created by Administrator on 2017-07-17.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private SysUserService userService;

    private String page_list = "test";

    private String redirect_list = "redirect:/user/list";

    @RequestMapping(value = {"list", ""})
    public ModelAndView getList(SysUser user) {
        ModelAndView result = new ModelAndView(page_list);
        List<SysUser> userList = userService.selectByUser(user);
        result.addObject("pageInfo", new PageInfo<SysUser>(userList));
        result.addObject("queryParam", user);
        result.addObject("page", user.getPage());
        result.addObject("rows", user.getRows());
        return result;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView view(SysUser user) {
        ModelAndView result = new ModelAndView();
        if (user.getId() != null) {
            user = userService.selectByKey(user.getId());
        }
        result.addObject("country", user);
        return result;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(SysUser user) {
        ModelAndView result = new ModelAndView(redirect_list);
        if (user.getId() != null) {
            userService.updateAll(user);
        } else {
            userService.save(user);
        }
        return result;
    }

    @RequestMapping("delete")
    public String delete(Integer id) {
        userService.delete(id);
        return redirect_list;
    }
}
