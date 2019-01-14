package edu.tamu.scholars.middleware.auth;

import edu.tamu.scholars.middleware.auth.controller.request.Registration;

public class RegistrationTestUtility {

    public static Registration getMockRegistration(String firstName, String lastName, String password) {
        Registration registration = new Registration();
        registration.setFirstName(firstName);
        registration.setLastName(lastName);
        registration.setEmail(password);
        return registration;
    }

}
