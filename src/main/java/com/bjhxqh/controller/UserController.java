package com.bjhxqh.controller;

import com.bjhxqh.model.po.SysUser;
import com.bjhxqh.model.po.UserInfo;
import com.bjhxqh.service.SysUserService;
import com.bjhxqh.service.UserInfoService;
import com.github.pagehelper.PageInfo;
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
    private SysUserService userService;

    private String page_list = "test";

    private String redirect_list = "redirect:list";

    @RequestMapping(value = {"list", ""})
    public ModelAndView getList(SysUser user,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "10") int rows) {
        ModelAndView result = new ModelAndView(page_list);
        List<SysUser> userList = userService.selectByUser(user, page, rows);
        result.addObject("pageInfo", new PageInfo<SysUser>(userList));
        result.addObject("queryParam", user);
        result.addObject("page", page);
        result.addObject("rows", rows);
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
