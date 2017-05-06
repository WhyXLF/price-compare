package com.neuq.flight.grab.processor;

import com.neuq.flight.Application;
import com.neuq.flight.grab.downloader.CtripDownloader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.commands.RedisPipeline;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * author: xiaoliufu
 * date:   2017/5/4.
 * description:
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class CtripProcessorTest {
    @Resource
    private CtripProcessor ctripProcessor;
    @Resource
    private CtripDownloader ctripDownloader;
    @Test
    public void test(){
        Spider.create(ctripProcessor)
                .addPipeline(new ConsolePipeline())
                .addUrl("http://flights.ctrip.com/international/chengdu-newyork-ctu-nyc?2017-06-10&y_s")
                .addUrl("http://flights.ctrip.com/international/chengdu-newyork-ctu-nyc?2017-06-11&y_s")
                .addUrl("http://flights.ctrip.com/international/chengdu-newyork-ctu-nyc?2017-06-12&y_s")
                .addUrl("http://flights.ctrip.com/international/chengdu-newyork-ctu-nyc?2017-06-13&y_s")
                .setDownloader(ctripDownloader)
                .run();
    }

}