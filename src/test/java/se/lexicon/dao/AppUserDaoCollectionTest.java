package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import se.lexicon.model.AppRole;
import se.lexicon.model.AppUser;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class AppUserDaoCollectionTest {
    AppUserDAO testObj, testObj1;
    AppUser appUserObj1, appUserObj2;
    Set<AppUser> appUserSet;

    @BeforeEach
    public void setUp() {
        testObj = AppUserDAOCollection.getInstance();
        appUserSet = new HashSet<>();
        appUserObj1 = new AppUser("person1", "person1", AppRole.ROLE_APP_USER);
        appUserObj2 = new AppUser("person2", "person2", AppRole.ROLE_APP_ADMIN);
    }

    @Test
    @Order(1)
    void testGetInstance() {
        testObj1 = AppUserDAOCollection.getInstance();
        assertEquals(testObj.hashCode(), testObj1.hashCode());
    }

    @Test
    @Order(2)
    void testPersist() {
        AppUser appUserDAOObject = testObj.persist(appUserObj1);
        assertNotNull(appUserDAOObject);
        String expected = "AppUser{username='person1', role=ROLE_APP_USER}";
        assertEquals(expected, appUserDAOObject.toString());
    }

    @Test
    @Order(3)
    void testFindByUsername() {
        //When Username is found
        testObj.persist(appUserObj1);
        String expected = "AppUser{username='person1', role=ROLE_APP_USER}";
        assertEquals(expected, testObj.findByUsername("Person1").toString());

        //When Username is not found
        assertNull(testObj.findByUsername("Person11"));
    }

    @Test
    @Order(4)
    void testFindAll() {
        appUserSet.add(testObj.persist(appUserObj1));
        appUserSet.add(testObj.persist(appUserObj2));
        assertEquals(appUserSet.toString(), testObj.findAll().toString());
    }

    @Test
    @Order(5)
    void testRemove() {
        appUserSet.add(testObj.persist(appUserObj1));
        appUserSet.add(testObj.persist(appUserObj2));
        //When Username is found --> remove
        testObj.remove("person2");
        appUserSet.remove(appUserObj2);
        assertEquals(appUserSet.toString(), testObj.findAll().toString());

        //When Username is found --> not remove
        int sizeBefore = appUserSet.size();
        testObj.remove("person2");
        assertEquals(sizeBefore, testObj.findAll().size());
    }

    @Test
    @Order(6)
    void testValidateInputs() {
        AppUser appUserObj1 = new AppUser("person1", "person1", AppRole.ROLE_APP_USER);
        appUserObj1.setUsername("Person11");
        assertNotNull(appUserObj1.getUsername());

        assertDoesNotThrow(() -> appUserObj1.setPassword("Person11"));

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> appUserObj1.setUsername(" "));
        assertTrue(thrown.getMessage().contains("empty"));

        thrown = assertThrows(IllegalArgumentException.class, () -> appUserObj1.setPassword(null));
        assertTrue(thrown.getMessage().contains("null"));

        thrown = assertThrows(NullPointerException.class, () -> appUserObj1.setRole(null));
        assertTrue(thrown.getMessage().contains("null"));
    }
}