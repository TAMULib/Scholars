package edu.tamu.scholars.middleware.service;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.config.VivoConfig;
import edu.tamu.scholars.middleware.service.request.HttpRequest;

@Service
public class VivoService {

    private final static Logger logger = LoggerFactory.getLogger(VivoService.class);

    @Autowired
    private VivoConfig vivoConfig;

    @Autowired
    private HttpService httpService;

    public String list(String predicate, String accept) {
        if (logger.isDebugEnabled()) {
            logger.debug("VIVO list");
            logger.debug("predicate: " + predicate);
            logger.debug("accept: " + accept);
        }
        HttpRequest request = new HttpRequest();

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", accept));

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("vclass", predicate));

        request.setUrl(vivoConfig.getListRdfEndpointUrl());
        request.setHeaders(headers);
        request.setParameters(parameters);

        String response = httpService.get(request);
        if (logger.isDebugEnabled()) {
            logger.debug("response:\n" + response);
        }
        return response;
    }

    public String individual(String id, String accept) {
        if (logger.isDebugEnabled()) {
            logger.debug("VIVO linked open data");
            logger.debug("id: " + id);
            logger.debug("accept: " + accept);
        }
        HttpRequest request = new HttpRequest();

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", accept));

        request.setUrl(vivoConfig.getLinkedOpenDataEndpointUrl() + "/" + id);
        request.setHeaders(headers);

        String response = httpService.get(request);
        if (logger.isDebugEnabled()) {
            logger.debug("response:\n" + response);
        }
        return response;
    }

    public String sparql(String query, String accept) {
        if (logger.isDebugEnabled()) {
            logger.debug("VIVO sparql");
            logger.debug("query: " + query);
            logger.debug("accept: " + accept);
        }
        HttpRequest request = new HttpRequest();

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", accept));

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("email", vivoConfig.getEmail()));
        parameters.add(new BasicNameValuePair("password", vivoConfig.getPassword()));
        parameters.add(new BasicNameValuePair("query", query));

        request.setUrl(vivoConfig.getSparqlQueryEndpointUrl());
        request.setHeaders(headers);
        request.setParameters(parameters);

        String response = httpService.get(request);
        if (logger.isDebugEnabled()) {
            logger.debug("response:\n" + response);
        }
        return response;
    }

    public Model toRdfModel(String rdf, String lang) {
        InputStream stream = new ByteArrayInputStream(rdf.getBytes(UTF_8));
        Model model = ModelFactory.createDefaultModel();
        model.read(stream, null, lang);
        return model;
    }

}
