package edu.tamu.scholars.middleware.auth;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.tamu.scholars.middleware.auth.model.Role;
import edu.tamu.scholars.middleware.auth.model.User;
import edu.tamu.scholars.middleware.auth.model.repo.UserRepo;

public abstract class UserIntegrationTest {

    @Autowired
    protected UserRepo userRepo;

    @Autowired
    protected BCryptPasswordEncoder bCryptPasswordEncoder;

    protected User createMockUser() {
        return userRepo.save(getMockUser());
    }

    protected User createMockAdmin() {
        return userRepo.save(getMockAdmin());
    }

    protected User createMockSuperAdmin() {
        return userRepo.save(getMockSuperAdmin());
    }

    protected User getMockUser() {
        return getMockUser("Bob", "Boring", "bboring@mailinator.com", "HelloWorld123!", Role.ROLE_USER);
    }

    protected User getMockAdmin() {
        return getMockUser("Eddie", "Exciting", "eexciting@mailinator.com", "HelloWorld123!", Role.ROLE_ADMIN);
    }

    protected User getMockSuperAdmin() {
        return getMockUser("Super", "Admin", "superadmin@mailinator.com", "HelloWorld123!", Role.ROLE_SUPER_ADMIN);
    }

    protected User getMockUser(String firstName, String lastName, String email, String password, Role role) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole(role);
        user.setConfirmed(true);
        user.setActive(true);
        user.setEnabled(true);
        return user;
    }

    @After
    public void deleteAllUsers() {
        userRepo.deleteAll();
    }

}
