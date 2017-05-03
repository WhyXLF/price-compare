package com.neuq.flight.grab.utils.http;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * 不重试机制
 *
 * Author: nimin
 * Datetime: 2016-08-10 12:22
 */
public class NoRetryHandler implements HttpRequestRetryHandler {
    @Override
    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        return false;
    }
}
