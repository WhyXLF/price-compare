package com.neuq.flight.grab.mappers;

import com.neuq.flight.Application;
import com.neuq.flight.grab.domain.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * author: xiaoliufu
 * date:   2017/5/7.
 * description:
 */
@SpringBootTest(classes = {Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class SearchResultMapperTest {
    @Resource
    private SearchResultMapper searchResultMapper;
    @Test
    public void insert() throws Exception {
        SearchResult searchResult = SearchResult.builder()
                .fromCityCode("fromCityCode")
                .toCityCode("toCityCode")
                .goDate("goDate")
                .backDate("backDate")
                .searchInfo("searchInfo")
                .build();
        searchResultMapper.insert(searchResult);
    }

}