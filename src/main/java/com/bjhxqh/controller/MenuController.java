package com.bjhxqh.controller;


import com.bjhxqh.model.po.SysResource;
import com.bjhxqh.model.po.SysUser;
import com.bjhxqh.model.po.UserInfo;
import com.bjhxqh.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;
import com.github.pagehelper.PageInfo;

/**
 * Created by Administrator on 2017-07-20.
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private SysResourceService resourceService;
    private String page_list = "system/menu/menu";
    private String redirect_list = "redirect:/menu/list";
    @RequestMapping(value = {"list", ""})
    public ModelAndView getList(SysResource sysResource) {
        ModelAndView result = new ModelAndView(page_list);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String account = userDetails.getUsername();
        System.out.println("account:" + account);
        List<SysResource> allList = resourceService.selectByResource(sysResource);
        result.addObject("allList", allList);
        result.addObject("pageInfo",new PageInfo<SysResource>(allList));
        result.addObject("queryParam", sysResource);
        result.addObject("page", sysResource.getPage());
        result.addObject("rows", sysResource.getRows());
        return result;
    }
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView view(SysResource sysResource) {
        ModelAndView result = new ModelAndView("system/menu/menudetail");
        if (sysResource.getId() != null) {
            sysResource = resourceService.selectByKey(sysResource.getId());
        }
        result.addObject("sysResource", sysResource);
        return result;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(SysResource sysResource) {
        ModelAndView result = new ModelAndView(redirect_list);
        if (sysResource.getId() != null) {
            resourceService.updateNotNull(sysResource);
        } else {
            resourceService.save(sysResource);
        }
        return result;
    }
    @RequestMapping("delete")
    public String delete(Integer id) {
        resourceService.delete(id);
        return redirect_list;
    }

}
