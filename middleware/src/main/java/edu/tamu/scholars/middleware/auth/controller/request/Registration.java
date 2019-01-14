package edu.tamu.scholars.middleware.auth.controller.request;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.auth.annotation.AvailableEmail;
import edu.tamu.scholars.middleware.auth.annotation.ValidPassword;
import edu.tamu.scholars.middleware.auth.validator.group.CompleteRegistration;
import edu.tamu.scholars.middleware.auth.validator.group.SubmitRegistration;

@ValidPassword(groups = CompleteRegistration.class, message = "{Registration.passwordInvalid}")
public class Registration {

    @Size(min = 2, max = 64, groups = { SubmitRegistration.class, CompleteRegistration.class }, message = "{Registration.firstNameSize}")
    private String firstName;

    @Size(min = 2, max = 64, groups = { SubmitRegistration.class, CompleteRegistration.class }, message = "{Registration.lastNameSize}")
    private String lastName;

    @NotNull(message = "{Registration.emailRequired}", groups = { SubmitRegistration.class, CompleteRegistration.class })
    @NotEmpty(message = "{Registration.emailRequired}", groups = { SubmitRegistration.class, CompleteRegistration.class })
    @AvailableEmail(groups = SubmitRegistration.class, message = "{Registration.emailAlreadyInUse}")
    @Email(message = "{Registration.emailInvalid}", groups = { SubmitRegistration.class, CompleteRegistration.class })
    private String email;

    @JsonInclude(NON_NULL)
    private String password;

    @JsonInclude(NON_NULL)
    private String confirm;

    public Registration() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

}
