package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person personObj1, personObj2;

    @BeforeEach
    void setUp() {
        personObj1 = new Person("Person1", "Person1", "test1@gmail.com");
        personObj2 = new Person("Person2", "Person2", "test2@gmail.com");
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
    }

    @Test
    @Order(3)
    void testGetSummary() {
        String expected = "Person ID: " + personObj2.getId() + " Name: Person2 Person2 Email: test2@gmail.com";
        assertEquals(expected, personObj2.getSummary());
    }
}