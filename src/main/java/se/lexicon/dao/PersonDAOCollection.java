package se.lexicon.dao;

import se.lexicon.model.Person;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PersonDAOCollection implements PersonDAO {

    private final Set<Person> personSet;

    //Singleton Object
    private static PersonDAOCollection instance;

    //Private constructor - to make the class not to get instantiated from outside
    private PersonDAOCollection() {
        personSet = new HashSet<>();
    }

    //When there is no object, create an object; Else return the instance of existing one.
    public static PersonDAOCollection getInstance() {
        if (instance == null)
            instance = new PersonDAOCollection();
        return instance;
    }

    @Override
    public Person persist(Person person) {
        personSet.add(person);
        return person;
    }

    @Override
    public Person findById(int id) {
        validateInputInteger(id);
        for (Person person : personSet) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person findByEmail(String email) {
        validateInputString(email);
        for (Person person : personSet) {
            if (person.getEmail().equalsIgnoreCase(email)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person findByUsername(String userName) {
        validateInputString(userName);
        for(Person person : personSet) {
            if(person.getCredentials().getUsername().equalsIgnoreCase(userName)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Set<Person> findAll() {
        return new HashSet<>(personSet);
    }

    @Override
    public void remove(int id) {
        validateInputInteger(id);
        Iterator<Person> iterator = personSet.iterator();
        Person removePerson;
        while (iterator.hasNext()) {
            removePerson = iterator.next();
            if (removePerson.getId() == id) {
                iterator.remove();
                break;
            }
        }
    }

    private void validateInputString(String userName) {
        if(userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty...");
        }
    }

    private void validateInputInteger(int id) {
        if(id <= 0) {
            throw new IllegalArgumentException("ID cannot be zero or negative number...");
        }
    }
}
