package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import se.lexicon.model.*;
import se.lexicon.sequencers.TodoItemIdSequencer;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class TodoItemDaoCollectionTest {
    TodoItemDAOCollection testObj, testObj1;
    AppUser appUserObj1, appUserObj2;
    Person personObj1, personObj2;
    TodoItem todoItemObj1, todoItemObj2;
    Set<TodoItem> todoItemSet;

    @BeforeEach
    public void setUp() {
        testObj = TodoItemDAOCollection.getInstance();
        todoItemSet = new HashSet<>();
        TodoItemIdSequencer sequencerObj = TodoItemIdSequencer.getInstance();
        sequencerObj.setCurrentId(100);
        appUserObj1 = new AppUser("person1", "person1", AppRole.ROLE_APP_USER);
        personObj1 = new Person("Person1", "Person1", "test1@gmail.com", appUserObj1);
        appUserObj2 = new AppUser("person2", "person2", AppRole.ROLE_APP_ADMIN);
        personObj2 = new Person("Person2", "Person2", "test2@gmail.com", appUserObj2);
        todoItemObj1 = new TodoItem("Change Tires", "Preferable to use MRF brand",
                LocalDate.of(2024, 6, 30), personObj1, true);
        todoItemObj2 = new TodoItem("Check brightness of light", "Light is dim",
                LocalDate.of(2024, 5, 30), personObj2, true);
    }

    @Test
    @Order(1)
    void testGetInstance() {
        testObj1 = TodoItemDAOCollection.getInstance();
        assertEquals(testObj.hashCode(), testObj1.hashCode());
    }

    @Test
    @Order(2)
    void testPersist() {
        TodoItem todoItemDaoObject = testObj.persist(todoItemObj1);
        assertNotNull(todoItemDaoObject);
        String expected = "TodoItem{id=101, title='Change Tires', taskDescription='Preferable to use MRF brand', deadLine=2024-06-30, done=true}";
        assertEquals(expected, todoItemDaoObject.toString());
    }

    @Test
    @Order(3)
    void testFindById() {
        todoItemSet.add(testObj.persist(todoItemObj1));
        todoItemSet.add(testObj.persist(todoItemObj2));
        String expected = "TodoItem{id=101, title='Change Tires', taskDescription='Preferable to use MRF brand', deadLine=2024-06-30, done=true}";
        assertEquals(expected, testObj.findById(101).toString());

        //When ID is not found
        assertNull(testObj.findById(1));
    }

    @Test
    @Order(4)
    void testFindAll() {
        todoItemSet.add(testObj.persist(todoItemObj1));
        todoItemSet.add(testObj.persist(todoItemObj2));
        assertEquals(todoItemSet.toString(), testObj.findAll().toString());
    }

    @Test
    @Order(5)
    void testFindAllByDoneStatus() {
        todoItemSet.add(testObj.persist(todoItemObj1));
        todoItemSet.add(testObj.persist(todoItemObj2));
        assertEquals(todoItemSet.toString(), testObj.findAllByDoneStatus(true).toString());

        //When DoneStatus is not found
        assertEquals("[]", testObj.findAllByDoneStatus(false).toString());
    }

    @Test
    @Order(6)
    void testFindByTitleContains() {
        todoItemSet.add(testObj.persist(todoItemObj1));
        todoItemSet.add(testObj.persist(todoItemObj2));
        String expected = "[TodoItem{id=101, title='Change Tires', taskDescription='Preferable to use MRF brand', deadLine=2024-06-30, done=true}]";
        assertEquals(expected, testObj.findByTitleContains("change tires").toString());

        //When Title is not found
        assertEquals("[]", testObj.findByTitleContains("tires").toString());
    }

    @Test
    @Order(7)
    void testFindByPersonID() {
        todoItemSet.add(testObj.persist(todoItemObj1));
        todoItemSet.add(testObj.persist(todoItemObj2));
        String expected = "[TodoItem{id=101, title='Change Tires', taskDescription='Preferable to use MRF brand', deadLine=2024-06-30, done=true}]";
        assertEquals(expected, testObj.findByPersonID(1).toString());

        //When PersonID is not found
        assertEquals("[]", testObj.findByPersonID(10).toString());
    }

    @Test
    @Order(8)
    void testFindByDeadlineBefore() {
        todoItemSet.add(testObj.persist(todoItemObj1));
        todoItemSet.add(testObj.persist(todoItemObj2));
        assertEquals(todoItemSet.toString(), testObj.findByDeadlineBefore(LocalDate.of(2024,7,30)).toString());

        assertEquals("[]", testObj.findByDeadlineBefore(LocalDate.of(2024,4,30)).toString());
    }

    @Test
    @Order(9)
    void testFindByDeadlineAfter() {
        todoItemSet.add(testObj.persist(todoItemObj1));
        todoItemSet.add(testObj.persist(todoItemObj2));
        assertEquals(todoItemSet.toString(), testObj.findByDeadlineAfter(LocalDate.of(2024,4,30)).toString());

        assertEquals("[]", testObj.findByDeadlineAfter(LocalDate.of(2024,7,30)).toString());
    }

    @Test
    @Order(10)
    void testRemove() {
        todoItemSet.add(testObj.persist(todoItemObj1));
        todoItemSet.add(testObj.persist(todoItemObj2));
        testObj.remove(101);
        todoItemSet.remove(todoItemObj1);
        assertEquals(todoItemSet.toString(), testObj.findAll().toString());

        //When ID is not found
        int sizeBefore = todoItemSet.size();
        testObj.remove(104);
        assertEquals(sizeBefore, testObj.findAll().size());
    }

    @Test
    @Order(11)
    void testValidateInputs() {
        todoItemSet.add(testObj.persist(todoItemObj1));
        todoItemSet.add(testObj.persist(todoItemObj2));
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> todoItemObj1.setTitle(" "));
        assertTrue(thrown.getMessage().contains("empty"));

        thrown = assertThrows(NullPointerException.class, () -> todoItemObj1.setDeadLine(null));
        assertTrue(thrown.getMessage().contains("null"));
    }
}