package edu.tamu.scholars.middleware;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import edu.tamu.scholars.middleware.auth.model.Role;
import edu.tamu.scholars.middleware.auth.model.User;
import edu.tamu.scholars.middleware.auth.model.repo.UserRepo;

@Component
public class RandomDataLoader implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(RandomDataLoader.class);

    @Autowired
    private UserRepo userRepo;

    @Lazy
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User superAdmin = new User();
        superAdmin.setFirstName("William");
        superAdmin.setLastName("Welling");
        superAdmin.setEmail("wwelling@library.tamu.edu");
        superAdmin.setPassword(bCryptPasswordEncoder.encode("HelloWorld123!"));
        superAdmin.setRole(Role.ROLE_SUPER_ADMIN);
        superAdmin.setConfirmed(true);
        superAdmin.setActive(true);
        superAdmin.setEnabled(true);
        userRepo.save(superAdmin);
        User admin = new User();
        admin.setFirstName("Bob");
        admin.setLastName("Boring");
        admin.setEmail("wwelling@outlook.com");
        admin.setPassword(bCryptPasswordEncoder.encode("HelloWorld123!"));
        admin.setRole(Role.ROLE_ADMIN);
        admin.setConfirmed(true);
        admin.setActive(true);
        admin.setEnabled(true);
        userRepo.save(admin);
        for (int i = 0; i < 10; i++) {
            createRandomUser();
        }
    }

    public void createRandomUser() {
        User user = new User();
        user.setFirstName(randomString(4, 16));
        user.setLastName(randomString(4, 32));
        user.setEmail(randomString(4, 16) + "@mailinator.com");
        user.setPassword(bCryptPasswordEncoder.encode("HelloWorld123!"));
        user.setRole(Role.values()[randomInteger(0, 2)]);
        user.setConfirmed(randomBoolean());
        user.setActive(randomBoolean());
        user.setEnabled(randomBoolean());
        user = userRepo.save(user);
        logger.info(user.getId() + ": " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
    }

    public String randomString(int min, int max) {
        return RandomStringUtils.randomAlphabetic(min, max);
    }

    public int randomInteger(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public boolean randomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

}
