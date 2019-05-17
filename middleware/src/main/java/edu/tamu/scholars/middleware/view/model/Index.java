package edu.tamu.scholars.middleware.view.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.solr.core.query.Criteria.OperationKey;

@Embeddable
public class Index {

    @Column(nullable = false)
    private String field;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OperationKey operationKey;

    @ElementCollection
    private List<String> options;

    public Index() {
        operationKey = OperationKey.STARTS_WITH;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public OperationKey getOperationKey() {
        return operationKey;
    }

    public void setOperationKey(OperationKey operationKey) {
        this.operationKey = operationKey;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

}
