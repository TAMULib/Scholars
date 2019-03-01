package edu.tamu.scholars.middleware.view.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.scholars.middleware.discovery.service.DiscoveryService;
import edu.tamu.scholars.middleware.view.annotation.ValidIndexField;
import edu.tamu.scholars.middleware.view.model.DirectoryView;
import edu.tamu.scholars.middleware.view.model.Index;

public class IndexFieldValidator implements ConstraintValidator<ValidIndexField, DirectoryView> {

    @Autowired
    private DiscoveryService discoveryService;

    @Override
    public boolean isValid(DirectoryView directoryView, ConstraintValidatorContext context) {
        String collection = directoryView.getCollection();
        Index index = directoryView.getIndex();
        if (collection == null || index == null) {
            return false;
        }
        return discoveryService.hasIndexField(collection, index.getField());
    }

}
