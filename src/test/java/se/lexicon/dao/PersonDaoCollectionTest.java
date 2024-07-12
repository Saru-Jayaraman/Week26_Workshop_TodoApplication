package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import se.lexicon.model.AppRole;
import se.lexicon.model.AppUser;
import se.lexicon.model.Person;
import se.lexicon.sequencers.PersonIdSequencer;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class PersonDaoCollectionTest {
    PersonDAO testObj, testObj1;
    AppUser appUserObj1, appUserObj2;
    Person personObj1, personObj2;
    Set<Person> personSet;

    @BeforeEach
    public void setUp() {
        testObj = PersonDAOCollection.getInstance();
        personSet = new HashSet<>();
        PersonIdSequencer sequencerObj = PersonIdSequencer.getInstance();
        sequencerObj.setCurrentId(0);
        appUserObj1 = new AppUser("person1", "person1", AppRole.ROLE_APP_USER);
        personObj1 = new Person("Person1", "Person1", "test1@gmail.com", appUserObj1);
        appUserObj2 = new AppUser("person2", "person2", AppRole.ROLE_APP_ADMIN);
        personObj2 = new Person("Person2", "Person2", "test2@gmail.com", appUserObj2);
    }

    @Test
    @Order(1)
    void testGetInstance() {
        testObj1 = PersonDAOCollection.getInstance();
        assertEquals(testObj.hashCode(), testObj1.hashCode());
    }

    @Test
    @Order(2)
    void testPersist() {
        Person personDAOObject = testObj.persist(personObj1);
        assertNotNull(personDAOObject);

        String expected = "Person{id=1, firstName='Person1', lastName='Person1', email='test1@gmail.com'}";
        assertEquals(expected, personDAOObject.toString());
    }

    @Test
    @Order(3)
    void testFindById() {
        personSet.add(testObj.persist(personObj1));
        personSet.add(testObj.persist(personObj2));
        String expected = "Person{id=1, firstName='Person1', lastName='Person1', email='test1@gmail.com'}";
        assertEquals(expected, testObj.findById(1).toString());

        //When ID is not found
        assertNull(testObj.findById(10));
    }

    @Test
    @Order(4)
    void testFindByEmail() {
        personSet.add(testObj.persist(personObj1));
        personSet.add(testObj.persist(personObj2));
        String expected = "Person{id=1, firstName='Person1', lastName='Person1', email='test1@gmail.com'}";
        assertEquals(expected, testObj.findByEmail("Test1@gmail.com").toString());

        //When Email is not found
        assertNull(testObj.findByEmail("Person11@gmail.com"));
    }

    @Test
    @Order(5)
    void testFindByUsername() {
        personSet.add(testObj.persist(personObj1));
        personSet.add(testObj.persist(personObj2));
        String expected = "Person{id=1, firstName='Person1', lastName='Person1', email='test1@gmail.com'}";
        assertEquals(expected, testObj.findByUsername("Person1").toString());

        //When Username is not found
        assertNull(testObj.findByUsername("Person11@gmail.com"));
    }

    @Test
    @Order(6)
    void testFindAll() {
        personSet.add(testObj.persist(personObj1));
        personSet.add(testObj.persist(personObj2));
        assertEquals(personSet.toString(), testObj.findAll().toString());
    }

    @Test
    @Order(7)
    void testRemove() {
        personSet.add(testObj.persist(personObj1));
        personSet.add(testObj.persist(personObj2));
        testObj.remove(1);
        personSet.remove(personObj1);
        assertEquals(personSet.toString(), testObj.findAll().toString());

        //When ID is not found
        int sizeBefore = personSet.size();
        testObj.remove(11);
        assertEquals(sizeBefore, testObj.findAll().size());
    }

    @Test
    @Order(8)
    void testValidateInputs() {
        personSet.add(testObj.persist(personObj1));
        personSet.add(testObj.persist(personObj2));
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> personObj1.setEmail(" "));
        assertTrue(thrown.getMessage().contains("empty"));

        thrown = assertThrows(IllegalArgumentException.class, () -> personObj1.getCredentials().setUsername(null));
        assertTrue(thrown.getMessage().contains("null"));
    }
}