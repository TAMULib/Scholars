package edu.tamu.scholars.middleware.view.validator;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.tamu.scholars.middleware.discovery.utility.DiscoveryUtility;
import edu.tamu.scholars.middleware.view.model.CollectionView;

public abstract class CollectionFieldValidator<A extends Annotation> implements ConstraintValidator<A, CollectionView> {

    @Override
    public boolean isValid(CollectionView collectionView, ConstraintValidatorContext context) {
        String collection = collectionView.getCollection();
        if (collection == null || collection.isEmpty()) {
            return false;
        }
        List<String> fields = DiscoveryUtility.getFields(collection);
        return isValidField(collectionView, fields);
    }

    protected abstract boolean isValidField(CollectionView collectionView, List<String> fields);

}
