package edu.tamu.scholars.middleware.auth.model;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;
import static org.springframework.beans.BeanUtils.copyProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -7535464109980348619L;

    @Id
    @JsonInclude(Include.NON_EMPTY)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull(message = "${User.firstNameRequired}")
    @Size(min = 2, max = 64, message = "${User.firstNameSize}")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "${User.lastNameRequired}")
    @Size(min = 2, max = 64, message = "${User.lastNameSize}")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "{User.emailRequired}")
    @Email(message = "{User.emailInvalid}")
    @Column(nullable = false, unique = true)
    private String email;

    @JsonProperty(access = WRITE_ONLY)
    @Column
    private String password;

    @JsonIgnore
    @ElementCollection(fetch = EAGER)
    private List<String> oldPasswords;

    @NotNull(message = "{User.roleRequired}")
    @Enumerated(STRING)
    @Column(nullable = false)
    private Role role;

    @JsonIgnore
    @CreationTimestamp
    @Temporal(TIMESTAMP)
    @Column(nullable = false)
    private Calendar created;

    @JsonIgnore
    @UpdateTimestamp
    @Temporal(TIMESTAMP)
    @Column(nullable = false)
    private Calendar timestamp;

    @JsonIgnore
    @Column(nullable = false)
    private boolean confirmed;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean enabled;

    public User() {
        super();
        this.oldPasswords = new ArrayList<String>();
        this.role = Role.ROLE_USER;
        this.created = Calendar.getInstance();
        this.timestamp = Calendar.getInstance();
        this.confirmed = false;
        this.active = true;
        this.enabled = false;
    }

    public User(String firstName, String lastName, String email) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(User user) {
        this();
        copyProperties(user, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getOldPasswords() {
        return oldPasswords;
    }

    public void setOldPasswords(List<String> oldPasswords) {
        this.oldPasswords = oldPasswords;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}