package edu.tamu.scholars.middleware.view.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "result_views")
public class ResultView extends View {

    private static final long serialVersionUID = 5222045969260239502L;

    @NotNull
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
