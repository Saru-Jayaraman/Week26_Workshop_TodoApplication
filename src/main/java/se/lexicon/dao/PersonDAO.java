package se.lexicon.dao;

import se.lexicon.model.Person;
import java.util.Set;

public interface PersonDAO {
    Person persist(Person person);
    Person findById(int id);
    Person findByEmail(String email);
    Person findByUsername(String userName);
    Set<Person> findAll();
    void remove(int id);
}