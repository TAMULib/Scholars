package edu.tamu.scholars.middleware.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.tamu.scholars.middleware.view.assembler.AbstractCollectionViewResourceAssembler;
import edu.tamu.scholars.middleware.view.model.CollectionView;
import edu.tamu.scholars.middleware.view.model.repo.ViewRepo;
import edu.tamu.scholars.middleware.view.resource.AbstractCollectionViewResource;

public abstract class AbstractCollectionViewController<V extends CollectionView, VR extends ViewRepo<V>, R extends AbstractCollectionViewResource<V>, CVA extends AbstractCollectionViewResourceAssembler<V, R>> {

    @Autowired
    private VR repo;

    @Autowired
    private CVA assembler;

    @GetMapping("/{id}")
    public ResponseEntity<Resource<V>> one(@PathVariable Long id) {
        Optional<V> view = repo.findById(id);
        if (!view.isPresent()) {
            throw new ResourceNotFoundException(String.format("Unabled to find collection view with id %s!", id));

        }
        return ResponseEntity.ok(assembler.toResource(view.get()));
    }

    @GetMapping
    public ResponseEntity<Resources<Resource<V>>> all() {
        List<Resource<V>> employees = repo.findAll().stream().map(assembler::toResource).collect(Collectors.toList());
        return ResponseEntity.ok(new Resources<>(employees, ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).all()).withSelfRel()));
    }

}
