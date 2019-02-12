package edu.tamu.scholars.middleware.service.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;

public class HttpRequest {

    private String url;

    private List<NameValuePair> parameters;

    private List<Header> headers;

    public HttpRequest() {
        parameters = new ArrayList<NameValuePair>();
        headers = new ArrayList<Header>();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<NameValuePair> getParameters() {
        return parameters;
    }

    public void setParameters(List<NameValuePair> parameters) {
        this.parameters = parameters;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

}