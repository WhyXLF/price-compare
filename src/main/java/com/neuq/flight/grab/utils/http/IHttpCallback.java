package com.neuq.flight.grab.utils.http;

/**
 * http回调接口
 *
 * Author: nimin
 * Datetime: 2016-08-10 11:23
 */
public interface IHttpCallback {
    /**
     * http请求成功时处理逻辑
     *
     * @param httpResult
     */
    public void onSuccess(String httpResult);

    /**
     * http请求异常时处理逻辑
     *
     * @param e
     */
    public void onException(Exception e);
}
