package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoItemTaskTest {
    private TodoItem todoItemObj1, todoItemObj2;

    private Person personObj1;

    @BeforeEach
    void setUp() {
        personObj1 = new Person("Person1", "Person1", "test1@gmail.com");
        todoItemObj1 = new TodoItem("Change Tires", "Preferable to use MRF brand",
                LocalDate.of(2024, 6, 30), personObj1, false);
        todoItemObj2 = new TodoItem("Check brightness of light", "Light is dim",
                LocalDate.of(2024, 5, 30), personObj1, true);
    }

    @Test
    @Order(1)
    void testTodoItemTaskConstructor() {
        TodoItemTask todoItemTaskObj1 = new TodoItemTask(personObj1, todoItemObj1);
        assertNotEquals(todoItemTaskObj1.getTodoItem().getId(), todoItemTaskObj1.getId());//Appended 1 for the TodoItemTask

        assertTrue(todoItemTaskObj1.isAssigned());
        assertEquals(personObj1, todoItemTaskObj1.getAssignee());
        assertEquals("Person1", todoItemTaskObj1.getAssignee().getFirstName());

        assertEquals(personObj1, todoItemTaskObj1.getTodoItem().getCreator());
        assertEquals("Change Tires", todoItemTaskObj1.getTodoItem().getTitle());
    }

    @Test
    @Order(2)
    void testTodoItemTaskConstructorWhenTodoItemNull() {
        TodoItemTask todoItemTaskObj2 = new TodoItemTask(personObj1, todoItemObj2);
        Throwable thrown = assertThrows(NullPointerException.class, () -> todoItemTaskObj2.setTodoItem(null));
        assertTrue(thrown.getMessage().contains("null"));
    }

    @Test
    @Order(3)
    void testTodoItemTaskConstructorWhenAssigneeNull() {
        TodoItemTask todoItemTaskObj2 = new TodoItemTask(null, todoItemObj2);
        assertFalse(todoItemTaskObj2.isAssigned());
        assertNull(todoItemTaskObj2.getAssignee());
    }

    @Test
    @Order(4)
    void testGetSummary() {
        TodoItemTask todoItemTaskObj1 = new TodoItemTask(personObj1, todoItemObj1);
        String expected = "TodoItemTask ID: " + todoItemTaskObj1.getId() + " Assigned: TRUE " +
                "TodoItem ID: " + todoItemObj1.getId() + " Title: Change Tires Description: Preferable to use MRF brand Deadline: 2024-06-30\n" +
                "Creator Details: Person ID: " + todoItemObj1.getCreator().getId() + " Name: Person1 Person1 Email: test1@gmail.com\n" +
                "Todo Item is NOT OVERDUE and needs to be completed before deadline by the Assigned Person: Person ID: " + personObj1.getId() +
                " Name: Person1 Person1 Email: test1@gmail.com";
        assertEquals(expected, todoItemTaskObj1.getSummary());

        TodoItemTask todoItemTaskObj2 = new TodoItemTask(null, todoItemObj2);
        expected = "TodoItemTask ID: " + todoItemTaskObj2.getId() + " Assigned: FALSE " +
                "TodoItem ID: " + todoItemObj2.getId() + " Title: Check brightness of light Description: Light is dim Deadline: 2024-05-30\n" +
                "Creator Details: Person ID: " + todoItemObj2.getCreator().getId() + " Name: Person1 Person1 Email: test1@gmail.com\n" +
                "Not assigned to anyone";
        assertEquals(expected, todoItemTaskObj2.getSummary());
    }
}