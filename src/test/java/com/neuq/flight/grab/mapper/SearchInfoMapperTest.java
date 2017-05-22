package com.neuq.flight.grab.mapper;

import com.neuq.flight.Application;
import com.neuq.flight.grab.domain.SearchInfo;
import com.neuq.flight.grab.domain.SearchInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * author: xiaoliufu
 * date:   2017/5/22.
 * description:
 */
@Slf4j
@SpringBootTest(classes = {Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class SearchInfoMapperTest {

    @Autowired
    private SearchInfoMapper searchInfoMapper;


    @Test
    public void insert() throws Exception {
        //插入数据
        SearchInfo searchInfo = SearchInfo.builder()
                .fromCityCode("CTU")
                .toCityCode("NYC")
                .tripType(1)
                .goDate("2017-10-01")
                .content("json content")
                .build();
        int result = searchInfoMapper.insert(searchInfo);
        log.info("result={}", result);
    }

    @Test
    public void selectByExample() throws Exception {
        //根据ftd查询数据
        SearchInfoExample searchInfoExample = new SearchInfoExample();
        searchInfoExample.createCriteria()
                .andFromCityCodeEqualTo("CTU")
                .andToCityCodeEqualTo("NYC")
                .andGoDateEqualTo("2017-10-01");
        List<SearchInfo> searchInfos = searchInfoMapper.selectByExample(searchInfoExample);
        log.info("searchInfos={}",searchInfos);

    }

}