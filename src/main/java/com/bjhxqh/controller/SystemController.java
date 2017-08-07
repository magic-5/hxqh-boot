package com.bjhxqh.controller;

import com.bjhxqh.model.dto.MenuDto;
import com.bjhxqh.model.po.Msg;
import com.bjhxqh.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by fuyg on 2017-08-07.
 */
@Controller
public class SystemController {

    @Autowired
    SysResourceService resourceService;

    @RequestMapping("/")
    String defaultIndex() {
        return "redirect:index";
    }

    @RequestMapping(value = "/index")
    ModelAndView index() {
        ModelAndView result = new ModelAndView("system/index");
        Msg msg =  new Msg("测试标题","测试内容","欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        String account = userDetails.getUsername();
        System.out.println("account:"+account);
        List<MenuDto> menuList = resourceService.getMenuByAccount(account);
        result.addObject("msg", msg);
        result.addObject("List", menuList);
        return result;
    }

    @RequestMapping(value = "/index/content")
    ModelAndView main() {
        ModelAndView result = new ModelAndView("system/content");
        return result;
    }

    @RequestMapping(value = "/index/portal")
    ModelAndView portal() {
        ModelAndView result = new ModelAndView("system/portal");
        return result;
    }

    @RequestMapping(value = "/login")
    ModelAndView login() {
        ModelAndView result = new ModelAndView("system/login");
        return result;
    }
}
