package edu.tamu.scholars.middleware.view.validator;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.scholars.middleware.discovery.service.DiscoveryService;
import edu.tamu.scholars.middleware.view.model.CollectionView;

public abstract class CollectionFieldValidator<A extends Annotation> implements ConstraintValidator<A, CollectionView> {

    @Autowired
    private DiscoveryService discoveryService;

    @Override
    public boolean isValid(CollectionView collectionView, ConstraintValidatorContext context) {
        String collection = collectionView.getCollection();
        if (collection == null || collection.isEmpty()) {
            return false;
        }
        List<String> fields = discoveryService.getFields(collection);
        return isValidField(collectionView, fields);
    }

    protected abstract boolean isValidField(CollectionView collectionView, List<String> fields);

}
