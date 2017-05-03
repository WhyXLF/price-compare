package com.neuq.flight.grab.utils.http;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * 超时重试处理（连接超时和响应超时）
 *
 * Author: nimin
 * Datetime: 2016-08-09 19:47
 */
public class TimeoutRetryHandler implements HttpRequestRetryHandler {

    private Logger logger = LoggerFactory.getLogger(TimeoutRetryHandler.class);
    private int retryCount;

    public TimeoutRetryHandler(int retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        if (executionCount > retryCount) {
            return false;
        }
        Object targetHost = context.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
        if (exception instanceof ConnectTimeoutException) {
            logger.warn("connection timeout. retry to connect again. {}", targetHost);
            return true;
        }
        if (exception instanceof SocketTimeoutException) {
            logger.warn("read timeout. retry to request again. {}", targetHost);
            return true;
        }
        return false;
    }
}
