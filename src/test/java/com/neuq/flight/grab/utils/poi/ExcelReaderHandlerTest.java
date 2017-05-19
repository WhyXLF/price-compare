package com.neuq.flight.grab.utils.poi;

import com.neuq.flight.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * author: xiaoliufu
 * date:   2017/5/18.
 * description:
 */
@Slf4j
@SpringBootTest(classes = {Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ExcelReaderHandlerTest {
    @Test
    public void getContent() throws Exception {
        ExcelReaderHandler excelReaderHandler = new ExcelReaderHandler();
        excelReaderHandler.config("/ftd.xlsx");
        String content = excelReaderHandler.getContent(0, 0, 0);
        log.info("content={}",content);
    }

}