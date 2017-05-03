package com.neuq.flight.grab.service.qunar.grab;

import com.alibaba.fastjson.JSON;
import com.neuq.flight.grab.constant.qunar.UrlConstants;
import com.neuq.flight.grab.entity.qunar.QunarResponse;
import com.neuq.flight.grab.utils.http.HttpAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by IntelliJ IDEA.
 * Author: xlf1995
 * Date: 12/21/16
 */
@Service
public class QunarGrabService {
    private static final Logger logger = LoggerFactory.getLogger(QunarGrabService.class);

    /**
     * 单次爬取去哪儿的报价数据
     *
     * @param tripType
     * @param from
     * @param to
     * @param toDate
     * @param retDate
     * @return
     * @throws UnsupportedEncodingException
     */
    public QunarResponse getSimpleGrabQunarSearchInfo(int tripType, String from, String to, String toDate, String retDate) {
        logger.info("QunarGrabService->getSimpleGrabQunarSearchInfo tripType={},from={},to={},toDate={},retDate={}", tripType, from, to, toDate, retDate);
        QunarResponse qunarResponse = null;
        try {
            from = URLEncoder.encode(from, "UTF-8");
            to = URLEncoder.encode(to, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String content;
        if (tripType == 1) {
            String url = String.format(UrlConstants.QUNAR_OW_SEARCH_URL_PREFIX, from, to, toDate, UrlConstants.QUERY_OW_SEARCH_URL_SUFFIX);
            HttpAgent httpAgent = HttpAgent.create();
            try {
                httpAgent.doGet(url);
                httpAgent.doGet(url);
                httpAgent.doGet(url);
                content = httpAgent.doGet(url);
                logger.info("QunarGrabService->getSimpleGrabQunarSearchInfo content={}", content);
                qunarResponse = JSON.parseObject(content, QunarResponse.class);
                logger.info("QunarGrabService->getSimpleGrabQunarSearchInfo qunarResponse={}", qunarResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return qunarResponse;
    }

}
