package com.neuq.flight.grab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: xiaoliufu
 * date:   2017/4/9.
 * description:
 */
@Controller
@RequestMapping(value = "/international")
public class InternationalController {
    @RequestMapping
    public String internationalView(){
        return "international-flight";
    }
}
