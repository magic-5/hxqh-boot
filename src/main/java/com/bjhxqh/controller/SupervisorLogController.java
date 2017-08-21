package com.bjhxqh.controller;

import com.bjhxqh.model.po.TbSuperviseLog;
import com.bjhxqh.service.SupervisorLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2017-08-14.
 */
@Controller
@RequestMapping("superviselog")
public class SupervisorLogController {

    @Autowired
    private SupervisorLogService logService;

    private String page_list = "app/superviseLog/table";

    private String redirect_list = "redirect:/superviselog/list";

    @RequestMapping(value = {"list", "table", "table.html", ""})
    public ModelAndView getList(TbSuperviseLog tslog) {
        ModelAndView result = new ModelAndView(page_list);
        List<TbSuperviseLog> logList = logService.selectByLog(tslog);
        result.addObject("pageInfo", new PageInfo<TbSuperviseLog>(logList));
        result.addObject("queryParam", tslog);
        result.addObject("page", tslog.getPage());
        result.addObject("rows", tslog.getRows());
        return result;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView view(TbSuperviseLog tslog) {
        ModelAndView result = new ModelAndView();
        if (tslog.getId() != null) {
            tslog = logService.selectByKey(tslog.getId());
        }
        result.addObject("country", tslog);
        return result;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(TbSuperviseLog tslog) {
        ModelAndView result = new ModelAndView(redirect_list);
        if (tslog.getId() != null) {
            logService.updateAll(tslog);
        } else {
            logService.save(tslog);
        }
        return result;
    }

    @RequestMapping("delete")
    public String delete(Integer id) {
        logService.delete(id);
        return redirect_list;
    }
}
