package edu.tamu.scholars.middleware.harvest.service.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import edu.tamu.scholars.middleware.harvest.annotation.Source;

public class SolrDocumentBuilder {

    private final Map<String, List<String>> collections;

    private final String subject;

    private final Source source;

    private Model model;

    private Resource resource;

    private String[] predicates;

    public SolrDocumentBuilder(String subject, Source source, Model model) {

        this.subject = subject;
        this.source = source;
        this.model = model;

        this.collections = new HashMap<String, List<String>>();

        for (Source.Property property : source.properties()) {
            this.collections.put(property.name(), new ArrayList<String>());
            if (!property.id().isEmpty()) {
                this.collections.put(property.id(), new ArrayList<String>());
            }
        }
        for (Source.Sparql sparql : source.sparql()) {
            for (Source.Property property : sparql.properties()) {
                this.collections.put(property.name(), new ArrayList<String>());
                if (!property.id().isEmpty()) {
                    this.collections.put(property.id(), new ArrayList<String>());
                }
            }
        }
    }

    public void add(String property, String value) {
        if (this.collections.containsKey(property)) {
            if (value.contains("^^")) {
                value = value.substring(0, value.indexOf("^^"));
            }
            this.collections.get(property).add(value);
        }
    }

    public Map<String, List<String>> getCollections() {
        return collections;
    }

    public String getSubject() {
        return subject;
    }

    public Source getSource() {
        return source;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String[] getPredicates() {
        return predicates;
    }

    public void setPredicates(String[] predicates) {
        this.predicates = predicates;
    }

    public static SolrDocumentBuilder of(String subject, Source source, Model model) {
        return new SolrDocumentBuilder(subject, source, model);
    }

}
