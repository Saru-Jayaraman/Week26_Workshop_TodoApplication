package se.lexicon.model;

import java.util.Objects;

public class Person {
    private int id;
    private String firstName;
    private String lastName;

    public Person(int id, String firstName, String lastName) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        validateInput(firstName, "First Name");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        validateInput(lastName, "Last Name");
        this.lastName = lastName;
    }

    private void validateInput(String paramName, String paramFullName) {
        if(paramName == null || paramName.trim().isEmpty()) {
            throw new IllegalArgumentException(paramFullName + " is either empty or null...");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + getId() + ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' + '}';
    }
}