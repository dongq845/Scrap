package com.scrap.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.scrap.demo.entity.Role;
import com.scrap.demo.entity.User;

// *** Tests written here do NOT actually modify the database. ***

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryNormalTest {
    // Fields
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    public void testCreateReadSingleUser() {
        // Set parameters
        String username = "Username";
        String passwordHashed = "PasswordHashed";
        String passwordSalt = "PasswordSalt";
        String email = "Email";
        Role role = Role.user;
        Date signupDate = new Date();

        // Test - Create
        User user = new User();
        user.setUsername(username);
        user.setPasswordHashed(passwordHashed);
        user.setPasswordSalt(passwordSalt);
        user.setEmail(email);
        user.setRole(role);
        user.setSignupDate(signupDate);

        // Test - Create - Save User
        userRepository.save(user);
        assertNotNull(user.getUserID(), "==== User ID should not be null after create ====");

        // Test - Read
        Long theUserID = user.getUserID();
        Optional<User> savedUserOptional = userRepository.findById(theUserID);
        assertTrue(savedUserOptional.isPresent(), "==== User must be found using UserID ====");
        User savedUser = savedUserOptional.get();
        assertEquals(username, savedUser.getUsername(), "==== Username must match ====");
        assertEquals(passwordHashed, savedUser.getPasswordHashed(), "==== PasswordHashed must match ====");
        assertEquals(passwordSalt, savedUser.getPasswordSalt(), "==== PasswordSalt must match ====");
        assertEquals(email, savedUser.getEmail(), "==== Email must match ====");
        assertEquals(role, savedUser.getRole(), "==== Role must match ====");
    }

    @Test
    @Order(2)
    public void testUpdateSingleUser() {
        // Set parameters
        String usernameFormat = "Username";
        String passwordHashedFormat = "PasswordHashed";
        String passwordSaltFormat = "PasswordSalt";
        String emailFormat = "Email";
        Role roleFormat = Role.user;
        Date signupDateFormat = new Date();

        // Create new user
        User user = new User();
        user.setUsername(usernameFormat);
        user.setPasswordHashed(passwordHashedFormat);
        user.setPasswordSalt(passwordSaltFormat);
        user.setEmail(emailFormat);
        user.setRole(roleFormat);
        user.setSignupDate(signupDateFormat);

        // Save the new user
        userRepository.save(user);
        assertNotNull(user.getUserID(), "==== User ID should not be null after create ====");

        // Update the saved new user
        String updated = "Updated";
        Optional<User> foundUserOptional = userRepository.findByUsername(usernameFormat);
        assertTrue(foundUserOptional.isPresent(), "==== User must be found using username ====");
        User foundUser = foundUserOptional.get();
        foundUser.setUsername(updated + usernameFormat);
        foundUser.setPasswordHashed(updated + passwordHashedFormat);
        foundUser.setPasswordSalt(updated + passwordSaltFormat);
        foundUser.setEmail(updated + emailFormat);
        foundUser.setRole(Role.admin);
        userRepository.save(foundUser);

        // Read and compare the update values
        Optional<User> updatedUserOptional = userRepository.findByUsername(updated + usernameFormat);
        assertTrue(updatedUserOptional.isPresent(), "==== Updated User must be found using updated username ====");
        User updatedUser = updatedUserOptional.get();
        assertEquals(updated + usernameFormat, updatedUser.getUsername(),
                "==== Updated Username must match ====");
        assertEquals(updated + passwordHashedFormat, updatedUser.getPasswordHashed(),
                "==== Updated PasswordHashed must match ====");
        assertEquals(updated + passwordSaltFormat, updatedUser.getPasswordSalt(),
                "==== Updated PasswordSalt must match ====");
        assertEquals(updated + emailFormat, updatedUser.getEmail(), "==== Updated Email must match ====");
        assertEquals(Role.admin, updatedUser.getRole(), "==== Updated Role must match ====");
    }

    @Test
    @Order(3)
    public void testDeleteSingleUser() {
        // Set parameters
        String usernameFormat = "Username";
        String passwordHashedFormat = "PasswordHashed";
        String passwordSaltFormat = "PasswordSalt";
        String emailFormat = "Email";
        Role roleFormat = Role.user;
        Date signupDateFormat = new Date();

        // Create new user
        User user = new User();
        user.setUsername(usernameFormat);
        user.setPasswordHashed(passwordHashedFormat);
        user.setPasswordSalt(passwordSaltFormat);
        user.setEmail(emailFormat);
        user.setRole(roleFormat);
        user.setSignupDate(signupDateFormat);

        // Save the new user
        userRepository.save(user);
        assertNotNull(user.getUserID(), "==== User ID should not be null after create ====");

        // Retrieve the user
        Optional<User> foundUserOptional = userRepository.findByUsername(usernameFormat);
        assertNotNull(foundUserOptional, "==== User must be found using Username ====");
        User foundUser = foundUserOptional.get();

        // Delete the found user
        userRepository.delete(foundUser);

        // Try to retrieve the deleted user - must be null
        Optional<User> trialUserOptional = userRepository.findByUsername(usernameFormat);
        assertFalse(trialUserOptional.isPresent(), "==== User should not be found using Username ====");
    }

    @Test
    @Order(4)
    public void testCreateReadMultipleUsers() {
        // Set parameters
        String usernameFormat = "Username";
        String passwordHashedFormat = "PasswordHashed";
        String passwordSaltFormat = "PasswordSalt";
        String emailFormat = "Email";
        Role roleFormat = Role.user;
        Date signupDateFormat = new Date();

        // Set variables for this test method
        int numUsersToCreate = 20;
        char charFlag = 'A';

        for (int i = 0; i < numUsersToCreate; i++) {
            // Test - Create
            // Create new user
            User user = new User();
            user.setUsername(usernameFormat + charFlag);
            user.setPasswordHashed(passwordHashedFormat + charFlag);
            user.setPasswordSalt(passwordSaltFormat + charFlag);
            user.setEmail(emailFormat + charFlag);
            user.setRole(roleFormat);
            user.setSignupDate(signupDateFormat);

            // Test - Create - Save User
            userRepository.save(user);
            assertNotNull(user.getUserID(), "==== User ID should not be null after create ====");

            // Test - Read
            Long theUserID = user.getUserID();
            Optional<User> savedUserOptional = userRepository.findById(theUserID);
            assertTrue(savedUserOptional.isPresent(), "==== User must be found using UserID ====");
            User savedUser = savedUserOptional.get();
            assertEquals(usernameFormat + charFlag, savedUser.getUsername(), "==== Username must match ====");
            assertEquals(passwordHashedFormat + charFlag, savedUser.getPasswordHashed(),
                    "==== PasswordHashed must match ====");
            assertEquals(passwordSaltFormat + charFlag, savedUser.getPasswordSalt(),
                    "==== PasswordSalt must match ====");
            assertEquals(emailFormat + charFlag, savedUser.getEmail(), "==== Email must match ====");
            assertEquals(roleFormat, savedUser.getRole(), "==== Role must match ====");

            // Update flag
            charFlag += 1;
        }
    }
}
