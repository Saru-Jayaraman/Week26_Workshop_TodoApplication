package se.lexicon.dao.impl;

import se.lexicon.dao.PeopleDAO;
import se.lexicon.exception.MySQLException;
import se.lexicon.model.Person;

import java.sql.*;
import java.util.*;

public class PeopleDAOImpl implements PeopleDAO {

    private Connection connection;

    public PeopleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Person create(Person person) {
        validateInputObject(person, "insertion");
        String query = "insert into person(first_name, last_name) values(?, ?)";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 0)
                throw new IllegalArgumentException("Creation operation failed...");
            System.out.println("Person added successfully!!!");
            try (
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys()
            ) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    person.setId(generatedId);
                    System.out.println("Generated Person Id: " + generatedId);
                }
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while creating person with id: " + person.getId(), e);
        }
        return person;
    }

    @Override
    public Collection<Person> findAll() {
        List<Person> personList = new ArrayList<>();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from person")
        ) {
            while (resultSet.next()) {
                int personId = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);

                Person person = new Person(personId, firstName, lastName);
                personList.add(person);
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding all person details: ", e);
        }
        return personList;
    }

    @Override
    public Person findById(int id) {
        Person person = null;
        validateInput(id);
        String query = "select * from person where person_id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                if (!resultSet.isBeforeFirst())
                    throw new IllegalArgumentException("Person id not found in the database... Enter a valid Person id...");
                if (resultSet.next()) {
                    int personId = resultSet.getInt(1);
                    String personFirstName = resultSet.getString(2);
                    String personLastName = resultSet.getString(3);
                    person = new Person(personId, personFirstName, personLastName);
                }
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding person with id: " + id, e);
        }
        return person;
    }

    @Override
    public Collection<Person> findByUsername(String userName) {
        if (userName == null || userName.trim().isEmpty())
            throw new IllegalArgumentException("Username cannot be neither null or empty...");
        List<Person> personList = new ArrayList<>();
        String[] splitUsername = userName.split(" ");
        String query = "select * from person where first_name = ? and last_name = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, splitUsername[0]);
            preparedStatement.setString(2, splitUsername[1]);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                if (!resultSet.isBeforeFirst())
                    throw new MySQLException("Person name not found in the database... Enter a valid Person name...");
                while (resultSet.next()) {
                    Person person;
                    int personId = resultSet.getInt(1);
                    String personFirstName = resultSet.getString(2);
                    String personLastName = resultSet.getString(3);
                    person = new Person(personId, personFirstName, personLastName);
                    personList.add(person);
                }
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding person with username: " + userName, e);
        }
        return personList;
    }

    @Override
    public Person update(Person person) {
        validateInputObject(person, "updation");
        Person existingPerson = findById(person.getId());
        if (existingPerson.equals(person))
            throw new IllegalArgumentException("Existing record in the database and Record to be updated are same... Updation is not needed...");
        String query = "update person set first_name = ?, last_name = ? where person_id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getId());
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 0)
                throw new MySQLException("Updation operation failed...");
            System.out.println("Person updated successfully!!!");
            System.out.println("Updated count: " + resultSet);
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while updating person with id: " + person.getId(), e);
        }
        return person;
    }

    @Override
    public boolean deleteById(int id) {
        validateInput(id);
        findById(id);
        String query = "delete from person where person_id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 0)
                throw new MySQLException("Deletion operation failed...");
            System.out.println("Person deleted successfully!!!");
            return true;
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while deleting person with id: " + id, e);
        }
    }

    private void validateInput(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("Id can be neither 0 nor negative...");
    }

    private void validateInputObject(Person person, String operationName) {
        if (Objects.isNull(person))
            throw new NullPointerException("Person detail is null... Cannot perform " + operationName + " operation...");
    }
}
