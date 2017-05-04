package com.neuq.flight.grab.controller;

import com.neuq.flight.grab.entity.common.PriceResult;
import com.neuq.flight.grab.service.ctrip.CtripGrabService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * author: xiaoliufu
 * date:   2017/4/9.
 * description:
 */
@Slf4j
@Controller
@RequestMapping(value = "/domestic")
public class DomesticController {
    @RequestMapping
    public String domesticView() {
        return "domestic-flight";
    }
}
