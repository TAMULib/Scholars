package edu.tamu.scholars.middleware.view.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.scholars.middleware.discovery.service.DiscoveryService;
import edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection;

public class DiscoveryCollectionValidator implements ConstraintValidator<ValidDiscoveryCollection, String> {

    @Autowired
    private DiscoveryService discoveryService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return discoveryService.isCollection(value);
    }

}
