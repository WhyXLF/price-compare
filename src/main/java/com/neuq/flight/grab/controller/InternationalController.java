package com.neuq.flight.grab.controller;

import com.neuq.flight.grab.entity.common.PriceResult;
import com.neuq.flight.grab.service.ctrip.CtripGrabService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * author: xiaoliufu
 * date:   2017/4/9.
 * description:
 */
@Controller
@RequestMapping(value = "/international")
public class InternationalController {
    @Resource
    private CtripGrabService ctripGrabService;
    @RequestMapping
    public String internationalView(){
        return "international-flight";
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public String domesticSearch(@RequestParam("dep") String fromCityCode, @RequestParam("arr") String toCityCode, @RequestParam("goTime") String goDate, Model model){
        PriceResult priceResult = ctripGrabService.getSimpleGrabSearchInfoOW(fromCityCode, toCityCode, goDate);
        model.addAttribute("priceResult",priceResult);
        return "domestic-flight";
    }
}
