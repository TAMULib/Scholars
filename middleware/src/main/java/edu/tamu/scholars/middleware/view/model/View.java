package edu.tamu.scholars.middleware.view.model;

import javax.persistence.MappedSuperclass;

import edu.tamu.scholars.middleware.theme.model.Named;

@MappedSuperclass
public abstract class View extends Named {

    private static final long serialVersionUID = 413593021970972190L;

    public View() {
        super();
    }

}
