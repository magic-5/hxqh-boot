package com.bjhxqh.controller;

import com.bjhxqh.model.dto.MenuDto;
import com.bjhxqh.model.po.Msg;
import com.bjhxqh.model.po.SysResource;
import com.bjhxqh.model.po.SysUser;
import com.bjhxqh.service.SysResourceService;
import com.bjhxqh.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fuyg on 2017-08-07.
 */
@Controller
public class SystemController {

    @Autowired
    SysResourceService resourceService;
    @Autowired
    SysUserService userService;

    @RequestMapping("/")
    String defaultIndex() {
        return "redirect:index";
    }

    @RequestMapping(value = "/index")
    ModelAndView index() {
        ModelAndView result = new ModelAndView("system/index/index");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String account = userDetails.getUsername();
        System.out.println("account:"+account);
        List<SysResource> parentResourcesList = resourceService.getParentResources(account);
        SysUser sysUser = userService.getUserByAccount(account);
        result.addObject("parentList", parentResourcesList);
        result.addObject("sysUser", sysUser);
        return result;
    }

    @RequestMapping(value = "/index/content")
    ModelAndView main(HttpServletRequest request) {
        ModelAndView result = new ModelAndView("system/index/content");
        String parentId=request.getParameter("id");
        String parentName=request.getParameter("name");
        System.out.println("parentId:"+parentId);
        System.out.println("parentName:"+parentName);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String account = userDetails.getUsername();
        System.out.println("account:"+account);
        if(parentId==null||parentName==null){
            List<SysResource> parentResourcesList = resourceService.getParentResources(account);
            if(parentResourcesList.size()>0){
                parentId = parentResourcesList.get(0).getId()+"";
                parentName = parentResourcesList.get(0).getName();
            }
        }
        List<MenuDto> subList = resourceService.getMenuByAccountAndParentId(account,Integer.parseInt(parentId),parentName);
        result.addObject("subList", subList);
        result.addObject("parentName", parentName);
        return result;
    }

    @RequestMapping(value = "/index/portal")
    ModelAndView portal() {
        ModelAndView result = new ModelAndView("system/portal/portal");
        return result;
    }

    @RequestMapping(value = "/login")
    ModelAndView login() {
        ModelAndView result = new ModelAndView("system/login/login");
        return result;
    }
}
