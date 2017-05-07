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
    @Scheduled(fixedRate = 10000)
    public void ctripCrab() {
//        String departDate = departLocalDate.format(formatter);
//        ctripGrabService.grabCtripPrice("BJS", "NYC", departDate, null, TripType.OW.getTripType());
//        departLocalDate = departLocalDate.plusDays(1);
    }
}
