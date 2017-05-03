package com.neuq.flight.grab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: xiaoliufu
 * date:   2017/5/3.
 * description:
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "test!";
    }
}
