package com.neuq.flight.grab.service.ctrip;

import com.neuq.flight.Application;
import com.neuq.flight.grab.entity.common.PriceResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * author: xiaoliufu
 * date:   2017/5/3.
 * description:
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class CtripGrabServiceTest {
    @Resource
    private CtripGrabService ctripGrabService;

    @Test
    public void getSimpleGrabSearchInfoOW() throws Exception {
        PriceResult priceResult = ctripGrabService.getSimpleGrabSearchInfoOW("CTU", "NYC", "2017-06-01");
        log.info("priceResult={}", priceResult);
    }

}