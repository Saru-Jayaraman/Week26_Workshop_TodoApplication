package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import se.lexicon.model.*;
import se.lexicon.sequencers.TodoItemIdSequencer;
import se.lexicon.sequencers.TodoItemTaskIdSequencer;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTaskDaoCollectionTest {
    TodoItemTaskDAO testObj, testObj1;
    AppUser appUserObj1, appUserObj2;
    Person personObj1, personObj2;
    TodoItem todoItemObj1, todoItemObj2;
    TodoItemTask todoItemTaskObj1, todoItemTaskObj2;
    Set<TodoItemTask> todoItemTaskSet;

    @BeforeEach
    public void setUp() {
        testObj = TodoItemTaskDAOCollection.getInstance();
        todoItemTaskSet = new HashSet<>();
        TodoItemTaskIdSequencer sequencerObj = TodoItemTaskIdSequencer.getInstance();
        sequencerObj.setCurrentId(200);
        TodoItemIdSequencer sequencerObj1 = TodoItemIdSequencer.getInstance();
        sequencerObj1.setCurrentId(100);
        appUserObj1 = new AppUser("person1", "person1", AppRole.ROLE_APP_USER);
        personObj1 = new Person("Person1", "Person1", "test1@gmail.com", appUserObj1);
        appUserObj2 = new AppUser("person2", "person2", AppRole.ROLE_APP_ADMIN);
        personObj2 = new Person("Person2", "Person2", "test2@gmail.com", appUserObj2);
        todoItemObj1 = new TodoItem("Change Tires", "Preferable to use MRF brand",
                LocalDate.of(2024, 6, 30), personObj1, true);
        todoItemObj2 = new TodoItem("Check brightness of light", "Light is dim",
                LocalDate.of(2024, 5, 30), personObj2, false);
        todoItemTaskObj1 = new TodoItemTask(personObj2, todoItemObj1);
        todoItemTaskObj2 = new TodoItemTask(personObj1, todoItemObj2);
    }

    @Test
    @Order(1)
    void testGetInstance() {
        testObj1 = TodoItemTaskDAOCollection.getInstance();
        assertEquals(testObj.hashCode(), testObj1.hashCode());
    }

    @Test
    @Order(2)
    void testPersist() {
        TodoItemTask todoItemTaskDaoObject = testObj.persist(todoItemTaskObj1);
        assertNotNull(todoItemTaskDaoObject);

        String expected = "TodoItemTask{id=201, assigned=true, todoItem=TodoItem{id=101, title='Change Tires', taskDescription='Preferable to use MRF brand', deadLine=2024-06-30, done=true}}\n" +
                "Todo Item was FINISHED";
        assertEquals(expected, todoItemTaskDaoObject.toString());
    }

    @Test
    @Order(3)
    void testFindById() {
        todoItemTaskSet.add(testObj.persist(todoItemTaskObj1));
        todoItemTaskSet.add(testObj.persist(todoItemTaskObj2));
        String expected = "TodoItemTask{id=201, assigned=true, todoItem=TodoItem{id=101, title='Change Tires', taskDescription='Preferable to use MRF brand', deadLine=2024-06-30, done=true}}\n" +
                "Todo Item was FINISHED";
        assertEquals(expected, testObj.findById(201).toString());

        //When ID is not found
        assertNull(testObj.findById(210));
    }

    @Test
    @Order(4)
    void testFindAll() {
        todoItemTaskSet.add(testObj.persist(todoItemTaskObj1));
        todoItemTaskSet.add(testObj.persist(todoItemTaskObj2));
        assertEquals(todoItemTaskSet.toString(), testObj.findAll().toString());
    }

    @Test
    @Order(5)
    void testFindAllByDoneStatus() {
        todoItemTaskSet.add(testObj.persist(todoItemTaskObj1));
        todoItemTaskSet.add(testObj.persist(todoItemTaskObj2));
        assertEquals(todoItemTaskSet.toString(), testObj.findByAssignedStatus(true).toString());

        //When DoneStatus is not found
        assertEquals("[]", testObj.findByAssignedStatus(false).toString());
    }

    @Test
    @Order(6)
    void testFindByPersonID() {
        todoItemTaskSet.add(testObj.persist(todoItemTaskObj1));
        todoItemTaskSet.add(testObj.persist(todoItemTaskObj2));
        String expected = "[TodoItemTask{id=201, assigned=true, todoItem=TodoItem{id=101, title='Change Tires', taskDescription='Preferable to use MRF brand', deadLine=2024-06-30, done=true}}\n" +
                "Todo Item was FINISHED]";
        assertEquals(expected, testObj.findByPersonId(2).toString());

        //When PersonID is not found
        assertEquals("[]", testObj.findByPersonId(10).toString());
    }

    @Test
    @Order(7)
    void testRemove() {
        todoItemTaskSet.add(testObj.persist(todoItemTaskObj1));
        todoItemTaskSet.add(testObj.persist(todoItemTaskObj2));
        testObj.remove(201);
        todoItemTaskSet.remove(todoItemTaskObj1);
        assertEquals(todoItemTaskSet.toString(), testObj.findAll().toString());

        //When ID is not found
        int sizeBefore = todoItemTaskSet.size();
        testObj.remove(204);
        assertEquals(sizeBefore, testObj.findAll().size());
    }
}