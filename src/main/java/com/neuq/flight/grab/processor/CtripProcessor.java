package com.neuq.flight.grab.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * author: xiaoliufu
 * date:   2017/5/4.
 * description:
 */
public class CtripProcessor implements PageProcessor{
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) {
        Selectable flightList = page.getHtml().css("div#flightList");

        page.putField("flightList",flightList);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
