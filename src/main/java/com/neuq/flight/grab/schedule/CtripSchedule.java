package com.neuq.flight.grab.schedule;

import com.neuq.flight.grab.constant.enumType.TripType;
import com.neuq.flight.grab.service.ctrip.CtripGrabService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * author: xiaoliufu
 * date:   2017/5/7.
 * description:携程报价抓取定时任务
 */
@Slf4j
@Component
public class CtripSchedule {
    @Resource
    private CtripGrabService ctripGrabService;

    /**
     * 定时抓取
     */
    @Scheduled(cron = "0 0 0/24 * * ?")
    public void ctripCrab() {
        ctripGrabService.grabCtripPrice("BJS", "NYC", "2017-07-15", null, TripType.OW.getTripType());
    }
}
