package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTaskTest {
    private TodoItem todoItemObj1, todoItemObj2;

    private Person personObj1;

    @BeforeEach
    void setUp() {
        AppUser appUser1 = new AppUser("person1", "person1", AppRole.ROLE_APP_USER);
        personObj1 = new Person("Person1", "Person1", "test1@gmail.com", appUser1);
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
    void testToString() {
        TodoItemTask todoItemTaskObj1 = new TodoItemTask(personObj1, todoItemObj1);
        String expected = "TodoItemTask{id=" + todoItemTaskObj1.getId() + ", assigned=true, todoItem=TodoItem{id=" + todoItemObj1.getId() +
                ", title='Change Tires', " + "taskDescription='Preferable to use MRF brand', deadLine=2024-06-30, done=false}}\n" +
                "Todo Item is OVERDUE";
        assertEquals(expected, todoItemTaskObj1.toString());

        TodoItemTask todoItemTaskObj2 = new TodoItemTask(null, todoItemObj2);
        expected = "TodoItemTask{id=" + todoItemTaskObj2.getId() + ", assigned=false, todoItem=TodoItem{id=" + todoItemObj2.getId() +
                ", title='Check brightness of light', taskDescription='Light is dim', deadLine=2024-05-30, done=true}}\n" +
                "Not assigned to anyone";
        assertEquals(expected, todoItemTaskObj2.toString());
    }

    @Test
    @Order(5)
    void testEquals() {
        TodoItemTask todoItemTaskObj1 = new TodoItemTask(personObj1, todoItemObj1);
        TodoItemTask todoItemTaskObj2 = new TodoItemTask(personObj1, todoItemObj2);
        assertTrue(todoItemTaskObj1.equals(todoItemTaskObj1));
        assertFalse(todoItemTaskObj1.equals(todoItemTaskObj2));
    }

    @Test
    @Order(6)
    void testHashCode() {
        TodoItemTask todoItemTaskObj1 = new TodoItemTask(personObj1, todoItemObj1);
        TodoItemTask todoItemTaskObj2 = new TodoItemTask(personObj1, todoItemObj2);
        assertNotEquals(todoItemTaskObj1.hashCode(), todoItemTaskObj2.hashCode());
        assertEquals(todoItemTaskObj1.hashCode(), todoItemTaskObj1.hashCode());
    }
}