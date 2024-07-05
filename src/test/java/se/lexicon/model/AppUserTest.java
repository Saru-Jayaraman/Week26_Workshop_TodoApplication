package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AppUserTest {
    AppUser appUser1, appUser2;

    @BeforeEach
    void setUp() {
        appUser1 = new AppUser("person1", "person1", AppRole.ROLE_APP_USER);
        appUser2 = new AppUser("person2", "person2", AppRole.ROLE_APP_ADMIN);
    }

    @Test
    @Order(1)
    void testAppUserConstructor() {
        assertEquals("person1", appUser1.getUsername());
        assertEquals(AppRole.ROLE_APP_USER, appUser1.getRole());
    }

    @Test
    @Order(2)
    void testValidateInput() {
        appUser2.setUsername("Person22");
        assertNotNull(appUser2.getUsername());

        assertDoesNotThrow(() -> appUser2.setPassword("Person22"));

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> appUser2.setUsername(" "));
        assertTrue(thrown.getMessage().contains("empty"));

        thrown = assertThrows(IllegalArgumentException.class, () -> appUser2.setPassword(null));
        assertTrue(thrown.getMessage().contains("null"));

        thrown = assertThrows(NullPointerException.class, () -> appUser2.setRole(null));
        assertTrue(thrown.getMessage().contains("null"));
    }

    @Test
    @Order(3)
    void testToString() {
        String expected = "AppUser{username='person1', role=ROLE_APP_USER}";
        assertEquals(expected, appUser1.toString());
    }

    @Test
    @Order(4)
    void testEquals() {
        assertTrue(appUser1.equals(appUser1));
        assertFalse(appUser1.equals(appUser2));
    }

    @Test
    @Order(5)
    void testHashCode() {
        assertNotEquals(appUser1.hashCode(), appUser2.hashCode());
        assertEquals(appUser1.hashCode(), appUser1.hashCode());
    }
}
