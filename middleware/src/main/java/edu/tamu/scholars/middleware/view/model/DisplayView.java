package edu.tamu.scholars.middleware.view.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "display_views")
public class DisplayView extends ResourceView {

    private static final long serialVersionUID = 7556127622115170597L;

    public DisplayView() {
        super();
    }

}
