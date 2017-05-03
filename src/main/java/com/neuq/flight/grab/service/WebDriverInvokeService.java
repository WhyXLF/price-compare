package com.neuq.flight.grab.service;

import com.neuq.flight.grab.constant.enumType.WebDriverType;
import com.neuq.flight.grab.utils.webdriver.WebDriverUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * author: xiaoliufu
 * date:   2017/5/1.
 * description: webDriver调用类
 */
@Service
@Slf4j
public class WebDriverInvokeService {

    /**
     * 获取页面信息
     *
     * @param url           url
     * @param webDriverType webDriver类型
     * @return optional string
     */
    public Optional<String> invoke(String url, WebDriverType webDriverType) {
        WebDriver webDriver;
        Optional<String> stringOptional = Optional.empty();
        try {
            webDriver = WebDriverUtil.getWebDriver(webDriverType);
            webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
            webDriver.get(url);
            String pageSource = webDriver.getPageSource();
            stringOptional = Optional.ofNullable(pageSource);
        } catch (Exception e) {
            log.error("invoke error url={}, webDriverType={}", url, webDriverType, e);
        }
        return stringOptional;
    }
}
