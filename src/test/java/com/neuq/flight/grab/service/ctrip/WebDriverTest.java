package com.neuq.flight.grab.service.ctrip;

import com.neuq.flight.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * author: xiaoliufu
 * date:   2017/5/22.
 * description:
 */
@Slf4j
@SpringBootTest(classes = {Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class WebDriverTest {
    @Test
    public void testWebDriver() throws Exception {
        WebDriver webDriver = new PhantomJSDriver();
        webDriver.get("http://www.baidu.com");
        //等待异步加载
        Thread.sleep(1000);
        String pageSource = webDriver.getPageSource();
        log.info("pageSource={}",pageSource);
    }
}
