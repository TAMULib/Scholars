package edu.tamu.scholars.middleware.auth.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.scholars.middleware.auth.annotation.AvailableEmail;
import edu.tamu.scholars.middleware.auth.model.repo.UserRepo;

public class EmailConstraintValidator implements ConstraintValidator<AvailableEmail, String> {

    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (userRepo.existsByEmail(email)) {
            return false;
        }
        return true;
    }

}
