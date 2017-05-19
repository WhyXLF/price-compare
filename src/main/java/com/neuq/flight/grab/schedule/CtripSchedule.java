package com.neuq.flight.grab.schedule;

import com.neuq.flight.grab.constant.enumType.TripType;
import com.neuq.flight.grab.service.ctrip.CtripGrabService;
import com.neuq.flight.grab.utils.poi.ExcelReaderHandler;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    @Scheduled(fixedRate = 86400000)
    public void ctripCrab() {
        ExcelReaderHandler excelReaderHandler =new ExcelReaderHandler();
        excelReaderHandler.config("/ftd.xlsx");
        int totalRowNum = excelReaderHandler.getTotalRowNum(0);
        for (int i = 1; i < totalRowNum; i++) {
            String from = excelReaderHandler.getContent(0, i, 0);
            String to = excelReaderHandler.getContent(0, i, 1);
            String goDate = excelReaderHandler.getContent(0, i, 2);
            log.info("from={},to={},goDate={}",from,to,goDate);
            if (from.equals(to)){
                log.info("continue!");
                continue;
            }
            List<String>urls= Lists.newArrayList();
            String owSearchUrl = ctripGrabService.getOwSearchUrl(from, to, goDate);
            urls.add(owSearchUrl);
            ctripGrabService.grabCtripPrice(from,to,TripType.OW.getTripType(),urls);
        }
    }
}
