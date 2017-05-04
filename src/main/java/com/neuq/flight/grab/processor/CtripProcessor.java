package com.neuq.flight.grab.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * author: xiaoliufu
 * date:   2017/5/4.
 * description:
 */
@Slf4j
@Component
public class CtripProcessor implements PageProcessor{
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) {
        //获取航班列表集合信息
        Selectable flightList = page.getHtml().css("div#flightList");
        if (flightList== null){
            log.warn("process no flightList");
            return;
        }
        List<Selectable> flightItems = flightList.$("div.flight-item").nodes();
        if (flightItems==null || flightItems.isEmpty()){
            log.warn("process no flightItems");
            return;
        }
        for (Selectable flightItem : flightItems) {
            //获取航班详情信息
            Selectable flightDetail = flightItem.$("div.flight-detail-expend");
            if (flightDetail == null){
                log.warn("process no flightDetail");
                return;
            }
            //获取航班详情对应的航段信息
            List<Selectable> flightDetailSections = flightDetail.$("div.flight-detail-expend").nodes();
            if (flightDetailSections==null || flightDetailSections.isEmpty()){
                log.warn("process no flightDetailSections");
                return;
            }
            for (Selectable flightDetailSection : flightDetailSections) {
                Selectable  carrier= flightDetailSection.$("p.section-flight-base");
                log.info("carrier={}",carrier.get());
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
