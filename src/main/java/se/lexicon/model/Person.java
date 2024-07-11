package se.lexicon.model;

import java.util.Objects;
import se.lexicon.sequencers.*;

public class Person {
    private final int id;
    private String firstName;
    private String lastName;
    private String email;
    private AppUser credentials;

    public Person(String firstName, String lastName, String email, AppUser credentials) {
        PersonIdSequencer sequencerObject = PersonIdSequencer.getInstance();
        this.id = sequencerObject.nextId();
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setCredentials(credentials);
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

    public String getEmail() {
        return email;
    }

    public AppUser getCredentials() {
        return credentials;
    }

    public void setFirstName(String firstName) {
        validateInput(firstName, "First Name");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        validateInput(lastName, "Last Name");
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        validateInput(email, "Email");
        this.email = email;
    }

    public void setCredentials(AppUser credentials) {
        this.credentials = Objects.requireNonNull(credentials, "Credentials cannot be null...");
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
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + getId() + ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' + ", email='" + getEmail() + '\'' + '}';
    }
}