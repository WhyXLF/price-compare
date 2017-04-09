package com.neuq.flight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * author: xiaoliufu
 * date:   2017/4/9.
 * description:
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("username","admin");
        return "index";
    }
}
