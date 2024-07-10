package se.lexicon.dao;

import se.lexicon.model.Person;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PersonDAOCollection implements PersonDAO {

    Set<Person> personSet = new HashSet<>();

    @Override
    public Person persist(Person person) {
        personSet.add(person);
        return person;
    }

    @Override
    public Person findById(int id) {
        for (Person person : personSet) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person findByEmail(String email) {
        for (Person person : personSet) {
            if (person.getEmail().equals(email)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Set<Person> findAll() {
        HashSet<Person> newPersonSet = new HashSet<>(personSet);
        return newPersonSet;
    }

    @Override
    public void remove(int id) {
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
}
