package edu.tamu.scholars.middleware.view.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import edu.tamu.scholars.middleware.view.annotation.ValidIndexField;

@Entity
@Table(name = "directory_views")
@ValidIndexField(message = "{DirectoryView.validIndexField}")
public class DirectoryView extends CollectionView {

    private static final long serialVersionUID = -3039416050754710347L;

    @Embedded
    private Index index;

    public DirectoryView() {
        super();
    }

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }

}
