package com.neuq.flight.grab.config;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Author: xiaoliufu
 * Date: 3/16/17
 */
@Component
public class CustomConfig {
    static {
        System.getProperties().setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver_mac");
        System.getProperties().setProperty("phantomjs.binary.path","./src/main/resources/phantomjs");
    }
}
