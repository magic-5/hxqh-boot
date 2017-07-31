package com.bjhxqh.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017-07-18.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    ModelAndView login(Model model) {
        ModelAndView result = new ModelAndView("login/login");
        return result;
    }
}
