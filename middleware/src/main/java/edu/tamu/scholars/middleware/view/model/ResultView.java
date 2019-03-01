package edu.tamu.scholars.middleware.view.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "result_views")
public class ResultView extends View {

    private static final long serialVersionUID = 5222045969260239502L;

    @Column(nullable = false, unique = true)
    private String name;

    public ResultView() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getIdentifier() {
        return name;
    }

}
