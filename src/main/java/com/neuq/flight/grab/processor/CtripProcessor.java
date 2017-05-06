package com.neuq.flight.grab.processor;

import com.neuq.flight.grab.entity.common.PriceResult;
import com.neuq.flight.grab.service.ctrip.CtripGrabService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.Resource;
import java.util.List;

/**
 * author: xiaoliufu
 * date:   2017/5/4.
 * description:
 */
@Slf4j
@Component
public class CtripProcessor implements PageProcessor {
    @Resource
    private CtripGrabService ctripGrabService;
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000)
            .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            .addHeader("Accept-Encoding","gzip, deflate")
            .addHeader("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6")
            .addHeader("Cache-Control","max-age=0")
            .addHeader("Connection","keep-alive")
            .addHeader("Content-Length","446")
            .addHeader("Content-Type","application/x-www-form-urlencoded")
            .addHeader("Upgrade-Insecure-Requests","1")
            .addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.96 Safari/537.36");

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) {
        String content = page.getRawText();
        PriceResult priceResult = ctripGrabService.getPriceResult(content);
        page.putField("priceResult", priceResult);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
