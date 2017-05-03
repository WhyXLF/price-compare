package com.neuq.flight.grab.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 自定义编码的response handler
 *
 * Author: nimin
 * Datetime: 2016-07-28 11:15
 */
public class EncodingResponseHandler implements ResponseHandler<String> {
    private String encoding;

    public EncodingResponseHandler(String encoding) {
        this.encoding = encoding;
    }

    /**
     * Returns the response body as a String if the response was successful (a
     * 2xx status code). If no response body exists, this returns null. If the
     * response was unsuccessful (>= 300 status code), throws an
     * {@link org.apache.http.client.HttpResponseException}.
     */
    public String handleResponse(final HttpResponse response) throws IOException {
        final StatusLine statusLine = response.getStatusLine();
        final HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= 300) {
            EntityUtils.consume(entity);
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }
        return entity == null ? null : EntityUtils.toString(entity, encoding);
    }
}

