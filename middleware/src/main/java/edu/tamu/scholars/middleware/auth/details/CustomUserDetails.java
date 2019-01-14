package edu.tamu.scholars.middleware.auth.details;

import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_DURATION_IN_DAYS;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.tamu.scholars.middleware.auth.model.User;

public class CustomUserDetails extends User implements UserDetails {

    private static final long serialVersionUID = 6674712962625174202L;

    public CustomUserDetails(User user) {
        super(user);
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(getRole().toString());
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return isActive();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return isEnabled() && isConfirmed();
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return ChronoUnit.DAYS.between(getTimestamp().toInstant(), Calendar.getInstance().toInstant()) < PASSWORD_DURATION_IN_DAYS;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return getEmail();
    }

}
