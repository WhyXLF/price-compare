package com.neuq.flight.grab.service.ctrip;

import com.neuq.flight.Application;
import com.neuq.flight.grab.constant.enumType.TripType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * author: xiaoliufu
 * date:   2017/5/7.
 * description: 携程数据抓取测试类
 */
@Slf4j
@SpringBootTest(classes = {Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CtripGrabServiceTest {
    @Resource
    private CtripGrabService ctripGrabService;

    @Test
    public void grabCtripPrice() throws Exception {
        ctripGrabService.grabCtripPrice("CTU","NYC","2017-06-10",null, TripType.OW.getTripType());
    }

}