package edu.tamu.scholars.middleware.view.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.tamu.scholars.middleware.discovery.utility.DiscoveryUtility;
import edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection;

public class DiscoveryCollectionValidator implements ConstraintValidator<ValidDiscoveryCollection, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return DiscoveryUtility.isCollection(value);
    }

}
