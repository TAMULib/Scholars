package edu.tamu.scholars.middleware.service.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

public class HttpRequest {

    private String url;

    private List<NameValuePair> parameters;

    private List<Header> headers;

    public HttpRequest() {

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

    public static HttpRequest linkedOpenDataRdf(String url, String id) {
        HttpRequest request = new HttpRequest();
        request.setUrl(url + "/" + id + "/" + id + ".rdf");
        return request;
    }

    public static HttpRequest listRdf(String url, String vclass) {
        HttpRequest request = new HttpRequest();

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/rdf+xml"));

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("vclass", vclass));

        request.setUrl(url);
        request.setHeaders(headers);
        request.setParameters(parameters);
        return request;
    }

    // NOTE: VIVO sparql endpoint only provides rdf for CONSTRUCT and DESCRIBE queries
    public static HttpRequest sparqlRdf(String url, String username, String password, String query) {
        HttpRequest request = new HttpRequest();

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/rdf+xml"));

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("email", username));
        parameters.add(new BasicNameValuePair("password", password));
        parameters.add(new BasicNameValuePair("query", query));

        request.setUrl(url);
        request.setHeaders(headers);
        request.setParameters(parameters);
        return request;
    }

    // NOTE: VIVO sparql endpoint only provides json for SELECT queries
    public static HttpRequest sparqlJson(String url, String username, String password, String query) {
        HttpRequest request = new HttpRequest();

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/sparql-results+json"));

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("email", username));
        parameters.add(new BasicNameValuePair("password", password));
        parameters.add(new BasicNameValuePair("query", query));

        request.setUrl(url);
        request.setHeaders(headers);
        request.setParameters(parameters);
        return request;
    }

}