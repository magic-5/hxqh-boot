package com.bjhxqh.controller;

import com.bjhxqh.model.po.SysRole;
import com.bjhxqh.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2017-07-20.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    private SysRoleService roleService;

    private String page_list = "test";

    private String redirect_list = "redirect:list";

    @RequestMapping(value = {"list", ""})
    public ModelAndView getList(SysRole role) {
        ModelAndView result = new ModelAndView(page_list);
        List<SysRole> roleList = roleService.selectByRole(role);
        result.addObject("pageInfo", new PageInfo<SysRole>(roleList));
        result.addObject("queryParam", role);
        result.addObject("page", role.getPage());
        result.addObject("rows", role.getRows());
        return result;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView view(SysRole role) {
        ModelAndView result = new ModelAndView();
        if (role.getId() != null) {
            role = roleService.selectByKey(role.getId());
        }
        result.addObject("country", role);
        return result;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(SysRole role) {
        ModelAndView result = new ModelAndView(redirect_list);
        if (role.getId() != null) {
            roleService.updateAll(role);
        } else {
            roleService.save(role);
        }
        return result;
    }

    @RequestMapping("delete")
    public String delete(Integer id) {
        roleService.delete(id);
        return redirect_list;
    }
}
