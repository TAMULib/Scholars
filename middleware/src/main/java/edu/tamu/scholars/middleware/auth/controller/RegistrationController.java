package edu.tamu.scholars.middleware.auth.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.tamu.scholars.middleware.auth.controller.exception.RegistrationException;
import edu.tamu.scholars.middleware.auth.controller.request.Registration;
import edu.tamu.scholars.middleware.auth.model.User;
import edu.tamu.scholars.middleware.auth.service.RegistrationService;
import edu.tamu.scholars.middleware.auth.validator.group.CompleteRegistration;
import edu.tamu.scholars.middleware.auth.validator.group.SubmitRegistration;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Registration> submit(@RequestBody @Validated(SubmitRegistration.class) Registration registration) throws JsonProcessingException {
        return ResponseEntity.ok(registrationService.submit(registration));
    }

    @GetMapping
    public ResponseEntity<Registration> confirm(@RequestParam(required = true) String key) throws JsonParseException, JsonMappingException, IOException, RegistrationException {
        return ResponseEntity.ok(registrationService.confirm(key));
    }

    @PutMapping
    public ResponseEntity<User> complete(@RequestBody @Validated(CompleteRegistration.class) Registration registration) throws RegistrationException {
        return ResponseEntity.ok(registrationService.complete(registration));
    }

}
