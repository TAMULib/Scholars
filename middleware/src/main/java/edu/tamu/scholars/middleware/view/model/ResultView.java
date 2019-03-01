package edu.tamu.scholars.middleware.view.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "result_views")
public class ResultView extends View {

    private static final long serialVersionUID = 5222045969260239502L;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String template;

    public ResultView() {
        super();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}
