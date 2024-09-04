package se.lexicon.dao;

import se.lexicon.model.Person;

import java.util.Collection;

public interface PeopleDAO {
    Person create(Person person);
    Collection<Person> findAll();
    Person findById(int id);
    Collection<Person> findByUsername(String userName);
    Person update(Person person);
    boolean deleteById(int id);
}