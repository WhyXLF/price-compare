package com.neuq.flight.grab.downloader;

import com.neuq.flight.grab.constant.enumType.WebDriverType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: xiaoliufu
 * date:   2017/5/4.
 * description:
 */
@Slf4j
@Component
public class WebDriverPool {
    private final static int DEFAULT_CAPACITY = 5;

    private final int capacity;
    private final static int STAT_RUNNING = 1;

    private final static int STAT_CLODED = 2;

    private AtomicInteger stat = new AtomicInteger(STAT_RUNNING);

    private WebDriver mDriver = null;

    protected static DesiredCapabilities sCaps;

    /**
     * 基础配置
     */
    public void config(WebDriverType webDriverType) {
        ArrayList<String> cliArgsCap = new ArrayList<>();
        cliArgsCap.add("--web-security=false");
        cliArgsCap.add("--ssl-protocol=any");
        cliArgsCap.add("--ignore-ssl-errors=true");
//        sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
//                cliArgsCap);
//
//        // Control LogLevel for GhostDriver, via CLI arguments
//        sCaps.setCapability(
//                PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
//                new String[] { "--logLevel=INFO"});

        if (webDriverType == WebDriverType.CHROME) {
            mDriver = new ChromeDriver();
        } else if (webDriverType == WebDriverType.PHANTOMJS) {
            mDriver = new PhantomJSDriver();
        }

    }


    /**
     * store webDrivers created
     */
    private final List<WebDriver> webDriverList = Collections
            .synchronizedList(new ArrayList<WebDriver>());

    /**
     * store webDrivers available
     */
    private BlockingDeque<WebDriver> innerQueue = new LinkedBlockingDeque<WebDriver>();

    public WebDriverPool() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public WebDriverPool(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return
     * @throws InterruptedException
     */
    public WebDriver get(WebDriverType webDriverType) throws InterruptedException {
        if (!checkRunning()) {
            config(webDriverType);
        }
        WebDriver poll = innerQueue.poll();
        if (poll != null) {
            return poll;
        }
        if (webDriverList.size() < capacity) {
            synchronized (webDriverList) {
                if (webDriverList.size() < capacity) {

                    // add new WebDriver instance into pool
                    config(webDriverType);
                    innerQueue.add(mDriver);
                    webDriverList.add(mDriver);
                }
            }

        }
        return innerQueue.take();
    }

    protected boolean checkRunning() {
        if (!stat.compareAndSet(STAT_RUNNING, STAT_RUNNING)) {
            log.info("Already closed!");
            return false;
        }
        return true;
    }

    public void returnToPool(WebDriver webDriver) {
        if (checkRunning()) {
            innerQueue.add(webDriver);
        }
    }

    public void closeAll() {
        boolean b = stat.compareAndSet(STAT_RUNNING, STAT_CLODED);
        if (!b) {
            log.info("already closed");
        }
        for (WebDriver webDriver : webDriverList) {
            log.info("Quit webDriver" + webDriver);
            webDriver.quit();
        }
    }

}
