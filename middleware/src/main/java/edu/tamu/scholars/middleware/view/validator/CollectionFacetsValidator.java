package edu.tamu.scholars.middleware.view.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.scholars.middleware.discovery.service.DiscoveryService;
import edu.tamu.scholars.middleware.view.annotation.ValidCollectionFacets;
import edu.tamu.scholars.middleware.view.model.CollectionView;
import edu.tamu.scholars.middleware.view.model.Facet;

public class CollectionFacetsValidator implements ConstraintValidator<ValidCollectionFacets, CollectionView> {

    @Autowired
    private DiscoveryService discoveryService;

    @Override
    public boolean isValid(CollectionView collectionView, ConstraintValidatorContext context) {
        String collection = collectionView.getCollection();
        if (collection == null) {
            return false;
        }
        List<Facet> facets = collectionView.getFacets();
        if (facets.isEmpty()) {
            return true;
        }
        List<String> fields = discoveryService.getFields(collection);
        for (Facet facet : facets) {
            boolean facetValid = false;
            for (String field : fields) {
                if (field.equals(facet.getField())) {
                    facetValid = true;
                    break;
                }
            }
            if (!facetValid) {
                return false;
            }
        }
        return true;
    }

}
