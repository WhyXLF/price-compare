package com.neuq.flight.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * author: xiaoliufu
 * date:   2017/4/8.
 * description:
 */
@Controller
public class TestController {
    @RequestMapping(value = "/echo/{msg}", method = RequestMethod.GET)
    @ResponseBody
    public String test(@PathVariable(name = "msg") String msg) {
        return "echo " + msg;
    }
}
