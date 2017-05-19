package com.neuq.flight.grab.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuq.flight.grab.domain.SearchInfo;
import com.neuq.flight.grab.domain.SearchInfoExample;
import com.neuq.flight.grab.mapper.SearchInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * author: xiaoliufu
 * date:   2017/5/18.
 * description:
 */
@Slf4j
@Controller
public class SearchController {
    @Autowired
    private SearchInfoMapper searchInfoMapper;

    @RequestMapping(value = "search")
    public String search(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("date") String date) {
        SearchInfoExample searchInfoExample = new SearchInfoExample();
        searchInfoExample.createCriteria()
                .andFromCityCodeEqualTo(from)
                .andToCityCodeEqualTo(to)
                .andGoDateEqualTo(date);
        List<SearchInfo> searchInfos = searchInfoMapper.selectByExample(searchInfoExample);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(searchInfos);
        } catch (JsonProcessingException e) {
            log.error("search exception ", e);
        }
        return "error";

    }
}
