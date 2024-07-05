package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person personObj1, personObj2;

    @BeforeEach
    void setUp() {
        AppUser appUser1 = new AppUser("person1", "person1", AppRole.ROLE_APP_USER);
        AppUser appUser2 = new AppUser("person2", "person2", AppRole.ROLE_APP_ADMIN);
        personObj1 = new Person("Person1", "Person1", "test1@gmail.com", appUser1);
        personObj2 = new Person("Person2", "Person2", "test2@gmail.com", appUser2);
    }

    @Test
    @Order(1)
    void testPersonConstructor() {
        assertEquals("Person1", personObj1.getFirstName());
        assertEquals("Person1", personObj1.getLastName());
        assertEquals("test1@gmail.com", personObj1.getEmail());
    }

    @Test
    @Order(2)
    void testValidateInput() {
        personObj1.setFirstName("Pragya");
        assertNotNull(personObj1.getFirstName());

        personObj1.setLastName("Pragya");
        assertNotNull(personObj1.getLastName());

        assertDoesNotThrow(() -> personObj1.setEmail("pragya@gmail.com"));

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> personObj2.setFirstName(" "));
        assertTrue(thrown.getMessage().contains("empty"));

        thrown = assertThrows(IllegalArgumentException.class, () -> personObj2.setLastName(" "));
        assertTrue(thrown.getMessage().contains("empty"));

        thrown = assertThrows(IllegalArgumentException.class, () -> personObj2.setEmail(null));
        assertTrue(thrown.getMessage().contains("null"));

        thrown = assertThrows(NullPointerException.class, () -> personObj2.setCredentials(null));
        assertTrue(thrown.getMessage().contains("null"));
    }

    @Test
    @Order(3)
    void testToString() {
        String expected = "Person{id=" + personObj2.getId() + ", firstName='Person2', lastName='Person2', " +
                "email='test2@gmail.com'}";
        assertEquals(expected, personObj2.toString());
    }

    @Test
    @Order(4)
    void testEquals() {
        assertTrue(personObj1.equals(personObj1));
        assertFalse(personObj1.equals(personObj2));
    }

    @Test
    @Order(5)
    void testHashCode() {
        assertNotEquals(personObj1.hashCode(), personObj2.hashCode());
        assertEquals(personObj1.hashCode(), personObj1.hashCode());
    }
}