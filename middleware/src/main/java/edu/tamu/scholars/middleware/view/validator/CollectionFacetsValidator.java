package edu.tamu.scholars.middleware.view.validator;

import java.util.List;

import edu.tamu.scholars.middleware.view.annotation.ValidCollectionFacets;
import edu.tamu.scholars.middleware.view.model.CollectionView;
import edu.tamu.scholars.middleware.view.model.Facet;

public class CollectionFacetsValidator extends CollectionFieldValidator<ValidCollectionFacets> {

    @Override
    protected boolean isValidField(CollectionView collectionView, List<String> fields) {
        List<Facet> facets = collectionView.getFacets();
        if (facets.isEmpty()) {
            return true;
        }
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
