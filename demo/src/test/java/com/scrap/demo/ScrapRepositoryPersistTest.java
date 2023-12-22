package com.scrap.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scrap.demo.entity.Role;
import com.scrap.demo.entity.Scrap;
import com.scrap.demo.entity.User;

// *** Tests written here DO MODIFY the database. ***

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ScrapRepositoryPersistTest {
    // Fields
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScrapRepository scrapRepository;

    @Test
    @Order(1)
    public void testCreateReadSingleScrap() {
        // Set parameters - User
        String username = "Username";
        String passwordHashed = "PasswordHashed";
        String passwordSalt = "PasswordSalt";
        String email = "Email";
        Role role = Role.user;
        Date signupDate = new Date();

        // Test - Create User
        User user = new User();
        user.setUsername(username);
        user.setPasswordHashed(passwordHashed);
        user.setPasswordSalt(passwordSalt);
        user.setEmail(email);
        user.setRole(role);
        user.setSignupDate(signupDate);

        // Test - Save User
        userRepository.save(user);
        Optional<User> foundUserOptional = userRepository.findByUsername(username);
        assertNotNull(user.getUserID(), "==== User ID must be set after save ====");
        User foundUser = foundUserOptional.get();

        // Set parameters - Scrap
        Long foundUserID = foundUser.getUserID();
        String title = "Title";
        String content = "TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent";
        String author = "Author";
        String url = "http://www.somewebsite.com/rest_of_url";
        Date scrapDate = new Date();

        // Test - Create Scrap for User
        Scrap scrap = new Scrap();
        scrap.setUserID(foundUserID);
        scrap.setTitle(title);
        scrap.setContent(content);
        scrap.setAuthor(author);
        scrap.setUrl(url);
        scrap.setScrapDate(scrapDate);

        // Test - Save Scrap
        scrapRepository.save(scrap);
        assertNotNull(scrap.getScrapID(), "==== Scrap ID must be set after save ====");

        // Test - Read
        Optional<Scrap> foundScrapOptional = scrapRepository.findById(scrap.getScrapID());
        assertTrue(foundScrapOptional.isPresent(), "==== Scrap must be found using ScrapID ====");
        Scrap foundScrap = foundScrapOptional.get();
        assertEquals(foundUserID, foundScrap.getUserID(), "==== User ID must match ====");
        assertEquals(title, foundScrap.getTitle(), "==== Title must match ====");
        assertEquals(content, foundScrap.getContent(), "==== Content must match ====");
        assertEquals(author, foundScrap.getAuthor(), "==== Author must match ====");
        assertEquals(url, foundScrap.getUrl(), "==== URL must match ====");
    }

    @Test
    @Order(2)
    public void testUpdateSingleScrap() {
        // Set parameters - User
        String username = "Username";
        String passwordHashed = "PasswordHashed";
        String passwordSalt = "PasswordSalt";
        String email = "Email";
        Role role = Role.user;
        Date signupDate = new Date();

        // Test - Create User
        User user = new User();
        user.setUsername(username);
        user.setPasswordHashed(passwordHashed);
        user.setPasswordSalt(passwordSalt);
        user.setEmail(email);
        user.setRole(role);
        user.setSignupDate(signupDate);

        // Test - Save User
        userRepository.save(user);
        Optional<User> foundUserOptional = userRepository.findByUsername(username);
        assertNotNull(user.getUserID(), "==== User ID must be set after save ====");
        User foundUser = foundUserOptional.get();

        // Set parameters - Scrap
        Long foundUserID = foundUser.getUserID();
        String title = "Title";
        String content = "TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent";
        String author = "Author";
        String url = "http://www.somewebsite.com/rest_of_url";
        Date scrapDate = new Date();

        // Test - Create Scrap for User
        Scrap scrap = new Scrap();
        scrap.setUserID(foundUserID);
        scrap.setTitle(title);
        scrap.setContent(content);
        scrap.setAuthor(author);
        scrap.setUrl(url);
        scrap.setScrapDate(scrapDate);

        // Test - Save Scrap
        scrapRepository.save(scrap);
        assertNotNull(scrap.getScrapID(), "==== Scrap ID must be set after save ====");

        // Test - Read
        Optional<Scrap> foundScrapOptional = scrapRepository.findById(scrap.getScrapID());
        assertTrue(foundScrapOptional.isPresent(), "==== Scrap must be found using ScrapID ====");
        Scrap foundScrap = foundScrapOptional.get();
        assertEquals(foundUserID, foundScrap.getUserID(), "==== User ID must match ====");
        assertEquals(title, foundScrap.getTitle(), "==== Title must match ====");
        assertEquals(content, foundScrap.getContent(), "==== Content must match ====");
        assertEquals(author, foundScrap.getAuthor(), "==== Author must match ====");
        assertEquals(url, foundScrap.getUrl(), "==== URL must match ====");

        // Update found scrap
        String updated = "Updated";
        foundScrap.setTitle(updated + title);
        foundScrap.setContent(updated + content);
        foundScrap.setAuthor(updated + author);
        foundScrap.setUrl(updated + url);

        // Save updated Scrap
        scrapRepository.save(foundScrap);

        // Test - Update
        Optional<Scrap> updatedScrapOptional = scrapRepository.findById(foundScrap.getScrapID());
        assertTrue(updatedScrapOptional.isPresent(), "==== Updated Scrap must be found using ScrapID ====");
        Scrap updatedScrap = updatedScrapOptional.get();
        assertEquals(updated + title, updatedScrap.getTitle(), "==== Updated Title must match ====");
        assertEquals(updated + content, updatedScrap.getContent(), "==== Updated Title must match ====");
        assertEquals(updated + author, updatedScrap.getAuthor(), "==== Updated Title must match ====");
        assertEquals(updated + url, updatedScrap.getUrl(), "==== Updated Title must match ====");
    }

    @Test
    @Order(3)
    public void testDeleteSingleScrap() {
        // Set parameters - User
        String username = "UsernameD";
        String passwordHashed = "PasswordHashedD";
        String passwordSalt = "PasswordSaltD";
        String email = "EmailD";
        Role role = Role.user;
        Date signupDate = new Date();

        // Test - Create User
        User user = new User();
        user.setUsername(username);
        user.setPasswordHashed(passwordHashed);
        user.setPasswordSalt(passwordSalt);
        user.setEmail(email);
        user.setRole(role);
        user.setSignupDate(signupDate);

        // Test - Save User
        userRepository.save(user);
        Optional<User> foundUserOptional = userRepository.findByUsername(username);
        assertNotNull(user.getUserID(), "==== User ID must be set after save ====");
        User foundUser = foundUserOptional.get();

        // Set parameters - Scrap
        Long foundUserID = foundUser.getUserID();
        String title = "TitleD";
        String content = "TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent";
        String author = "Author";
        String url = "http://www.somewebsite.com/rest_of_url";
        Date scrapDate = new Date();

        // Test - Create Scrap for User
        Scrap scrap = new Scrap();
        scrap.setUserID(foundUserID);
        scrap.setTitle(title);
        scrap.setContent(content);
        scrap.setAuthor(author);
        scrap.setUrl(url);
        scrap.setScrapDate(scrapDate);

        // Test - Save Scrap
        scrapRepository.save(scrap);
        assertNotNull(scrap.getScrapID(), "==== Scrap ID must be set after save ====");

        // Test - Read
        Optional<Scrap> foundScrapOptional = scrapRepository.findById(scrap.getScrapID());
        assertTrue(foundScrapOptional.isPresent(), "==== Scrap must be found using ScrapID ====");
        Scrap foundScrap = foundScrapOptional.get();
        assertEquals(foundUserID, foundScrap.getUserID(), "==== User ID must match ====");
        assertEquals(title, foundScrap.getTitle(), "==== Title must match ====");
        assertEquals(content, foundScrap.getContent(), "==== Content must match ====");
        assertEquals(author, foundScrap.getAuthor(), "==== Author must match ====");
        assertEquals(url, foundScrap.getUrl(), "==== URL must match ====");

        // Save the Scrap ID for temporary use
        Long tempScrapID = foundScrap.getScrapID();

        // Delete found scrap
        scrapRepository.delete(foundScrap);

        // Try to retrieve the deleted scrap
        Optional<Scrap> shouldBeDeletedScrapOptional = scrapRepository.findById(tempScrapID);
        assertFalse(shouldBeDeletedScrapOptional.isPresent(), "==== Scrap must not be found using ScrapID ====");
    }

    @Test
    @Order(4)
    public void testCreateReadDifficultMode() {
        // Create 20 users
        int numUsers = 20;

        // Set parameters for user
        String usernameFormat = "Username";
        String passwordHashedFormat = "PasswordHashed";
        String passwordSaltFormat = "PasswordSalt";
        String emailFormat = "Email";
        Role roleFormat = Role.user;
        Date signupDateFormat = new Date();

        // Set flags to update
        char charFlag = 'A';

        for (int i = 0; i < numUsers; i++) {
            // Create user
            User user = new User();
            user.setUsername(usernameFormat + charFlag);
            user.setPasswordHashed(passwordHashedFormat + charFlag);
            user.setPasswordSalt(passwordSaltFormat + charFlag);
            user.setEmail(emailFormat + charFlag);
            user.setRole(roleFormat);
            user.setSignupDate(signupDateFormat);

            // Save user
            userRepository.save(user);

            // Increment flag
            charFlag += 1;
        }

        // Check if total number of users match after creation
        assertEquals(numUsers, userRepository.findAll().size(), "==== Total number of users must match ====");

        // Retrieve list of all users
        List<User> userList = userRepository.findAll();

        // Set dictionary of number of scraps per user
        Map<Long, Integer> expectedDictionary = new HashMap<>();

        // Set flags to update
        charFlag = 'A';

        // Create random(1 ~ 100) number of scraps per user
        for (User theUser : userList) {
            // Generate random number of scraps to create
            int numScraps = new Random().nextInt(100) + 1;

            // Populate expected dictionary
            expectedDictionary.put(theUser.getUserID(), numScraps);

            // Set parameters for scrap
            String titleFormat = "Title";
            String contentFormat = "TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent";
            String authorFormat = "Author";
            String urlFormat = "URL";
            Date scrapDateFormat = new Date();

            for (int i = 0; i < numScraps; i++) {
                // Create new scrap
                Scrap scrap = new Scrap();
                scrap.setUserID(theUser.getUserID());
                scrap.setTitle(titleFormat + charFlag);
                scrap.setContent(contentFormat);
                scrap.setAuthor(authorFormat + charFlag);
                scrap.setUrl(urlFormat + charFlag);
                scrap.setScrapDate(scrapDateFormat);

                // Save the new scrap
                scrapRepository.save(scrap);
            }

            // Increment flag
            charFlag += 1;
        }

        // Check if total number of scraps match per user using dictionary
        for (Long userIDKey : expectedDictionary.keySet()) {
            // Retrieve list of scraps for given user
            List<Scrap> scrapListPerUser = scrapRepository.findByUserID(userIDKey);
            assertEquals(expectedDictionary.get(userIDKey), scrapListPerUser.size());
        }
    }

    @Test
    @Order(5)
    public void testCreateReadHellMode() {
        // Create 20 users
        int numUsers = 20;

        // Set dictionary of number of scraps per user
        Map<Long, Integer> expectedDictionary = new HashMap<>();

        // Set parameters for user
        String usernameFormat = "Username";
        String passwordHashedFormat = "PasswordHashed";
        String passwordSaltFormat = "PasswordSalt";
        String emailFormat = "Email";
        Role roleFormat = Role.user;
        Date signupDateFormat = new Date();

        // Set flags to update
        char charFlag = 'A';

        for (int i = 0; i < numUsers; i++) {
            // Create user
            User user = new User();
            user.setUsername(usernameFormat + charFlag);
            user.setPasswordHashed(passwordHashedFormat + charFlag);
            user.setPasswordSalt(passwordSaltFormat + charFlag);
            user.setEmail(emailFormat + charFlag);
            user.setRole(roleFormat);
            user.setSignupDate(signupDateFormat);

            // Save user
            userRepository.save(user);

            // Populate the expected dictionary
            expectedDictionary.put(user.getUserID(), 0);

            // Increment flag
            charFlag += 1;
        }

        // Check if total number of users match after creation
        assertEquals(numUsers, userRepository.findAll().size(), "==== Total number of users must match ====");

        // Retrieve list of all users
        List<User> userList = userRepository.findAll();

        // Set flags to update
        charFlag = 'A';

        // Set total number of scraps to generate
        int numScrapsTotal = 10000;

        // Set parameters for scrap
        String titleFormat = "Title";
        String contentFormat = "TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent-TheContent";
        String authorFormat = "Author";
        String urlFormat = "URL";
        Date scrapDateFormat = new Date();
        Random myRandom = new Random();

        for (int i = 0; i < numScrapsTotal; i++) {
            // Generate random number (UserID)
            Long randomUserID = myRandom.nextLong(numUsers) + 1;

            // Create new scrap
            Scrap scrap = new Scrap();
            scrap.setUserID(randomUserID);
            scrap.setTitle(titleFormat + randomUserID);
            scrap.setContent(contentFormat);
            scrap.setAuthor(authorFormat + randomUserID);
            scrap.setUrl(urlFormat + randomUserID);
            scrap.setScrapDate(scrapDateFormat);

            // Save new scrap
            scrapRepository.save(scrap);

            // Update the expected dictionary count
            expectedDictionary.put(randomUserID, expectedDictionary.get(randomUserID) + 1);
        }

        // Check if total number of scraps match per user
        for (User theUser : userList) {
            // Retrieve list of all scraps per user
            List<Scrap> scrapList = scrapRepository.findByUserID(theUser.getUserID());
            assertEquals(expectedDictionary.get(theUser.getUserID()), scrapList.size());
        }
    }
}
