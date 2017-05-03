package com.neuq.flight.grab.utils.http;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 * HTTP客户端,使用方式示例:
 * <code>
 * private static HttpAgent agent;
 * static{
 * HttpConfig config=new HttpConfig();
 * config.setConnectionTimeout(1000);
 * config.setTimeout(3000);
 * config.setMaxConnectionsPerRoute(32);
 * config.setMaxConnections(128);
 * agent=HttpAgent.create(config);
 * }
 * </code>
 * </pre>
 * <p>
 * Author: nimin
 * Datetime: 2016-07-28 10:59
 */
public class HttpAgent {
    private HttpConfig config;
    private PoolingHttpClientConnectionManager connManager;
    private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int MAX_SIZE = CORE_SIZE * 2;
    private static final int KEEYALIVE = 60;
    private static final int QUEUE_SIZE = 1024;
    private static ThreadPoolExecutor exec = new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, KEEYALIVE, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(QUEUE_SIZE), new ThreadFactory() {
        private AtomicInteger counter = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("async-http-" + counter.getAndIncrement());
            t.setDaemon(true);
            return t;
        }
    });

    private HttpAgent() {
    }

    public HttpConfig getConfiguration() {
        return config;
    }

    public void setConfiguration(HttpConfig configuration) {
        this.config = configuration;
    }

    public PoolingHttpClientConnectionManager getConnManager() {
        return connManager;
    }

    public void setConnManager(PoolingHttpClientConnectionManager connManager) {
        this.connManager = connManager;
    }

    public static HttpAgent create() {
        HttpConfig config = new HttpConfig();
        HttpAgent agent = new HttpAgent();
        agent.setConfiguration(config);
        agent.setConnManager(createConnectionManager(config));
        return agent;
    }

    public static HttpAgent create(HttpConfig config) {
        HttpAgent agent = new HttpAgent();
        agent.setConfiguration(config);
        agent.setConnManager(createConnectionManager(config));
        return agent;
    }

    /**
     * 提交get请求
     *
     * @param urlPath
     * @return
     */
    public String doGet(String urlPath) throws IOException {
        HttpClient client = buildClient();
        return client.execute(new HttpGet(urlPath), new BasicResponseHandler());
    }

    /**
     * 异步提交get请求
     *
     * @param urlPath
     * @param callback
     */
    public void doAsyncGet(final String urlPath, final IHttpCallback callback) {
        final HttpClient client = buildClient();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = client.execute(new HttpGet(urlPath), new BasicResponseHandler());
                    callback.onSuccess(response);
                } catch (Exception e) {
                    callback.onException(e);
                }
            }
        });
    }

    /**
     * 提交单向https get请求
     *
     * @param urlPath
     * @return
     */
    public String doGetWithSSL(String urlPath) throws Exception {
        HttpClient client = buildSSLClient();
        return client.execute(new HttpGet(urlPath), new BasicResponseHandler());
    }

    /**
     * 提交单向https post请求
     *
     * @param urlPath
     * @param content
     * @return
     */
    public String doPostWithSSL(String urlPath, String content) throws Exception {
        HttpClient client = buildSSLClient();
        return doPost(client, urlPath, content, "UTF-8", null);
    }

    /**
     * 提交单向https post请求，包含自定义请求头
     *
     * @param urlPath
     * @param content
     * @param headers
     * @return
     */
    public String doPostWithSSL(String urlPath, String content, Map<String, String> headers) throws Exception {
        HttpClient client = buildSSLClient();
        return doPost(client, urlPath, content, "UTF-8", headers);
    }

    /**
     * 提交双向https post请求
     *
     * @param urlPath
     * @param content
     * @return
     */
    public String doPostWithSSL(String urlPath, String content, String certFile, String password) throws Exception {
        HttpClient client = buildTwoWaySSLClient(certFile, password);
        return doPost(client, urlPath, content, "UTF-8", null);
    }

    /**
     * 提交单向https post请求
     *
     * @param urlPath
     * @param params
     * @return
     */
    public String doPostWithSSL(String urlPath, Map<String, String> params) throws Exception {
        HttpClient client = buildSSLClient();
        return doPost(client, urlPath, params, "UTF-8");
    }

    /**
     * 提交双向https post请求
     *
     * @param urlPath
     * @param params
     * @return
     */
    public String doPostWithSSL(String urlPath, Map<String, String> params, String certFile, String password) throws Exception {
        HttpClient client = buildTwoWaySSLClient(certFile, password);
        return doPost(client, urlPath, params, "UTF-8");
    }

    /**
     * 提交普通http post请求
     *
     * @param urlPath
     * @param content
     * @return
     */
    public String doPost(String urlPath, String content) throws IOException {
        HttpClient client = buildClient();
        return doPost(client, urlPath, content, "UTF-8", null);
    }

    /**
     * 异步提交post请求
     *
     * @param urlPath
     * @param content
     * @param callback
     */
    public void doAsyncPost(final String urlPath, final String content, final IHttpCallback callback) {
        final HttpClient client = buildClient();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = doPost(client, urlPath, content, "UTF-8", null);
                    callback.onSuccess(response);
                } catch (Exception e) {
                    callback.onException(e);
                }
            }
        });
    }

    /**
     * 提交普通http post请求，包含自定义请求头
     *
     * @param urlPath
     * @param content
     * @param headers
     * @return
     */
    public String doPost(String urlPath, String content, Map<String, String> headers) throws IOException {
        HttpClient client = buildClient();
        return doPost(client, urlPath, content, "UTF-8", headers);
    }

    /**
     * 异步提交post请求，包含自定义请求头
     *
     * @param urlPath
     * @param content
     * @param callback
     */
    public void doAsyncPost(final String urlPath, final String content, final Map<String, String> headers, final IHttpCallback callback) {
        final HttpClient client = buildClient();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = doPost(client, urlPath, content, "UTF-8", headers);
                    callback.onSuccess(response);
                } catch (Exception e) {
                    callback.onException(e);
                }
            }
        });
    }

    /**
     * 提交post请求，自己指定编码
     *
     * @param urlPath
     * @param content
     * @param encoding
     * @return
     */
    public String doPost(String urlPath, String content, String encoding) throws IOException {
        HttpClient client = buildClient();
        return doPost(client, urlPath, content, encoding, null);
    }

    /**
     * 提交post请求，自己指定编码，包含自定义请求头
     *
     * @param urlPath
     * @param content
     * @param encoding
     * @param headers
     * @return
     */
    public String doPost(String urlPath, String content, String encoding, Map<String, String> headers) throws IOException {
        HttpClient client = buildClient();
        return doPost(client, urlPath, content, encoding, headers);
    }

    /**
     * 提交post请求，直接把内容写在body里
     *
     * @param client
     * @param urlPath
     * @param content
     * @param encoding
     * @param headers
     * @return
     */
    private String doPost(HttpClient client, String urlPath, String content, String encoding, Map<String, String> headers) throws IOException {
        HttpPost post = new HttpPost(urlPath);
        try {
            if (headers != null) {
                for (Map.Entry<String, String> kv : headers.entrySet()) {
                    post.setHeader(kv.getKey(), kv.getValue());
                }
            }
            post.setEntity(new ByteArrayEntity(content.getBytes(encoding)));
            String response = client.execute(post, new EncodingResponseHandler(encoding));
            return response;
        } catch (ClientProtocolException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 提交post请求，默认UTF-8编码
     *
     * @param urlPath
     * @param params
     * @return
     */
    public String doPost(String urlPath, Map<String, String> params) throws Exception {
        return doPost(urlPath, params, "UTF-8");
    }

    /**
     * 提交post请求，默认UTF-8编码，http协议
     *
     * @param urlPath
     * @param params
     * @param encoding
     * @return
     */
    public String doPost(String urlPath, Map<String, String> params, String encoding) throws Exception {
        HttpClient client = buildClient();
        return doPost(client, urlPath, params, encoding);
    }

    /**
     * 提交post请求，参数为key/value的Map
     *
     * @param client
     * @param urlPath
     * @param params
     * @return
     */
    public String doPost(HttpClient client, String urlPath, Map<String, String> params, String encoding) throws Exception {
        try {
            RequestBuilder builder = RequestBuilder.post().setUri(new URI(urlPath));
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> param : params.entrySet()) {
                parameters.add(new BasicNameValuePair(param.getKey(), param.getValue()));
            }
            HttpEntity entity = new UrlEncodedFormEntity(parameters, Charset.forName(encoding));

            builder.setEntity(entity);
            HttpUriRequest request = builder.build();

            String response = client.execute(request, new EncodingResponseHandler(encoding));

            return response;
        } catch (URISyntaxException e) {
            throw e;
        } catch (HttpResponseException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * https客户端构造（单向https）
     *
     * @return
     */
    private HttpClient buildSSLClient() throws Exception {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(sslContext);
            HttpClientBuilder builder = HttpClientBuilder.create().setConnectionManager(connManager);

            //设置UA
            builder.setUserAgent(config.getUserAgent());

            //设置请求参数
            RequestConfig.Builder rcBuilder = RequestConfig.custom();
            rcBuilder.setConnectTimeout(config.getConnectionTimeout());
            rcBuilder.setStaleConnectionCheckEnabled(Boolean.FALSE);
            rcBuilder.setSocketTimeout(config.getTimeout());
            builder.setDefaultRequestConfig(rcBuilder.build());

            builder.setSSLSocketFactory(sslFactory);

            HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
                @Override
                public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                    if (executionCount >= 3) {
                        return false;
                    }
                    if (exception instanceof NoHttpResponseException) {
                        return true;
                    } else if (exception instanceof ClientProtocolException) {
                        return true;
                    }
                    return false;
                }
            };
            builder.setRetryHandler(retryHandler);

            return builder.build();
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * https客户端构造（双向https）
     *
     * @return
     */
    private HttpClient buildTwoWaySSLClient(String certFile, String password) throws Exception {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream instream = new FileInputStream(new File(certFile));
            try {
                keyStore.load(instream, password.toCharArray());
            } finally {
                instream.close();
            }
            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, password.toCharArray())
                    .build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

            HttpClientBuilder builder = HttpClientBuilder.create();
            builder.setSSLSocketFactory(sslFactory);
            return builder.build();
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * Http客户端构造
     *
     * @return
     */
    private HttpClient buildClient() {
        HttpClientBuilder builder = HttpClientBuilder.create().setConnectionManager(connManager);
        //设置UA
        builder.setUserAgent(config.getUserAgent());

        //设置请求参数
        RequestConfig.Builder rcBuilder = RequestConfig.custom();
        rcBuilder.setConnectTimeout(config.getConnectionTimeout());
        rcBuilder.setStaleConnectionCheckEnabled(Boolean.FALSE);
        rcBuilder.setSocketTimeout(config.getTimeout());
        builder.setDefaultRequestConfig(rcBuilder.build());

        //设置proxy
        if (config.isUseProxy()) {
            builder.setProxy(new HttpHost(config.getProxyHost(), config.getProxyPort()));
        }

        //连接复用和keep-alive设置
        final long keepAlive = config.getKeepAlive();
        if (keepAlive == 0) {
            builder.setConnectionReuseStrategy(new NoConnectionReuseStrategy());
        } else {
            builder.setConnectionReuseStrategy(new DefaultConnectionReuseStrategy());
            builder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy() {
                @Override
                public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                    final long duration = super.getKeepAliveDuration(response, context);
                    return (duration == -1) ? keepAlive : duration;
                }
            });
        }

        //重试机制
        builder.setRetryHandler(config.getRetryHandler());

        //认证机制
        if (config.getCredentialsProvider() != null) {
            builder.setDefaultCredentialsProvider(config.getCredentialsProvider());
        }

        return builder.build();
    }

    /**
     * 创建http连接池，配置最大连接数和每个Route的最大连接数
     *
     * @return
     */
    private static PoolingHttpClientConnectionManager createConnectionManager(HttpConfig configuration) {
        int ttl = configuration.getTimeToLive();
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(ttl, TimeUnit.MILLISECONDS);
        manager.setDefaultMaxPerRoute(configuration.getMaxConnectionsPerRoute());
        manager.setMaxTotal(configuration.getMaxConnections());
        //socket设置
        SocketConfig.Builder builder = SocketConfig.custom();
        builder.setSoTimeout(configuration.getTimeout());
        builder.setTcpNoDelay(Boolean.TRUE);
        manager.setDefaultSocketConfig(builder.build());
        //connectoin设置
//        manager.setDefaultConnectionConfig();

        return manager;
    }

    public static void main(String[] args) throws IOException {
        HttpAgent agent = HttpAgent.create();
        String result = agent.doGet("http://www.baidu.com");
        System.out.println(result);
    }

}
