package edu.tamu.scholars.middleware.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.config.HttpConfig;
import edu.tamu.scholars.middleware.service.request.HttpRequest;

@Service
public class HttpService {

    private final static Logger logger = LoggerFactory.getLogger(HttpService.class);

    @Autowired
    private HttpConfig httpConfig;

    private static PoolingHttpClientConnectionManager connectionManager;

    private static CloseableHttpClient httpClient;

    @PostConstruct
    private void init() throws URISyntaxException {
        // @formatter:off
        RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(httpConfig.getTimeout())
            .setConnectionRequestTimeout(httpConfig.getRequestTimeout())
            .setSocketTimeout(httpConfig.getSocketTimeout())
            .build();
        
        connectionManager = new PoolingHttpClientConnectionManager();
        
        httpClient = HttpClients.custom()
            .setConnectionManager(connectionManager)
            .setConnectionTimeToLive(httpConfig.getTimeToLive(), TimeUnit.MILLISECONDS)
            .setDefaultRequestConfig(config)
            .build();
        // @formatter:on
    }

    @PreDestroy
    private void cleanUp() throws IOException {
        httpClient.close();
        connectionManager.close();
    }

    public String get(HttpRequest request) {
        String body = null;
        try (CloseableHttpResponse response = request(craftGet(request))) {
            body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("Error performing GET request: " + request.getUrl());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            logger.warn("Invalid URI: " + request.getUrl());
        }
        return body;
    }

    private CloseableHttpResponse request(HttpRequestBase request) throws IOException, URISyntaxException {
        CloseableHttpResponse response = httpClient.execute(request);
        StatusLine sl = response.getStatusLine();
        int sc = sl.getStatusCode();
        if (sc < HttpStatus.SC_OK || sc >= HttpStatus.SC_MULTIPLE_CHOICES) {
            response.close();
            throw new IOException("Incorrect response status: " + sc);
        }
        return response;
    }

    private HttpGet craftGet(HttpRequest request) throws URISyntaxException {
        HttpGet httpGet = new HttpGet(buildUrl(request));
        List<Header> headers = request.getHeaders();
        httpGet.setHeaders(headers.toArray(new Header[headers.size()]));
        return httpGet;
    }

    private URI buildUrl(HttpRequest request) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(request.getUrl());
        builder.setParameters(request.getParameters());
        return builder.build();
    }

}