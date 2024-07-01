package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTest {
    private TodoItem todoItemObj1, todoItemObj2;

    private Person personObj1;

    @BeforeEach
    void setUp() {
        personObj1 = new Person("Person1", "Person1", "test1@gmail.com");
        todoItemObj1 = new TodoItem("Change Tires", "Preferable to use MRF brand",
                LocalDate.of(2024, 6, 30), personObj1, false);
        todoItemObj2 = new TodoItem("Check brake", " ",
                LocalDate.of(2024, 5, 30), personObj1, false);
    }

    @Test
    @Order(1)
    void testTodoItemConstructor() {
        assertEquals("Change Tires", todoItemObj1.getTitle());
        assertEquals("Preferable to use MRF brand", todoItemObj1.getTaskDescription());
        assertEquals(LocalDate.of(2024, 6, 30), todoItemObj1.getDeadLine());
        assertEquals(personObj1, todoItemObj1.getCreator());
        assertFalse(todoItemObj1.isDone());
    }

    @Test
    @Order(2)
    void testTodoItemConstructorWhenCreatorNull() {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> todoItemObj1.setCreator(null));
        assertTrue(thrown.getMessage().contains("Creator detail is mandatory"));
    }

    @Test
    @Order(3)
    void testTodoItemConstructorForNullEmptyCheck() {
        assertEquals(" ", todoItemObj2.getTaskDescription());

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> todoItemObj2.setTitle(null));
        assertTrue(thrown.getMessage().contains("null"));

        thrown = assertThrows(NullPointerException.class, () -> todoItemObj2.setDeadLine(null));
        assertTrue(thrown.getMessage().contains("null"));
    }

    @Test
    @Order(4)
    void testGetSummary() {
        String expected = "TodoItem ID: " + todoItemObj1.getId() + " Title: Change Tires Description: Preferable to use MRF brand Deadline: 2024-06-30\n" +
                "Creator Details: Person ID: " + todoItemObj1.getCreator().getId() + " Name: Person1 Person1 Email: test1@gmail.com\n";
        assertEquals(expected, todoItemObj1.getSummary());
    }
}