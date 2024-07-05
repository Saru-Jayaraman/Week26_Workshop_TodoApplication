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
        AppUser appUser1 = new AppUser("person1", "person1", AppRole.ROLE_APP_ADMIN);
        personObj1 = new Person("Person1", "Person1", "test1@gmail.com", appUser1);
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
    void testToString() {
        String expected = "TodoItem{id=" + todoItemObj1.getId() + ", title='Change Tires', taskDescription='Preferable to use MRF brand', " +
                "deadLine=2024-06-30, done=false}";
        assertEquals(expected, todoItemObj1.toString());
    }

    @Test
    @Order(5)
    void testEquals() {
        assertTrue(todoItemObj1.equals(todoItemObj1));
        assertFalse(todoItemObj1.equals(todoItemObj2));
    }

    @Test
    @Order(6)
    void testHashCode() {
        assertNotEquals(todoItemObj1.hashCode(), todoItemObj2.hashCode());
        assertEquals(todoItemObj1.hashCode(), todoItemObj1.hashCode());
    }
}