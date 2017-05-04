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
    private int wait = 0;
    private int poolSize = 1;

    public CtripDownloader(String phantomjsPath, int waitMinute, int poolSize) {
        System.getProperties().setProperty("phantomjs.binary.path", phantomjsPath);
        this.wait = waitMinute;
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
        manage.timeouts().implicitlyWait(wait, TimeUnit.MINUTES);
        Site site = task.getSite();
        if (site.getCookies() != null) {
            for (Map.Entry<String, String> cookieEntry : site.getCookies()
                    .entrySet()) {
                Cookie cookie = new Cookie(cookieEntry.getKey(),
                        cookieEntry.getValue());
                manage.addCookie(cookie);
            }
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
