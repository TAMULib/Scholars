package edu.tamu.scholars.middleware.view.validator;

import java.util.List;

import edu.tamu.scholars.middleware.view.annotation.ValidCollectionFilters;
import edu.tamu.scholars.middleware.view.model.CollectionView;
import edu.tamu.scholars.middleware.view.model.Filter;

public class CollectionFiltersValidator extends CollectionFieldValidator<ValidCollectionFilters> {

    @Override
    protected boolean isValidField(CollectionView collectionView, List<String> fields) {
        List<Filter> filters = collectionView.getFilters();
        if (filters.isEmpty()) {
            return true;
        }
        for (Filter filter : filters) {
            boolean facetValid = false;
            for (String field : fields) {
                if (field.equals(filter.getField())) {
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
