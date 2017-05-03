package com.neuq.flight.grab.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: xiaoliufu
 * date:   2017/4/9.
 * description:
 */
@Controller
@RequestMapping(value = "/domestic")
public class DomesticController {
    @RequestMapping
    public String domesticView(){
        return "domestic-flight";
    }
}
