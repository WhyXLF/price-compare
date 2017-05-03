package com.neuq.flight.grab.utils.http;

import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;

/**
 * http配置:
 * 1. 时间单位全部是毫秒
 * 2. 默认不重试
 *
 * Author: nimin
 * Datetime: 2016-07-28 11:14
 */
public class HttpConfig {
    /** 处理超时时间 - 对应read timeout */
    private int timeout = 5000;
    /** 连接超时时间 - 对应connect timeout */
    private int connectionTimeout = 5000;
    /** 连接存活检测（连接池中使用） */
    private int timeToLive = 5000;
    /** 最大连接数 */
    private int maxConnections = 1024;
    /** 每个目标主机的最大连接数 */
    private int maxConnectionsPerRoute = 256;

    /** 连接复用 + keepalive */
    private int keepAlive = 0;

    /** 失败重试处理类 */
    private HttpRequestRetryHandler retryHandler = new NoRetryHandler();
    /** 认证 */
    private CredentialsProvider credentialsProvider;

    /** UA */
    private String userAgent = null;

    /** 是否使用代理 */
    private boolean useProxy = false;
    /** 代理IP */
    private String proxyHost = null;
    /** 代理端口 */
    private int proxyPort = 0;

//    /** 线程池核心线程数 */
//    private int threadPoolCoreSize = Runtime.getRuntime().availableProcessors() / 2 + 1;
//    /** 线程池队列大小 */
//    private int threadPoolQueueSize = 1024;
//    /** 线程池keepalive时间 */
//    private int threadPoolKeepalive = 60000;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getMaxConnectionsPerRoute() {
        return maxConnectionsPerRoute;
    }

    public void setMaxConnectionsPerRoute(int maxConnectionsPerRoute) {
        this.maxConnectionsPerRoute = maxConnectionsPerRoute;
    }

    public int getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getUserAgent() {
        return userAgent == null ? "Apache-HttpClient/1.0" : userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public boolean isUseProxy() {
        return useProxy;
    }

    public void setUseProxy(boolean useProxy) {
        this.useProxy = useProxy;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public HttpRequestRetryHandler getRetryHandler() {
        return retryHandler;
    }

    public void setRetryHandler(HttpRequestRetryHandler retryHandler) {
        this.retryHandler = retryHandler;
    }

    public CredentialsProvider getCredentialsProvider() {
        return credentialsProvider;
    }

    public void setCredentialsProvider(CredentialsProvider credentialsProvider) {
        this.credentialsProvider = credentialsProvider;
    }

    @Override
    public String toString() {
        return "HttpConfig{" +
                "timeout=" + timeout +
                ", connectionTimeout=" + connectionTimeout +
                ", timeToLive=" + timeToLive +
                ", maxConnections=" + maxConnections +
                ", maxConnectionsPerRoute=" + maxConnectionsPerRoute +
                ", keepAlive=" + keepAlive +
                ", retryHandler=" + retryHandler +
                ", credentialsProvider=" + credentialsProvider +
                ", userAgent='" + userAgent + '\'' +
                ", useProxy=" + useProxy +
                ", proxyHost='" + proxyHost + '\'' +
                ", proxyPort=" + proxyPort +
                '}';
    }
}
