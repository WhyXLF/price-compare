package com.neuq.flight.grab.downloader;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * author: xiaoliufu
 * date:   2017/5/4.
 * description:
 */
@Slf4j
@Component
public class CtripDownloader implements Downloader, Closeable {
    private volatile WebDriverPool webDriverPool;
    private static final int DEFAULT_WAIT_TIME = 5;
    private static final int DEFAULT_POOL_SIZE = 10;
    private int waitSeconds;
    private int poolSize;



    public CtripDownloader() {
        System.getProperties().setProperty("phantomjs.binary.path", "./src/main/resources/phantomjs");
        System.getProperties().setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver_mac");
        waitSeconds = DEFAULT_WAIT_TIME;
        poolSize =DEFAULT_POOL_SIZE;
    }

    public CtripDownloader(String phantomjsPath, int waitSecond, int poolSize) {
        System.getProperties().setProperty("phantomjs.binary.path", phantomjsPath);
        this.waitSeconds = waitSecond;
        this.poolSize = poolSize;
    }

    @Override
    public void close() throws IOException {
        webDriverPool.closeAll();
    }

    @Override
    public Page download(Request request, Task task) {
        checkInit();
        WebDriver webDriver;
        try {
            webDriver = webDriverPool.get();
        } catch (InterruptedException e) {
            log.warn("interrupted", e);
            return null;
        }
        log.info("downloading page " + request.getUrl());

        webDriver.get(request.getUrl());
        WebDriver.Options manage = webDriver.manage();
        manage.timeouts().implicitlyWait(waitSeconds*2, TimeUnit.SECONDS);
        Site site = task.getSite();
        if (site.getCookies() != null) {
            for (Map.Entry<String, String> cookieEntry : site.getCookies()
                    .entrySet()) {
                Cookie cookie = new Cookie(cookieEntry.getKey(),
                        cookieEntry.getValue());
                manage.addCookie(cookie);
            }
        }
        try {
            Thread.sleep(waitSeconds * 1000);
        } catch (InterruptedException e) {
            log.error("download interrupted! ", e);
        }
        //获取实际内容
        String pageSource = webDriver.getPageSource();
        //创建page对象
        Page page = new Page();
        page.setRawText(pageSource);
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        webDriverPool.returnToPool(webDriver);
        return page;
    }

    private void checkInit() {
        if (webDriverPool == null) {
            synchronized (this) {
                webDriverPool = new WebDriverPool(poolSize);
            }
        }
    }

    @Override
    public void setThread(int threadNum) {

    }
}
