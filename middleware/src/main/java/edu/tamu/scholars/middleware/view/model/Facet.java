package edu.tamu.scholars.middleware.view.model;

import static edu.tamu.scholars.middleware.view.model.FacetType.STRING;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.solr.core.query.FacetOptions.FacetSort.COUNT;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.solr.core.query.FacetOptions.FacetSort;

@Embeddable
public class Facet {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String field;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FacetType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FacetSort sort;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Column(nullable = false, name = "default_limit")
    private int limit;

    @Column(nullable = false)
    private boolean collapsed;

    @Column(nullable = false)
    private boolean hidden;

    public Facet() {
        type = STRING;
        sort = COUNT;
        direction = DESC;
        limit = 10;
        collapsed = true;
        hidden = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public FacetType getType() {
        return type;
    }

    public void setType(FacetType type) {
        this.type = type;
    }

    public FacetSort getSort() {
        return sort;
    }

    public void setSort(FacetSort sort) {
        this.sort = sort;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

}
