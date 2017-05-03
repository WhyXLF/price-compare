package com.neuq.flight.grab.utils.webdriver;

import com.neuq.flight.grab.constant.enumType.WebDriverType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * author: xiaoliufu
 * date:   2017/5/1.
 * description:
 */
@Slf4j
public class WebDriverUtil {

    private static volatile WebDriver singletonWebDriver;
    private static final Object lock = new Object();

    /**
     * 获取单例webDriver
     *
     * @param webDriverType webDriver类型
     * @return webDriver
     */
    public static WebDriver getWebDriver(WebDriverType webDriverType) {
        if (singletonWebDriver == null) {
            synchronized (lock) {
                if (webDriverType == WebDriverType.CHROME) {
                    singletonWebDriver = new ChromeDriver();
                } else if (webDriverType == WebDriverType.PHANTOMJS) {
                    singletonWebDriver = new PhantomJSDriver();
                }
            }
        }
        return singletonWebDriver;
    }

    /**
     * 关闭webdriver
     *
     * @return boolean
     */
    public static void closeWebDriver() {
        try {
            if (singletonWebDriver != null) {
                singletonWebDriver.close();
                return;
            }
        } catch (Exception e) {
            log.error("closeWebDriver close error", e);
        }
        log.warn("closeWebDriver not start this singletonDriver");
    }
}
